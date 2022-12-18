package jp.co.genuine.hm.rest.response.holiday;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HolidayTookResponse {
    private String eventId;
    private String kind;
    private Date date;
    private double days;
    private String applicationId;
    private int userId;

    public HolidayTookResponse(String eventId, String kind, Date date, double days, String applicationId, int userId) {
        this.eventId = eventId;
        this.kind = kind;
        this.date = date;
        this.days = days;
        this.applicationId = applicationId;
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getKind() {
        return kind;
    }

    public Date getDate() {
        return date;
    }

    public double getDays() {
        return days;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public int getUserId() {
        return userId;
    }
}
