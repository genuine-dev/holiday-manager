package holiday.manager.batch.service;

import static org.assertj.core.api.Assertions.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import holiday.manager.TestApplication;
import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.repository.repository.GrantingPaiedLeaveRuleDetailEntity;
import holiday.manager.repository.repository.GrantingPaiedLeaveRuleEntity;
import holiday.manager.repository.repository.GrantingPaiedLeaveRuleRepository;
import holiday.manager.repository.repository.PaidLeaveGrantHistoryEntiry;
import holiday.manager.repository.repository.PaidLeaveGrantHistoryEntiry.ID;
import holiday.manager.repository.repository.PaidLeaveGrantHistoryRepository;
import holiday.manager.repository.repository.UserEntity;
import holiday.manager.repository.repository.UserRepository;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class GrantHolidayServiceTest {
	@Autowired
	private GrantingPaiedLeaveRuleRepository grantingPaiedLeaveRuleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaidLeaveGrantHistoryRepository paidLeaveGrantHistoryRepository;

	@Autowired
	private GrantHolidayService grantHolidayService;

	@Autowired
	private HolidayListService holidayListService;

	@Before
	@Transactional
	public void setup() {
		GrantingPaiedLeaveRuleEntity rule = new GrantingPaiedLeaveRuleEntity();
		rule.setName("testRule");
		rule.setCreatedAt(new Date(0));
		rule.setUpdateedAt(new Date(0));

		rule = grantingPaiedLeaveRuleRepository.save(rule);

		GrantingPaiedLeaveRuleDetailEntity detail1 = new GrantingPaiedLeaveRuleDetailEntity();
		detail1.setId(1);
		detail1.setRule(rule);
		detail1.setGrantDays(3);
		detail1.setElapsedYear(0);
		detail1.setElapsedMonth(0);
		detail1.setElapsedDays(0);
		detail1.setCreatedAt(new Date(0));
		detail1.setUpdatedAt(new Date(0));

		GrantingPaiedLeaveRuleDetailEntity detail2 = new GrantingPaiedLeaveRuleDetailEntity();
		detail2.setId(2);
		detail2.setRule(rule);
		detail2.setGrantDays(7);
		detail2.setElapsedYear(0);
		detail2.setElapsedMonth(6);
		detail2.setElapsedDays(0);
		detail2.setCreatedAt(new Date(0));
		detail2.setUpdatedAt(new Date(0));

		GrantingPaiedLeaveRuleDetailEntity detail3 = new GrantingPaiedLeaveRuleDetailEntity();
		detail3.setId(3);
		detail3.setRule(rule);
		detail3.setGrantDays(11);
		detail3.setElapsedYear(1);
		detail3.setElapsedMonth(6);
		detail3.setElapsedDays(0);
		detail3.setCreatedAt(new Date(0));
		detail3.setUpdatedAt(new Date(0));

		GrantingPaiedLeaveRuleDetailEntity detail4 = new GrantingPaiedLeaveRuleDetailEntity();
		detail4.setId(4);
		detail4.setRule(rule);
		detail4.setGrantDays(12);
		detail4.setElapsedYear(2);
		detail4.setElapsedMonth(6);
		detail4.setElapsedDays(0);
		detail4.setCreatedAt(new Date(0));
		detail4.setUpdatedAt(new Date(0));

		GrantingPaiedLeaveRuleDetailEntity detail5 = new GrantingPaiedLeaveRuleDetailEntity();
		detail5.setId(5);
		detail5.setRule(rule);
		detail5.setGrantDays(100);
		detail5.setElapsedYear(0);
		detail5.setElapsedMonth(0);
		detail5.setElapsedDays(0);
		detail5.setCreatedAt(new Date());
		detail5.setUpdatedAt(new Date());


		rule.setRules(Arrays.asList(detail1, detail2, detail3, detail4, detail5));

		rule = grantingPaiedLeaveRuleRepository.save(rule);


		//入社半年
		UserEntity user1 = new UserEntity();
		user1.setId(100);
		user1.setStatus("ACTIVE");
		user1.setEmail("testuser1@example.com");
		user1.setName("testUser1");
		user1.setHireDate(Date.from(
				ZonedDateTime.now().toLocalDate().minusMonths(6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		user1.setRule(rule);
		user1.setCreatedAt(new Date());
		user1.setUpdateedAt(new Date());

		userRepository.save(user1);
		holidayListService.createHolidayList(new UserId(100));

		//入社1年
		UserEntity user2 = new UserEntity();
		user2.setId(200);
		user2.setStatus("ACTIVE");
		user2.setEmail("testuser1@example.com");
		user2.setName("testUser2");
		user2.setHireDate(Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		user2.setRule(rule);
		user2.setCreatedAt(new Date());
		user2.setUpdateedAt(new Date());

		userRepository.save(user2);
		holidayListService.createHolidayList(new UserId(200));

		//入社2年6ヶ月
		UserEntity user3 = new UserEntity();
		user3.setId(300);
		user3.setStatus("ACTIVE");
		user3.setEmail("testuser3@example.com");
		user3.setName("testUser3");
		user3.setHireDate(Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(2).minusMonths(6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		user3.setRule(rule);
		user3.setCreatedAt(new Date());
		user3.setUpdateedAt(new Date());

		userRepository.save(user3);
		holidayListService.createHolidayList(new UserId(300));

		//入社1年6ヶ月退職
		UserEntity user4 = new UserEntity();
		user4.setId(400);
		user4.setStatus("RETIRED");
		user4.setEmail("testuser4@example.com");
		user4.setName("testUser4");
		user4.setHireDate(Date.from(
				ZonedDateTime.now().toLocalDate().minusYears(2).minusMonths(6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		user4.setRule(rule);
		user4.setCreatedAt(new Date());
		user4.setUpdateedAt(new Date());

		userRepository.save(user4);
		holidayListService.createHolidayList(new UserId(400));


		PaidLeaveGrantHistoryEntiry history1 = new PaidLeaveGrantHistoryEntiry(new ID(100, 1), new Date());
		PaidLeaveGrantHistoryEntiry history2 = new PaidLeaveGrantHistoryEntiry(new ID(300, 1), new Date());
		PaidLeaveGrantHistoryEntiry history3 = new PaidLeaveGrantHistoryEntiry(new ID(300, 2), new Date());
		PaidLeaveGrantHistoryEntiry history4 = new PaidLeaveGrantHistoryEntiry(new ID(300, 3), new Date());

		paidLeaveGrantHistoryRepository.save(history1);
		paidLeaveGrantHistoryRepository.save(history2);
		paidLeaveGrantHistoryRepository.save(history3);
		paidLeaveGrantHistoryRepository.save(history4);

	}

	@Test
	public void test() {
		grantHolidayService.grant();

		HolidayList holidayList1 = holidayListService.findHolidayList(new UserId(100));
		assertThat(holidayList1.getDays(KindOfHoliday.PAYED_LEAVE, new Date())).isEqualTo(7.0);

		HolidayList holidayList2 = holidayListService.findHolidayList(new UserId(200));
		assertThat(holidayList2.getDays(KindOfHoliday.PAYED_LEAVE, new Date())).isEqualTo(10.0);

		HolidayList holidayList3 = holidayListService.findHolidayList(new UserId(300));
		assertThat(holidayList3.getDays(KindOfHoliday.PAYED_LEAVE, new Date())).isEqualTo(12.0);

		HolidayList holidayList4 = holidayListService.findHolidayList(new UserId(400));
		assertThat(holidayList4.getDays(KindOfHoliday.PAYED_LEAVE, new Date())).isEqualTo(0.0);

		assertThat(paidLeaveGrantHistoryRepository.findByIdUserId(200)).isNotEmpty();
		assertThat(paidLeaveGrantHistoryRepository.findByIdUserId(400)).isEmpty();
	}
}

