package holiday.manager.port.adapter.persistence.event.sourcing;

import java.util.List;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.event.sourcing.EventStream;

public class DefaultEventStream implements EventStream {

	private List<DomainEvent> events;
	private int version;

	public DefaultEventStream(List<DomainEvent> events, int version) {
		super();

		this.setEvents(events);
		this.setVersion(version);
	}

	@Override
	public List<DomainEvent> events() {
		return this.events;
	}

	@Override
	public int version() {
		return this.version;
	}

	private void setEvents(List<DomainEvent> events) {
		this.events = events;
	}

	private void setVersion(int version) {
		this.version = version;
	}
}
