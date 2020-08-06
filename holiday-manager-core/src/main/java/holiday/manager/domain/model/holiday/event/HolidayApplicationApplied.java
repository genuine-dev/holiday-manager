package holiday.manager.domain.model.holiday.event;

import java.util.Date;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.HolidayApplicationId;
import holiday.manager.domain.model.holiday.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.HolidayType;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.user.UserId;

public class HolidayApplicationApplied implements DomainEvent {
	private int version = 1;

	private  Date occurredOn;

	private  HolidayApplicationId id;

	private  KindOfHoliday kindOfHoliday;

	private  HolidayType holidayType;

	private  Date date;

	private  HolidayApplicationStatus status;

	private  UserId applicantId;

	@SuppressWarnings("unused")
	private HolidayApplicationApplied(){
	}

	public HolidayApplicationApplied(HolidayApplicationId id, KindOfHoliday kindOfHoliday,
			HolidayType holidayType, Date date, UserId applicant) {
		super();
		this.version = 1;
		this.occurredOn = new Date();
		this.id = id;
		this.kindOfHoliday = kindOfHoliday;
		this.holidayType = holidayType;
		this.date = date;
		this.status = HolidayApplicationStatus.APPLYING;
		this.applicantId = applicant;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Date getOccurredOn() {
		return occurredOn;
	}


	public HolidayApplicationId getId() {
		return id;
	}

	public KindOfHoliday getKindOfHoliday() {
		return kindOfHoliday;
	}

	public HolidayType getHolidayType() {
		return holidayType;
	}

	public Date getDate() {
		return date;
	}

	public HolidayApplicationStatus getStatus() {
		return status;
	}

	public UserId getApplicantId() {
		return applicantId;
	}
}
