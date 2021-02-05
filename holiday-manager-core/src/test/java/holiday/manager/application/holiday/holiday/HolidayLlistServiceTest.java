package holiday.manager.application.holiday.holiday;

import static org.assertj.core.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.holiday.Holiday;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.holiday.holiday.HolidayListRepository;
import holiday.manager.domain.model.holiday.holiday.event.HolidayGranted;
import holiday.manager.domain.model.holiday.holiday.event.HolidayListCreated;
import holiday.manager.domain.model.holiday.holiday.event.HolidayTook;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.event.sourcing.EventStore;
import holiday.manager.event.sourcing.EventStream;
import holiday.manager.event.sourcing.EventStreamId;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HolidayLlistServiceTest {
	@Autowired
	private HolidayListService service;

	@Autowired
	private EventStore eventStore;

	@Autowired
	private HolidayListRepository repository;

	@Test
	public void createHolidayList() {
		UserId owner = new UserId(1);

		HolidayList holidayList = service.createHolidayList(owner);

		// 休暇リスト作成イベントが登録されていること
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(holidayList.getId().getValue()));
		assertThat(eventStream.events()).hasSize(1);
		assertThat(eventStream.events().get(0)).isInstanceOf(HolidayListCreated.class);

		// 休暇リストがイベントストリームから再現できること
		HolidayList saved = repository.findById(holidayList.getId());
		assertThat(saved.getId()).isEqualTo(holidayList.getId());
		assertThat(saved.getOwner()).isEqualTo(holidayList.getOwner());

	}

	@Test
	public void grantHoliday() {
		UserId owner = new UserId(1);
		Date grantedDate = Date.from(ZonedDateTime.now().toInstant());
		Date expirationDate = Date.from(ZonedDateTime.now().plusYears(2).toInstant());

		service.createHolidayList(owner);
		HolidayList holidayList = service.grantHoliday(owner, KindOfHoliday.PAYED_LEAVE, 10, grantedDate,
				expirationDate);
		// 休暇リスト作成イベントと休暇付与イベントが登録されていること
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(holidayList.getId().getValue()));
		assertThat(eventStream.events()).hasSize(2);
		assertThat(eventStream.events().get(0)).isInstanceOf(HolidayListCreated.class);
		assertThat(eventStream.events().get(1)).isInstanceOf(HolidayGranted.class);

		// 休暇リストがイベントストリームから再現できること
		HolidayList saved = repository.findById(holidayList.getId());
		assertThat(saved.getId()).isEqualTo(holidayList.getId());
		assertThat(saved.getOwner()).isEqualTo(holidayList.getOwner());
		assertThat(saved.getHolidays().size()).isEqualTo(1);
		assertThat(saved.getHolidays().get(0).getKind()).isEqualTo(KindOfHoliday.PAYED_LEAVE);
		assertThat(saved.getHolidays().get(0).getDays()).isEqualTo(10);
		assertThat(saved.getHolidays().get(0).getGrantedDate()).isEqualTo(grantedDate);
		assertThat(saved.getHolidays().get(0).getExpirationDate()).isEqualTo(expirationDate);

	}

	@Test
	public void takeHoliday() {
		UserId owner = new UserId(1);
		Date grantedDate = Date.from(ZonedDateTime.now().toInstant());
		Date expirationDate = Date.from(ZonedDateTime.now().plusYears(2).toInstant());
		Date today = Date.from(ZonedDateTime.now().toInstant());
		HolidayApplicationId applicationId = new HolidayApplicationId();

		service.createHolidayList(owner);
		service.grantHoliday(owner, KindOfHoliday.PAYED_LEAVE, 10, grantedDate,
				expirationDate);
		HolidayList holidayList = service.takeHoliday(owner, KindOfHoliday.PAYED_LEAVE, 0.5, today, applicationId);
		// 休暇リスト作成イベントと休暇付与イベントと休暇取得イベントが登録されていること
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(holidayList.getId().getValue()));
		assertThat(eventStream.events()).hasSize(3);
		assertThat(eventStream.events().get(0)).isInstanceOf(HolidayListCreated.class);
		assertThat(eventStream.events().get(1)).isInstanceOf(HolidayGranted.class);
		assertThat(eventStream.events().get(2)).isInstanceOf(HolidayTook.class);

		// 休暇リストがイベントストリームから再現できること
		HolidayList saved = repository.findById(holidayList.getId());
		assertThat(saved.getId()).isEqualTo(holidayList.getId());
		assertThat(saved.getOwner()).isEqualTo(holidayList.getOwner());
		assertThat(saved.getHolidays().size()).isEqualTo(1);
		assertThat(saved.getHolidays().get(0).getKind()).isEqualTo(KindOfHoliday.PAYED_LEAVE);
		assertThat(saved.getHolidays().get(0).getDays()).isEqualTo(9.5);
		assertThat(saved.getHolidays().get(0).getGrantedDate()).isEqualTo(grantedDate);
		assertThat(saved.getHolidays().get(0).getExpirationDate()).isEqualTo(expirationDate);

	}

	@Test
	public void findHolidayList() {
		UserId owner = new UserId(1);

		service.createHolidayList(owner);
		//HolidayListが取得できること
		HolidayList holidayList = service.findHolidayList(owner);
		assertThat(holidayList.getId()).isEqualTo(holidayList.getId());
		assertThat(holidayList.getOwner()).isEqualTo(holidayList.getOwner());
	}

	@Test
	public void takeHoliday_日数不足() {
		UserId owner = new UserId();
		Date grantedDate = Date.from(ZonedDateTime.now().toInstant());
		Date expirationDate = Date.from(ZonedDateTime.now().plusYears(2).toInstant());
		Date today = Date.from(ZonedDateTime.now().toInstant());
		HolidayApplicationId applicationId = new HolidayApplicationId();

		service.createHolidayList(owner);
		service.grantHoliday(owner, KindOfHoliday.PAYED_LEAVE, 0.5, grantedDate,
				expirationDate);
		try {
			service.takeHoliday(owner, KindOfHoliday.PAYED_LEAVE, 1, today, applicationId);
			fail("エラー発生せず");
		} catch(IllegalStateException e) {

		}
	}

	@Test
	public void takeHoliday_期限切れ() {
		UserId owner = new UserId();
		Date grantedDate = Date.from(ZonedDateTime.now().plusDays(-2).toInstant());
		Date expirationDate = Date.from(ZonedDateTime.now().plusDays(-1).toInstant());
		Date today = Date.from(ZonedDateTime.now().toInstant());
		HolidayApplicationId applicationId = new HolidayApplicationId();

		service.createHolidayList(owner);
		service.grantHoliday(owner, KindOfHoliday.PAYED_LEAVE, 10, grantedDate,
				expirationDate);
		try {
			service.takeHoliday(owner, KindOfHoliday.PAYED_LEAVE, 1, today, applicationId);
			fail("エラー発生せず");
		} catch(IllegalStateException e) {

		}
	}

	@Test
	public void takeHoliday_有効期限の早いものから消費() {
		UserId owner = new UserId();
		Date grantedDate = Date.from(ZonedDateTime.now().toInstant());
		Date expirationDate1 = Date.from(ZonedDateTime.now().plusYears(2).toInstant());
		Date expirationDate2 = Date.from(ZonedDateTime.now().plusDays(2).toInstant());
		Date expirationDate3 = Date.from(ZonedDateTime.now().plusYears(1).toInstant());
		Date today = Date.from(ZonedDateTime.now().toInstant());
		HolidayApplicationId applicationId = new HolidayApplicationId();

		service.createHolidayList(owner);
		service.grantHoliday(owner, KindOfHoliday.PAYED_LEAVE, 10, grantedDate,
				expirationDate1);
		service.grantHoliday(owner, KindOfHoliday.PAYED_LEAVE, 10, grantedDate,
				expirationDate2);
		service.grantHoliday(owner, KindOfHoliday.PAYED_LEAVE, 10, grantedDate,
				expirationDate3);
		HolidayList holidayList = service.takeHoliday(owner, KindOfHoliday.PAYED_LEAVE, 0.5, today, applicationId);

		List<Holiday> result = holidayList.getHolidays();
		assertThat(result.get(0).getDays()).isEqualTo(10);
		assertThat(result.get(1).getDays()).isEqualTo(9.5);
		assertThat(result.get(2).getDays()).isEqualTo(10);
	}
}
