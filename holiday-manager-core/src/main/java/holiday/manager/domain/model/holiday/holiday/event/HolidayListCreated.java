package holiday.manager.domain.model.holiday.holiday.event;

import java.util.Date;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.holiday.HolidayListId;
import holiday.manager.domain.model.user.UserId;

public class HolidayListCreated implements DomainEvent {
	private int version = 1;
	private Date occurredOn;
	private HolidayListId id;
	private UserId owner;

	@SuppressWarnings("unused")
	private HolidayListCreated() {
	}

	public HolidayListCreated(HolidayListId id, UserId owner) {
		this.version = 1;
		this.occurredOn = new Date();
		this.id = id;
		this.owner = owner;
	}

	public HolidayListId getId() {
		return id;
	}

	public UserId getOwner() {
		return owner;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Date getOccurredOn() {
		return occurredOn;
	}

}
