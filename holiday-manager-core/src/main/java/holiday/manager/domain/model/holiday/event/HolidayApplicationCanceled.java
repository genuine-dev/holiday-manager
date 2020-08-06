package holiday.manager.domain.model.holiday.event;

import java.util.Date;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.HolidayApplicationId;
import holiday.manager.domain.model.user.UserId;

public class HolidayApplicationCanceled implements DomainEvent {
	private int version = 1;

	private Date occurredOn;

	private HolidayApplicationId id;

	private UserId applicantId;

	@SuppressWarnings("unused")
	private HolidayApplicationCanceled() {
	}

	public HolidayApplicationCanceled(HolidayApplicationId id, UserId applicantId) {
		super();
		this.version = 1;
		this.occurredOn = new Date();
		this.id = id;
		this.applicantId = applicantId;
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

	public UserId getApplicantId() {
		return applicantId;
	}

}
