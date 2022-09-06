package holiday.manager.domain.model.holiday.holiday.event;

import java.util.Date;
import java.util.UUID;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.holiday.HolidayListId;
import holiday.manager.domain.model.user.UserId;

public class HolidayTook implements DomainEvent {
	private int version = 1;
	private Date occurredOn;
	private HolidayListId id;
	private String eventId;
	private KindOfHoliday kind;
	private Date date;
	private double days;
	private HolidayApplicationId applicationId;
	private UserId owner;


	@SuppressWarnings("unused")
	private HolidayTook() {
	}



	public HolidayTook(HolidayListId id, KindOfHoliday kind, Date date, double days,
			HolidayApplicationId applicationId, UserId owner) {
		this.version = 1;
		this.occurredOn = new Date();
		this.id = id;
		this.eventId = UUID.randomUUID().toString();
		this.kind = kind;
		this.date = date;
		this.days = days;
		this.applicationId = applicationId;
		this.owner = owner;
	}



	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Date getOccurredOn() {
		return occurredOn;
	}

	public String getEventId() { return eventId; }

	public HolidayListId getId() {
		return id;
	}

	public KindOfHoliday getKind() {
		return kind;
	}

	public Date getDate() {
		return date;
	}

	public double getDays() {
		return days;
	}

	public HolidayApplicationId getApplicationId() {
		return applicationId;
	}

	public UserId getOwner() {
		return owner;
	}


}
