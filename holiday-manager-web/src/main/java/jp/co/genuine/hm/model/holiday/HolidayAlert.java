package jp.co.genuine.hm.model.holiday;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class HolidayAlert {
    private Double days;
    private Date deadline;

    public HolidayAlert() {
    }

    public HolidayAlert(Double days, Date deadline) {
        this.days = days;
        this.deadline = deadline;
    }

    public boolean isEmpty() {
        return days == null;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public String displayAlert() {
        return deadline + "までに有給休暇をあと" + days + "日取得してください";
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
