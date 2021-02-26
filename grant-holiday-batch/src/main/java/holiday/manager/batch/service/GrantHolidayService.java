package holiday.manager.batch.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.batch.repository.UserRepository;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.user.UserId;

@Service
public class GrantHolidayService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HolidayListService holidayListService;

	public String test() {

		userRepository.findAll();
		return "test";
	}

	@Transactional
	public void grant() {
		StreamSupport.stream(userRepository.findAll().spliterator(), false)
				.filter(user -> user.getStatus().equals("ACTIVE"))
				.forEach(user -> {
					LocalDate today = ZonedDateTime.now().toLocalDate();
					LocalDate hireDate = user.getHireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

					if(user.getRule() == null || user.getRule().getRules() == null) {
						return;
					}

					user.getRule().getRules().stream()
							.filter(rule -> {
								return hireDate
										.plusYears(rule.getElapsedYear())
										.plusMonths(rule.getElapsedMonth())
										.plusDays(rule.getElapsedDays())
										.isEqual(today);
							})
							.forEach(rule -> {
								Date expireDate = Date
										.from(today.plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
								holidayListService.grantHoliday(new UserId(user.getId()), KindOfHoliday.PAYED_LEAVE,
										rule.getGrantDays(), new Date(), expireDate);
							});
				});
	}
}
