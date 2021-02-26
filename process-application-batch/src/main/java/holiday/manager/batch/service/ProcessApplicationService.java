package holiday.manager.batch.service;

import java.util.Date;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.application.service.holiday.application.HolidayApplicationService;
import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationQueryRepository;

@Service
public class ProcessApplicationService {

	private Logger logger = LoggerFactory.getLogger(ProcessApplicationService.class);

	@Autowired
	private HolidayApplicationQueryRepository queryRepository;

	@Autowired
	private HolidayListService holidayListService;

	@Autowired
	private HolidayApplicationService holidayApplicationService;

	public void process() {
		StreamSupport
				.stream(queryRepository.findByStatus(HolidayApplicationStatus.APPROVED.toString()).spliterator(), false)
				.filter(application -> application.getDate().before(new Date()))
				.forEach(application -> {
					UserId owner = new UserId(application.getAplicantId());
					KindOfHoliday kind = KindOfHoliday.valueOf(application.getKind());
					double days = (HolidayType.valueOf(application.getType()) == HolidayType.FULL_OFF) ? 1.0 : 0.5;
					Date date = application.getDate();
					HolidayApplicationId applicationId = new HolidayApplicationId(application.getId());

					try {
						holidayListService.takeHoliday(owner, kind, days, date, applicationId);
						holidayApplicationService.process(applicationId);
					} catch (IllegalStateException e) {
						logger.warn(e.getMessage() + "user: " + application.getAplicantId() + ", application: "
								+ application.getAplicantId());
						holidayApplicationService.processFail(applicationId);
					}
				});
	}
}
