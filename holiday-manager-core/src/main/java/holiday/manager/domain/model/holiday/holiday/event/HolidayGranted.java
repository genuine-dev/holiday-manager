package holiday.manager.domain.model.holiday.holiday.event;

import java.util.Date;
import java.util.UUID;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.holiday.HolidayListId;
import holiday.manager.domain.model.user.UserId;


public class HolidayGranted implements DomainEvent {
	private int version = 1;
	private Date occurredOn;
	private HolidayListId id;
	private String eventId;
	private KindOfHoliday kind;
	private double days;
	private Date grantedDate;
	private Date expirationDate;
	private UserId owner;

	@SuppressWarnings("unused")
	private HolidayGranted() {
	}

	public HolidayGranted(HolidayListId id, KindOfHoliday kind, double days,
			Date grantedDate, Date expirationDate, UserId owner) {
		this.version = 1;
		this.occurredOn = new Date();
		this.id = id;
		this.eventId = UUID.randomUUID().toString();
		this.kind = kind;
		this.days = days;
		this.grantedDate = grantedDate;
		this.expirationDate = expirationDate;
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

	public HolidayListId getId() {
		return id;
	}

	public KindOfHoliday getKind() {
		return kind;
	}

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

}
