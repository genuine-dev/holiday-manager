package holiday.manager.rest.response.holiday;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetHolidayTakeAlertResponse {
    private Double days;
    private Date deadline;

    public Double getDays() {
        return days;
    }

    public Date getDeadline() {
        return deadline;
    }
}
