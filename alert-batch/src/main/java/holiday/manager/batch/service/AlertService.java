package holiday.manager.batch.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.batch.repository.AlertForTakingPaidLeaveEntity;
import holiday.manager.batch.repository.AlertForTakingPaidLeaveRepository;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.repository.repository.UserRepository;

@Service
public class AlertService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AlertForTakingPaidLeaveRepository alertForTakingPaidLeaveRepository;

	@Autowired
	private HolidayListService holidayListService;

	public void alert() {
		StreamSupport.stream(userRepository.findAll().spliterator(), false)
				.filter(user -> user.getStatus().equals("ACTIVE"))
				.forEach(user -> {
					HolidayList holidayList = holidayListService.findHolidayList(new UserId(user.getId()));
					if (holidayList == null || holidayList.grantHistory().isEmpty()) {
						return;
					}

					LocalDate today = ZonedDateTime.now().toLocalDate();
					LocalDate criteriaDate = holidayList.grantHistory().get(0).getGrantedDate().toInstant()
							.atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate fromDate = (criteriaDate.withYear(today.getYear()).isAfter(today))
							? criteriaDate.withYear(today.getYear() - 1)
							: criteriaDate.withYear(today.getYear());
					LocalDate deadLineDate = (criteriaDate.withYear(today.getYear()).isAfter(today))
							? criteriaDate.withYear(today.getYear()).minusDays(1)
							: criteriaDate.withYear(today.getYear() + 1).minusDays(1);
					Date from = Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					Date deadLine = Date.from(deadLineDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

					int tookDays = (int) holidayList.takeHistory().stream()
							.filter(history -> !history.getDate().before(from))
							.count();

					if (tookDays >= 5) {
						alertForTakingPaidLeaveRepository.findById(user.getId());
						return;
					}
					Optional<AlertForTakingPaidLeaveEntity> entity = alertForTakingPaidLeaveRepository.findById(user.getId());
					AlertForTakingPaidLeaveEntity alert = (entity.isPresent())? entity.get() : new AlertForTakingPaidLeaveEntity();
					alert.setUserId(user.getId());
					alert.setDays(5 - tookDays);
					alert.setDeadLine(deadLine);
					alert.setUpdateedAt(new Date());
					alertForTakingPaidLeaveRepository.save(alert);
				});
		;

	}
}
