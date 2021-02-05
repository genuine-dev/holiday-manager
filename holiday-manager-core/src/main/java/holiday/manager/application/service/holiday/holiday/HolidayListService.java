package holiday.manager.application.service.holiday.holiday;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.holiday.holiday.HolidayListId;
import holiday.manager.domain.model.holiday.holiday.HolidayListRepository;
import holiday.manager.domain.model.user.UserId;

@Service
public class HolidayListService {
	@Autowired
	private HolidayListRepository repository;

	public HolidayList createHolidayList(UserId owner) {
		HolidayList holidayList = new HolidayList(owner);
		repository.save(holidayList);
		return holidayList;
	}

	public HolidayList grantHoliday(UserId owner, KindOfHoliday kind, double days, Date grantedDate,
			Date expirationDate) {
		HolidayList holidayList = repository.findById(new HolidayListId(owner.getValue().toString()));
		holidayList.grant(kind, days, grantedDate, expirationDate);
		repository.save(holidayList);
		return holidayList;

	}

	public HolidayList takeHoliday(UserId owner, KindOfHoliday kind, double days, Date date,
			HolidayApplicationId applicationId) {
		HolidayList holidayList = repository.findById(new HolidayListId(owner.getValue().toString()));
		holidayList.take(kind, days, date, applicationId);
		repository.save(holidayList);
		return holidayList;
	}

	public HolidayList findHolidayList(UserId owner) {
		return  repository.findById(new HolidayListId(owner.getValue().toString()));
	}
}
