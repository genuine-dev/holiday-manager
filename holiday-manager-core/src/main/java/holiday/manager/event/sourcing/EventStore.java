package holiday.manager.event.sourcing;

import java.util.List;

import holiday.manager.domain.model.DomainEvent;

public interface EventStore {

	public void appendWith(EventStreamId streamId, List<DomainEvent> events);

	public List<DispatchableDomainEvent> eventsSince(long lastReceivedEvent);

	public EventStream eventStreamSince(EventStreamId id);

	public EventStream fullEventStreamFor(EventStreamId id);
}