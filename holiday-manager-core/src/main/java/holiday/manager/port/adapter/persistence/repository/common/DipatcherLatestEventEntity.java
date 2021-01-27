package holiday.manager.port.adapter.persistence.repository.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dispatcher_latest_event", schema = "event")
public class DipatcherLatestEventEntity {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "event_id")
	private long eventId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

}
