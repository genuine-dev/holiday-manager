package holiday.manager.batch.service;

import static org.assertj.core.api.Assertions.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import holiday.manager.TestApplication;
import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.batch.repository.AlertForTakingPaidLeaveEntity;
import holiday.manager.batch.repository.AlertForTakingPaidLeaveRepository;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.repository.repository.GrantingPaiedLeaveRuleEntity;
import holiday.manager.repository.repository.UserEntity;
import holiday.manager.repository.repository.UserRepository;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AlertServiceTest {
	@Autowired
	private AlertService alertService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HolidayListService holidayListService;

	@Autowired
	private AlertForTakingPaidLeaveRepository alertForTakingPaidLeaveRepository;

	@Before
	@Transactional
	public void setup() {
		GrantingPaiedLeaveRuleEntity rule = new GrantingPaiedLeaveRuleEntity();
		rule.setName("testRule");
		rule.setCreatedAt(new Date(0));
		rule.setUpdateedAt(new Date(0));

		//有給休暇未消化
		UserEntity user1 = new UserEntity();
		user1.setId(100);
		user1.setStatus("ACTIVE");
		user1.setEmail("testuser1@example.com");
		user1.setName("testUser1");
		user1.setHireDate(Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		user1.setRule(rule);
		user1.setCreatedAt(new Date());
		user1.setUpdateedAt(new Date());

		userRepository.save(user1);
		holidayListService.createHolidayList(new UserId(100));
		holidayListService.grantHoliday(new UserId(100), KindOfHoliday.PAYED_LEAVE, 20, Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(2).plusDays(10).atStartOfDay(ZoneId.systemDefault())
						.toInstant()),
				Date.from(
						ZonedDateTime.now().toLocalDate().minusYears(1).plusDays(10)
								.atStartOfDay(ZoneId.systemDefault())
								.toInstant()));
		holidayListService.grantHoliday(new UserId(100), KindOfHoliday.PAYED_LEAVE, 20, Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(1).plusDays(10).atStartOfDay(ZoneId.systemDefault())
						.toInstant()),
				Date.from(
						ZonedDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault())
								.toInstant()));

		//有給消化(4日)
		UserEntity user2 = new UserEntity();
		user2.setId(200);
		user2.setStatus("ACTIVE");
		user2.setEmail("testuser2@example.com");
		user2.setName("testUser2");
		user2.setHireDate(Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		user2.setRule(rule);
		user2.setCreatedAt(new Date());
		user2.setUpdateedAt(new Date());

		userRepository.save(user2);
		holidayListService.createHolidayList(new UserId(200));
		holidayListService.grantHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 20, Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(2).plusDays(10).atStartOfDay(ZoneId.systemDefault())
						.toInstant()),
				Date.from(
						ZonedDateTime.now().toLocalDate().minusYears(1).plusDays(10)
								.atStartOfDay(ZoneId.systemDefault())
								.toInstant()));
		holidayListService.grantHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 20, Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(1).plusDays(10).atStartOfDay(ZoneId.systemDefault())
						.toInstant()),
				Date.from(
						ZonedDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault())
								.toInstant()));

		holidayListService.takeHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 1, Date
				.from(ZonedDateTime.now().toLocalDate().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				new HolidayApplicationId());
		holidayListService.takeHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 1, Date
				.from(ZonedDateTime.now().toLocalDate().minusDays(9).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				new HolidayApplicationId());
		holidayListService.takeHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 1, Date
				.from(ZonedDateTime.now().toLocalDate().minusDays(8).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				new HolidayApplicationId());
		holidayListService.takeHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 1, Date
				.from(ZonedDateTime.now().toLocalDate().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				new HolidayApplicationId());
		//付与日以前の取得
		holidayListService.takeHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 1, Date
				.from(ZonedDateTime.now().toLocalDate().minusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				new HolidayApplicationId());
	}

	@Test
	public void test() {
		alertService.alert();
		Map<Integer, AlertForTakingPaidLeaveEntity> alertMap = new HashMap<>();
		StreamSupport.stream(alertForTakingPaidLeaveRepository.findAll().spliterator(), false)
				.forEach(alert -> {
					alertMap.put(alert.getUserId(), alert);
				});
		assertThat(alertMap.get(100)).isNotNull();
		assertThat(alertMap.get(100).getDays()).isEqualTo(5);
		assertThat(alertMap.get(200)).isNotNull();
		assertThat(alertMap.get(200).getDays()).isEqualTo(1);

		//有給休暇取得したらアラート消える
		holidayListService.takeHoliday(new UserId(200), KindOfHoliday.PAYED_LEAVE, 1, Date
				.from(ZonedDateTime.now().toLocalDate().minusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				new HolidayApplicationId());
		alertService.alert();
		assertThat(alertForTakingPaidLeaveRepository.findById(200).isPresent()).isFalse();
	}
}
