package jp.co.genuine.hm.model.holiday.application.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class HolidayApplicationApplyForm {
    @NotBlank
    @Length(max = 20)
    private String kindOfHoliday;

    @NotBlank
    @Length(max = 20)
    private String holidayType;

    @NotBlank
    private String date;

    @NotBlank
    private String status;

    public String getKindOfHoliday() {
        return kindOfHoliday;
    }

    public void setKindOfHoliday(String kindOfHoliday) {
        this.kindOfHoliday = kindOfHoliday;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
