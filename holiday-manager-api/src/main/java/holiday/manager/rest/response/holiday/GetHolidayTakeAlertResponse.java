package holiday.manager.rest.response.holiday;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GetHolidayTakeAlertResponse {
	private Double days;
	private Date deadline;
}
