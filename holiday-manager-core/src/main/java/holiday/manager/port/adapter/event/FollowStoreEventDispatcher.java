package holiday.manager.port.adapter.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holiday.manager.event.sourcing.DispatchableDomainEvent;
import holiday.manager.event.sourcing.EventDispatcher;
import holiday.manager.event.sourcing.EventNotifiable;
import holiday.manager.event.sourcing.EventPublisher;
import holiday.manager.event.sourcing.EventStore;
import holiday.manager.port.adapter.persistence.repository.common.DipatcherLatestEventEntity;
import holiday.manager.port.adapter.persistence.repository.common.DipatcherLatestEventRepository;

@Component
public class FollowStoreEventDispatcher implements EventNotifiable, EventDispatcher {

	@Autowired
	private DipatcherLatestEventRepository dipatcherLatestEventRepository;

	@Autowired
	private EventStore eventStore;

	@Autowired
	private EventPublisher publisher;

	@Override
	public void notifyDispatchableEvents() {
		Optional<DipatcherLatestEventEntity> latestEvent = dipatcherLatestEventRepository.findById("event");
		long eventId = (latestEvent.isPresent()) ? latestEvent.get().getEventId() : 0;

		List<DispatchableDomainEvent> undispatchedEvents = eventStore.eventsSince(eventId);
		undispatchedEvents.forEach(this::dispatch);
		if (!undispatchedEvents.isEmpty()) {
			long latestEventid = undispatchedEvents.get(undispatchedEvents.size() - 1).eventId();
			DipatcherLatestEventEntity entity = new DipatcherLatestEventEntity();
			entity.setId("event");
			entity.setEventId(latestEventid);
			dipatcherLatestEventRepository.save(entity);
		}
	}

	@Override
	public void dispatch(DispatchableDomainEvent event) {
		publisher.publish(event.domainEvent());
	}
}
