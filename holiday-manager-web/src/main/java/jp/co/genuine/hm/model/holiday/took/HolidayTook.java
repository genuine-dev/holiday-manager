package jp.co.genuine.hm.model.holiday.took;

import jp.co.genuine.hm.model.holiday.KindOfHoliday;
import jp.co.genuine.hm.model.holiday.application.HolidayApplicationId;
import jp.co.genuine.hm.model.user.UserId;

import java.util.Date;

public class HolidayTook {
    private String eventId;
    private KindOfHoliday kindOfHoliday;
    private Date date;
    private double days;
    private HolidayApplicationId holidayApplicationId;
    private UserId userId;

    public HolidayTook(String eventId, KindOfHoliday kindOfHoliday, Date date, double days, HolidayApplicationId holidayApplicationId, UserId userId) {
        this.eventId = eventId;
        this.kindOfHoliday = kindOfHoliday;
        this.date = date;
        this.days = days;
        this.holidayApplicationId = holidayApplicationId;
        this.userId = userId;
    }
}
