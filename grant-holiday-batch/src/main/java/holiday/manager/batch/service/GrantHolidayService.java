package holiday.manager.batch.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.repository.repository.PaidLeaveGrantHistoryEntiry;
import holiday.manager.repository.repository.PaidLeaveGrantHistoryEntiry.ID;
import holiday.manager.repository.repository.PaidLeaveGrantHistoryRepository;
import holiday.manager.repository.repository.UserRepository;

@Service
public class GrantHolidayService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaidLeaveGrantHistoryRepository paidLeaveGrantHistoryRepository;

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
					Set<Integer> ruleIdSet = StreamSupport
							.stream(paidLeaveGrantHistoryRepository.findByIdUserId(user.getId()).spliterator(), false)
							.map(entity -> entity.getId().getRuleDetailId())
							.collect(Collectors.toSet());

					if (user.getRule() == null || user.getRule().getRules() == null) {
						return;
					}

					user.getRule().getRules().stream()
							.filter(rule -> {
								LocalDate targetDate = hireDate
										.plusYears(rule.getElapsedYear())
										.plusMonths(rule.getElapsedMonth())
										.plusDays(rule.getElapsedDays());

								//付与日が過去かつまだ付与していないかつ作成日が付与日より前のルール
								return !targetDate.isAfter(today) &&
										!ruleIdSet.contains(rule.getId()) &&
										targetDate
												.isAfter(rule.getCreatedAt().toInstant().atZone(ZoneId.systemDefault())
														.toLocalDate());
							})
							.forEach(rule -> {
								LocalDate targetDate = hireDate
										.plusYears(rule.getElapsedYear())
										.plusMonths(rule.getElapsedMonth())
										.plusDays(rule.getElapsedDays());
								Date expireDate = Date
										.from(targetDate.plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
								Date grantedDate = Date.from(targetDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
								//有給休暇付与
								holidayListService.grantHoliday(new UserId(user.getId()), KindOfHoliday.PAYED_LEAVE,
										rule.getGrantDays(),
										grantedDate,
										expireDate);

								//有給休暇付与履歴作成
								PaidLeaveGrantHistoryEntiry history = new PaidLeaveGrantHistoryEntiry();
								ID id = new ID(user.getId(), rule.getId());
								history.setId(id);
								history.setCreatedAt(new Date());
								paidLeaveGrantHistoryRepository.save(history);
							});
				});
	}
}
