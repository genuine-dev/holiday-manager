package holiday.manager.application.service.holiday.application;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.application.HolidayApplicationRepository;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.user.User;

@Service
public class HolidayApplicationService {
	@Autowired
	private HolidayApplicationRepository repository;

	public HolidayApplication apply(KindOfHoliday kindOfHoliday, HolidayType holidayType, Date date, User applicant) {
		HolidayApplication application = new HolidayApplication(kindOfHoliday, holidayType, date, applicant.getId());
		repository.save(application);

		return application;
	}

	public HolidayApplication approve(HolidayApplicationId id, User approver) {
		HolidayApplication application = repository.findById(id);
		application.approve(approver);
		repository.save(application);

		return application;
	}

	public HolidayApplication reject(HolidayApplicationId id, User approver) {
		HolidayApplication application = repository.findById(id);
		application.reject(approver);
		repository.save(application);

		return application;
	}

	public HolidayApplication cancel(HolidayApplicationId id, User applecant) {
		HolidayApplication application = repository.findById(id);
		application.cancel(applecant);

		repository.save(application);

		return application;

	}

}
