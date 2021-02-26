package holiday.manager.domain.model.holiday.application.event;

import java.util.Date;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;

public class HolidayApplicationProcessFailed implements DomainEvent {
	private int version = 1;

	private Date occurredOn;

	private HolidayApplicationId id;

	@SuppressWarnings("unused")
	private HolidayApplicationProcessFailed() {
	}

	public HolidayApplicationProcessFailed(HolidayApplicationId id) {
		super();
		this.version = 1;
		this.occurredOn = new Date();
		this.id = id;
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
}
