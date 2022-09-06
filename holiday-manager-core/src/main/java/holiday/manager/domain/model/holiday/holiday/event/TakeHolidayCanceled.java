package holiday.manager.domain.model.holiday.holiday.event;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.holiday.HolidayListId;

import java.util.Date;

public class TakeHolidayCanceled implements DomainEvent {
    private int version = 1;
    private Date occurredOn;
    private HolidayListId id;
    private String targetEventId;

    private TakeHolidayCanceled() {
    }

    public TakeHolidayCanceled(HolidayListId id, String targetEventId) {
        this.version = 1;
        this.occurredOn = new Date();
        this.id = id;
        this.targetEventId = targetEventId;
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

    public String getTargetEventId() {
        return targetEventId;
    }
}
