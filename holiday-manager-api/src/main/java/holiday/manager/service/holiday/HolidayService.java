package holiday.manager.service.holiday;

import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.rest.request.holiday.PostHolidayGrantRequest;
import holiday.manager.service.user.UserService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class HolidayService {
	private final HolidayListService holidayListService;
	private final UserService userService;

	public HolidayService(HolidayListService holidayListService, UserService userService) {
		this.holidayListService = holidayListService;
		this.userService = userService;
	}

	public Double getHolidayDays(Integer userId, String kind) {
		UserId owner = new UserId(userId);

		HolidayList holidayList = holidayListService.findHolidayList(owner);

		KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(kind);
		Date today = Date.from(ZonedDateTime.now().toInstant());

		return holidayList.getDays(kindOfHoliday, today);
	}

	public void postHolidayGrant(PostHolidayGrantRequest request) {
		UserId userId = new UserId( request.getUserId());
		KindOfHoliday kindOfHoliday = request.getKindOfHoliday();
		double days = request.getDays();
		Date grantedDate = request.getGrantedDate();
		Date expirationDate = request.getExpirationDate();

		holidayListService.grantHoliday(userId, kindOfHoliday, days, grantedDate, expirationDate);
	}

	public HolidayAlert getHolidayAlert(Integer userId) {
		return userService.findHolidayAlert(new holiday.manager.domain.user.UserId(userId));
	}
}
