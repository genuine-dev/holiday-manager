package holiday.manager.domain.model.holiday.holiday;

import java.util.Date;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.user.UserId;

/**
 * 休暇
 *
 */
public class Holiday {
	private HolidayId id;
	private KindOfHoliday kind;
    private double grantedDays;
	private double days;
	private Date grantedDate;
	private Date expirationDate;
	private UserId owner;

	@SuppressWarnings("unused")
	private Holiday() {
	}

	public Holiday(HolidayId id, KindOfHoliday kind, double days, Date grantedDate, Date expirationDate, UserId owner) {
		super();
		this.id = id;
		this.kind = kind;
        this.grantedDays = days;
		this.days = days;
		this.grantedDate = grantedDate;
		this.expirationDate = expirationDate;
		this.owner = owner;
	}

	public HolidayId getId() {
		return id;
	}

	public KindOfHoliday getKind() {
		return kind;
	}

    public double getGrantedDays() { return grantedDays; }

    public double getDays() {
		return days;
	}

	public Date getGrantedDate() {
		return grantedDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public UserId getOwner() {
		return owner;
	}

	double take(double days) {
		if(days >= this.days) {
			double result = days - this.days;
			this.days = 0;
			return result;
		} else {
			this.days = this.days - days;
			return 0;
		}
	}

}
