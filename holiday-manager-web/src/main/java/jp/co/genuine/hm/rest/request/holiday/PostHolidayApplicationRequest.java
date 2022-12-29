package jp.co.genuine.hm.rest.request.holiday;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostHolidayApplicationRequest {
    private Integer applicantId;
    private String kindOfHoliday;
    private String date;
    private String holidayType;

    public PostHolidayApplicationRequest(Integer applicantId, String kindOfHoliday, String date, String holidayType) {
        this.applicantId = applicantId;
        this.kindOfHoliday = kindOfHoliday;
        this.date = date;
        this.holidayType = holidayType;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getKindOfHoliday() {
        return kindOfHoliday;
    }

    public void setKindOfHoliday(String kindOfHoliday) {
        this.kindOfHoliday = kindOfHoliday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }
}
