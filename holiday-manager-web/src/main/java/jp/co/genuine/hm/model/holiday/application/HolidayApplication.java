package jp.co.genuine.hm.model.holiday.application;

import jp.co.genuine.hm.model.holiday.KindOfHoliday;
import jp.co.genuine.hm.model.user.UserId;

import java.util.Date;

public class HolidayApplication {
    private HolidayApplicationId holidayApplicationId;
    private KindOfHoliday kindOfHoliday;
    private HolidayType holidayType;
    private Date date;
    private UserId applicantId;
    public HolidayApplication(HolidayApplicationId holidayApplicationId, KindOfHoliday kindOfHoliday, HolidayType holidayType, Date date, UserId applicantId) {
        this.holidayApplicationId = holidayApplicationId;
        this.kindOfHoliday = kindOfHoliday;
        this.holidayType = holidayType;
        this.date = date;
        this.applicantId = applicantId;
    }
}
