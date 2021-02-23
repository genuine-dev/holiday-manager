package jp.co.genuine.hm.api.domain.holiday;

import java.util.Date;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.user.UserId;

public class HolidayGrantDTO {
	private UserId userId;
	private KindOfHoliday kindOfHoliday;
	private double days;
	private Date grantedDate;
	private Date expirationDate;
	public HolidayGrantDTO() {
	}
	public HolidayGrantDTO(UserId userId, KindOfHoliday kindOfHoliday, double days, Date grantedDate,
			Date expirationDate) {
		super();
		this.userId = userId;
		this.kindOfHoliday = kindOfHoliday;
		this.days = days;
		this.grantedDate = grantedDate;
		this.expirationDate = expirationDate;
	}
	public UserId getUserId() {
		return userId;
	}
	public void setUserId(UserId userId) {
		this.userId = userId;
	}
	public KindOfHoliday getKindOfHoliday() {
		return kindOfHoliday;
	}
	public void setKindOfHoliday(KindOfHoliday kindOfHoliday) {
		this.kindOfHoliday = kindOfHoliday;
	}
	public double getDays() {
		return days;
	}
	public void setDays(double days) {
		this.days = days;
	}
	public Date getGrantedDate() {
		return grantedDate;
	}
	public void setGrantedDate(Date grantedDate) {
		this.grantedDate = grantedDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
