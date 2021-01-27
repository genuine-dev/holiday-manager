package holiday.manager.domain.model.holiday.holiday;

import java.util.Date;

import holiday.manager.domain.model.user.UserId;

/**
 * 欠勤
 *
 */
public class Absence {
	private double days;
	private Date date;
	private UserId owner;

	@SuppressWarnings("unused")
	private Absence() {
	}

	public Absence(double days, Date date, UserId owner) {
		super();
		this.days = days;
		this.date = date;
		this.owner = owner;
	}

	public double getDays() {
		return days;
	}

	public Date getDate() {
		return date;
	}

	public UserId getOwner() {
		return owner;
	};

}
