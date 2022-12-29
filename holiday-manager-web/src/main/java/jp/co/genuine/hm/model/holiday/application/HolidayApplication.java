package jp.co.genuine.hm.model.holiday.application;

import jp.co.genuine.hm.model.holiday.KindOfHoliday;
import jp.co.genuine.hm.model.user.UserId;

import java.util.Date;

public class HolidayApplication {
    private HolidayApplicationId holidayApplicationId;
    private KindOfHoliday kindOfHoliday;
    private HolidayType holidayType;
    private HolidayApplicationStatus holidayApplicationStatus;
    private Date date;
    private UserId applicantId;

    public HolidayApplication(HolidayApplicationId holidayApplicationId, KindOfHoliday kindOfHoliday, HolidayType holidayType, HolidayApplicationStatus holidayApplicationStatus, Date date, UserId applicantId) {
        this.holidayApplicationId = holidayApplicationId;
        this.kindOfHoliday = kindOfHoliday;
        this.holidayType = holidayType;
        this.holidayApplicationStatus = holidayApplicationStatus;
        this.date = date;
        this.applicantId = applicantId;
    }

    @Deprecated
    public HolidayApplicationId getHolidayApplicationId() {
        return holidayApplicationId;
    }

    @Deprecated
    public Date getDate() {
        return date;
    }

    @Deprecated
    public KindOfHoliday getKindOfHoliday() {
        return kindOfHoliday;
    }

    @Deprecated
    public HolidayType getHolidayType() {
        return holidayType;
    }

    @Deprecated
    public HolidayApplicationStatus getHolidayApplicationStatus() {
        return holidayApplicationStatus;
    }

    @Deprecated
    public UserId getApplicantId() {
        return applicantId;
    }
}
