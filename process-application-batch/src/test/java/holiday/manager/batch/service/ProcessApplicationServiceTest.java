package holiday.manager.batch.service;

import static org.assertj.core.api.Assertions.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import holiday.manager.TestApplication;
import holiday.manager.application.service.holiday.application.HolidayApplicationService;
import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationRepository;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.User;
import holiday.manager.domain.model.user.UserId;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProcessApplicationServiceTest {

	@Autowired
	private HolidayListService holidayListService;

	@Autowired
	private HolidayApplicationService holidayApplicationService;

	@Autowired
	private ProcessApplicationService processApplicationService;

	@Autowired
	private HolidayApplicationRepository holidayApplicationRepository;

	@Test
	public void process_ok() {
		User user = new User(new UserId(100), new ArrayList<>());
		List<User> managedMembers = new ArrayList<>();
		managedMembers.add(user);
		User manager = new User(new UserId(1000), managedMembers);

		Date grantedDate = Date
				.from(ZonedDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date expirationDate = Date
				.from(ZonedDateTime.now().toLocalDate().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

		holidayListService.createHolidayList(user.getId());
		holidayListService.grantHoliday(user.getId(), KindOfHoliday.PAYED_LEAVE, 10.0, grantedDate, expirationDate);

		HolidayApplication application = holidayApplicationService.apply(KindOfHoliday.PAYED_LEAVE,
				HolidayType.FULL_OFF, new Date(), user);
		holidayApplicationService.approve(application.getId(), manager);

		processApplicationService.process();

		HolidayApplication result = holidayApplicationRepository.findById(application.getId());
		assertThat(result.getStatus()).isEqualTo(HolidayApplicationStatus.PROCESSED);

	}

	@Test
	public void process_failed() {
		User user = new User(new UserId(200), new ArrayList<>());
		List<User> managedMembers = new ArrayList<>();
		managedMembers.add(user);
		User manager = new User(new UserId(1000), managedMembers);

		Date grantedDate = Date
				.from(ZonedDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date expirationDate = Date
				.from(ZonedDateTime.now().toLocalDate().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

		holidayListService.createHolidayList(user.getId());
		HolidayList list = holidayListService.grantHoliday(user.getId(), KindOfHoliday.PAYED_LEAVE, 0.5, grantedDate, expirationDate);

		HolidayApplication application = holidayApplicationService.apply(KindOfHoliday.PAYED_LEAVE,
				HolidayType.FULL_OFF, new Date(), user);
		holidayApplicationService.approve(application.getId(), manager);

		processApplicationService.process();

		HolidayApplication result = holidayApplicationRepository.findById(application.getId());
		assertThat(result.getStatus()).isEqualTo(HolidayApplicationStatus.PROCESS_FAILED);
	}

	@Test
	public void not_process_applying_application() {
		User user = new User(new UserId(300), new ArrayList<>());

		Date grantedDate = Date
				.from(ZonedDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date expirationDate = Date
				.from(ZonedDateTime.now().toLocalDate().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

		holidayListService.createHolidayList(user.getId());
		holidayListService.grantHoliday(user.getId(), KindOfHoliday.PAYED_LEAVE, 10.0, grantedDate, expirationDate);

		HolidayApplication application = holidayApplicationService.apply(KindOfHoliday.PAYED_LEAVE,
				HolidayType.FULL_OFF, new Date(), user);

		processApplicationService.process();

		HolidayApplication result = holidayApplicationRepository.findById(application.getId());
		assertThat(result.getStatus()).isEqualTo(HolidayApplicationStatus.APPLYING);
	}

	@Test
	public void not_process_cancle_application() {
		User user = new User(new UserId(400), new ArrayList<>());

		Date grantedDate = Date
				.from(ZonedDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date expirationDate = Date
				.from(ZonedDateTime.now().toLocalDate().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

		holidayListService.createHolidayList(user.getId());
		holidayListService.grantHoliday(user.getId(), KindOfHoliday.PAYED_LEAVE, 10.0, grantedDate, expirationDate);

		HolidayApplication application = holidayApplicationService.apply(KindOfHoliday.PAYED_LEAVE,
				HolidayType.FULL_OFF, new Date(), user);
		holidayApplicationService.cancel(application.getId(), user);
		processApplicationService.process();

		HolidayApplication result = holidayApplicationRepository.findById(application.getId());
		assertThat(result.getStatus()).isEqualTo(HolidayApplicationStatus.CANCELED);
	}

	@Test
	public void not_process_future_application() {
		User user = new User(new UserId(500), new ArrayList<>());
		List<User> managedMembers = new ArrayList<>();
		managedMembers.add(user);
		User manager = new User(new UserId(1000), managedMembers);

		Date grantedDate = Date
				.from(ZonedDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date expirationDate = Date
				.from(ZonedDateTime.now().toLocalDate().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date applicationDate = Date
				.from(ZonedDateTime.now().toLocalDate().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

		holidayListService.createHolidayList(user.getId());
		holidayListService.grantHoliday(user.getId(), KindOfHoliday.PAYED_LEAVE, 10.0, grantedDate, expirationDate);

		HolidayApplication application = holidayApplicationService.apply(KindOfHoliday.PAYED_LEAVE,
				HolidayType.FULL_OFF, applicationDate, user);
		holidayApplicationService.approve(application.getId(), manager);

		processApplicationService.process();

		HolidayApplication result = holidayApplicationRepository.findById(application.getId());
		assertThat(result.getStatus()).isEqualTo(HolidayApplicationStatus.APPROVED);

	}

}
