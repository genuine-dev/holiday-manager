package holiday.manager.domain.user.alert;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class HolidayAlert {
	private Double days;
	private Date deadline;
	public HolidayAlert() {
	}
	public HolidayAlert(Double days, Date deadline) {
		this.days = days;
		this.deadline = deadline;
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
