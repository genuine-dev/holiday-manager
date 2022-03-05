package holiday.manager.domain.holiday.dto;

import java.util.Date;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.user.User;

public class HolidayApplyDTO {
	private User applicant;
	private KindOfHoliday kindOfHoliday;
	private HolidayType holidayType;
	private Date applyDate;
	public HolidayApplyDTO() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public HolidayApplyDTO(User applicant, KindOfHoliday kindOfHoliday, HolidayType holidayType, Date applyDate) {
		super();
		this.applicant = applicant;
		this.kindOfHoliday = kindOfHoliday;
		this.holidayType = holidayType;
		this.applyDate = applyDate;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	public KindOfHoliday getKindOfHoliday() {
		return kindOfHoliday;
	}
	public void setKindOfHoliday(KindOfHoliday kindOfHoliday) {
		this.kindOfHoliday = kindOfHoliday;
	}
	public HolidayType getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(HolidayType holidayType) {
		this.holidayType = holidayType;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
}
