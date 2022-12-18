package jp.co.genuine.hm.rest.response.holiday;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HolidayAlertResponse {

    private Double days;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date deadline;

    public HolidayAlertResponse() {
    }

    public boolean isEmpty() {
        return days == null;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
