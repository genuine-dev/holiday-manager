package holiday.manager.application;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import holiday.manager.application.query.holiday.HolidayApplicationQueryService;
import holiday.manager.application.query.holiday.dto.HolidayApplicationDto;
import holiday.manager.application.service.holiday.HolidayApplicationService;
import holiday.manager.domain.model.AuthenticationException;
import holiday.manager.domain.model.holiday.HolidayApplication;
import holiday.manager.domain.model.holiday.HolidayApplicationRepository;
import holiday.manager.domain.model.holiday.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.HolidayType;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.event.HolidayApplicationApplied;
import holiday.manager.domain.model.holiday.event.HolidayApplicationApproved;
import holiday.manager.domain.model.holiday.event.HolidayApplicationCanceled;
import holiday.manager.domain.model.holiday.event.HolidayApplicationRejected;
import holiday.manager.domain.model.user.User;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.event.sourcing.EventStore;
import holiday.manager.event.sourcing.EventStream;
import holiday.manager.event.sourcing.EventStreamId;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HolidayApplicationServiceTest {

	@Autowired
	private HolidayApplicationService service;

	@Autowired
	private EventStore eventStore;

	@Autowired
	private HolidayApplicationRepository holidayApplicationRepository;

	@Autowired
	private HolidayApplicationQueryService queryService;

	@Test
	public void applyHolidayApplication() {
		//休暇申請時にステータスがAPPLYINGになること
		@SuppressWarnings("deprecation")
		Date applyDate = new Date(2020, 10, 10);
		User applicant = new User(new UserId("test"), new ArrayList<User>());
		HolidayApplication application = service.apply(KindOfHoliday.PAYED_LEAVE, HolidayType.FULL_OFF, applyDate,
				applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);

		//休暇申請申請イベントが登録されていること
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(application.getId().getValue()));
		assertThat(eventStream.events()).hasSize(1);
		assertThat(eventStream.events().get(0)).isInstanceOf(HolidayApplicationApplied.class);

		//休暇申請がイベントストリームから再現できること
		HolidayApplication saved = holidayApplicationRepository.findById(application.getId());
		assertThat(saved.getId()).isEqualTo(application.getId());
		assertThat(saved.getKindOfHoliday()).isEqualTo(application.getKindOfHoliday());
		assertThat(saved.getHolidayType()).isEqualTo(application.getHolidayType());
		assertThat(saved.getStatus()).isEqualTo(application.getStatus());
		assertThat(saved.getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(saved.getApplicantId()).isEqualTo(application.getApplicantId());

		//クエリサービスで休暇申請が取得できること
		List<HolidayApplicationDto> applications = queryService.findAll();
		assertThat(applications).hasSize(1);
		assertThat(applications.get(0).getId()).isEqualTo(application.getId().getValue());
		assertThat(applications.get(0).getKind()).isEqualTo(application.getKindOfHoliday().name());
		assertThat(applications.get(0).getType()).isEqualTo(application.getHolidayType().name());
		assertThat(applications.get(0).getStatus()).isEqualTo(application.getStatus().name());
		assertThat(applications.get(0).getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(applications.get(0).getAplicantId()).isEqualTo(application.getApplicantId().getValue());
	}

	@Test
	public void approveHolidayApplication() {
		//休暇申請時にステータスがAPPROVEDになること
		@SuppressWarnings("deprecation")
		Date applyDate = new Date(2020, 10, 10);
		User applicant = new User(new UserId("test"), new ArrayList<User>());
		User approver = new User(new UserId("admin"), Arrays.asList(applicant));

		HolidayApplication application = service.apply(KindOfHoliday.PAYED_LEAVE, HolidayType.FULL_OFF, applyDate,
				applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);
		application = service.approve(application.getId(), approver);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPROVED);

		//休暇申請申請イベントと休暇申請承認イベントが登録されていること
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(application.getId().getValue()));
		assertThat(eventStream.events()).hasSize(2);
		assertThat(eventStream.events().get(0)).isInstanceOf(HolidayApplicationApplied.class);
		assertThat(eventStream.events().get(1)).isInstanceOf(HolidayApplicationApproved.class);

		//休暇申請がイベントストリームから再現できること
		HolidayApplication saved = holidayApplicationRepository.findById(application.getId());
		assertThat(saved.getId()).isEqualTo(application.getId());
		assertThat(saved.getKindOfHoliday()).isEqualTo(application.getKindOfHoliday());
		assertThat(saved.getHolidayType()).isEqualTo(application.getHolidayType());
		assertThat(saved.getStatus()).isEqualTo(application.getStatus());
		assertThat(saved.getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(saved.getApplicantId()).isEqualTo(application.getApplicantId());
		assertThat(saved.getApproverId()).isEqualTo(application.getApproverId());

		//クエリサービスで休暇申請が取得できること
		List<HolidayApplicationDto> applications = queryService.findAll();
		assertThat(applications).hasSize(1);
		assertThat(applications.get(0).getId()).isEqualTo(application.getId().getValue());
		assertThat(applications.get(0).getKind()).isEqualTo(application.getKindOfHoliday().name());
		assertThat(applications.get(0).getType()).isEqualTo(application.getHolidayType().name());
		assertThat(applications.get(0).getStatus()).isEqualTo(application.getStatus().name());
		assertThat(applications.get(0).getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(applications.get(0).getAplicantId()).isEqualTo(application.getApplicantId().getValue());
		assertThat(applications.get(0).getApproverId()).isEqualTo(application.getApproverId().getValue());
	}

	@Test
	public void rejectHolidayApplication() {
		//休暇申請時にステータスがREJECTEDになること
		@SuppressWarnings("deprecation")
		Date applyDate = new Date(2020, 10, 10);
		User applicant = new User(new UserId("test"), new ArrayList<User>());
		User approver = new User(new UserId("admin"), Arrays.asList(applicant));

		HolidayApplication application = service.apply(KindOfHoliday.PAYED_LEAVE, HolidayType.FULL_OFF, applyDate,
				applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);
		application = service.reject(application.getId(), approver);

		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.REJECTED);

		//休暇申請申請イベントと休暇申請承認イベントが登録されていること
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(application.getId().getValue()));
		assertThat(eventStream.events()).hasSize(2);
		assertThat(eventStream.events().get(0)).isInstanceOf(HolidayApplicationApplied.class);
		assertThat(eventStream.events().get(1)).isInstanceOf(HolidayApplicationRejected.class);

		//休暇申請がイベントストリームから再現できること
		HolidayApplication saved = holidayApplicationRepository.findById(application.getId());
		assertThat(saved.getId()).isEqualTo(application.getId());
		assertThat(saved.getKindOfHoliday()).isEqualTo(application.getKindOfHoliday());
		assertThat(saved.getHolidayType()).isEqualTo(application.getHolidayType());
		assertThat(saved.getStatus()).isEqualTo(application.getStatus());
		assertThat(saved.getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(saved.getApplicantId()).isEqualTo(application.getApplicantId());
		assertThat(saved.getApproverId()).isEqualTo(application.getApproverId());

		//クエリサービスで休暇申請が取得できること
		List<HolidayApplicationDto> applications = queryService.findAll();
		assertThat(applications).hasSize(1);
		assertThat(applications.get(0).getId()).isEqualTo(application.getId().getValue());
		assertThat(applications.get(0).getKind()).isEqualTo(application.getKindOfHoliday().name());
		assertThat(applications.get(0).getType()).isEqualTo(application.getHolidayType().name());
		assertThat(applications.get(0).getStatus()).isEqualTo(application.getStatus().name());
		assertThat(applications.get(0).getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(applications.get(0).getAplicantId()).isEqualTo(application.getApplicantId().getValue());
		assertThat(applications.get(0).getApproverId()).isEqualTo(application.getApproverId().getValue());
	}

	@Test
	public void cancelHolidayApplication() {
		//休暇申請時にステータスがCANCELEDになること
		@SuppressWarnings("deprecation")
		Date applyDate = new Date(2020, 10, 10);
		User applicant = new User(new UserId("test"), new ArrayList<User>());

		HolidayApplication application = service.apply(KindOfHoliday.PAYED_LEAVE, HolidayType.FULL_OFF, applyDate,
				applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);
		application = service.cancel(application.getId(), applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.CANCELED);

		//休暇申請申請イベントと休暇申請承認イベントが登録されていること
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(application.getId().getValue()));
		assertThat(eventStream.events()).hasSize(2);
		assertThat(eventStream.events().get(0)).isInstanceOf(HolidayApplicationApplied.class);
		assertThat(eventStream.events().get(1)).isInstanceOf(HolidayApplicationCanceled.class);

		//休暇申請がイベントストリームから再現できること
		HolidayApplication saved = holidayApplicationRepository.findById(application.getId());
		assertThat(saved.getId()).isEqualTo(application.getId());
		assertThat(saved.getKindOfHoliday()).isEqualTo(application.getKindOfHoliday());
		assertThat(saved.getHolidayType()).isEqualTo(application.getHolidayType());
		assertThat(saved.getStatus()).isEqualTo(application.getStatus());
		assertThat(saved.getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(saved.getApplicantId()).isEqualTo(application.getApplicantId());

		//クエリサービスで休暇申請が取得できること
		List<HolidayApplicationDto> applications = queryService.findAll();
		assertThat(applications).hasSize(1);
		assertThat(applications.get(0).getId()).isEqualTo(application.getId().getValue());
		assertThat(applications.get(0).getKind()).isEqualTo(application.getKindOfHoliday().name());
		assertThat(applications.get(0).getType()).isEqualTo(application.getHolidayType().name());
		assertThat(applications.get(0).getStatus()).isEqualTo(application.getStatus().name());
		assertThat(applications.get(0).getDate().getTime()).isEqualTo(application.getDate().getTime());
		assertThat(applications.get(0).getAplicantId()).isEqualTo(application.getApplicantId().getValue());
	}

	@Test
	public void approveNoAuth() throws Exception {
		@SuppressWarnings("deprecation")
		Date applyDate = new Date(2020, 10, 10);
		User applicant = new User(new UserId("test"), new ArrayList<User>());
		User approver = new User(new UserId("admin"), new ArrayList<User>());

		HolidayApplication application = service.apply(KindOfHoliday.PAYED_LEAVE, HolidayType.FULL_OFF, applyDate,
				applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);
		try {
			application = service.approve(application.getId(), approver);
			fail("AuthenticationException is not occured.");
		} catch (AuthenticationException e) {

		}
	}

	@Test
	public void rejectoAuth() throws Exception {
		@SuppressWarnings("deprecation")
		Date applyDate = new Date(2020, 10, 10);
		User applicant = new User(new UserId("test"), new ArrayList<User>());
		User approver = new User(new UserId("admin"), new ArrayList<User>());

		HolidayApplication application = service.apply(KindOfHoliday.PAYED_LEAVE, HolidayType.FULL_OFF, applyDate,
				applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);
		try {
			application = service.reject(application.getId(), approver);
			fail("AuthenticationException is not occured.");
		} catch (AuthenticationException e) {

		}
	}

	@Test
	public void cancelNoAuth() throws Exception {
		@SuppressWarnings("deprecation")
		Date applyDate = new Date(2020, 10, 10);
		User applicant = new User(new UserId("test"), new ArrayList<User>());
		User approver = new User(new UserId("admin"), new ArrayList<User>());

		HolidayApplication application = service.apply(KindOfHoliday.PAYED_LEAVE, HolidayType.FULL_OFF, applyDate,
				applicant);
		assertThat(application.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);
		try {
			application = service.cancel(application.getId(), approver);
			fail("AuthenticationException is not occured.");
		} catch (AuthenticationException e) {

		}
	}

}
