package holiday.manager.port.adapter.persistence.event.sourcing.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.event.sourcing.DispatchableDomainEvent;
import holiday.manager.event.sourcing.EventNotifiable;
import holiday.manager.event.sourcing.EventStore;
import holiday.manager.event.sourcing.EventStoreException;
import holiday.manager.event.sourcing.EventStream;
import holiday.manager.event.sourcing.EventStreamId;
import holiday.manager.port.adapter.persistence.event.sourcing.DefaultEventStream;
import holiday.manager.port.adapter.persistence.repository.common.EventStoreEntity;
import holiday.manager.port.adapter.persistence.repository.common.EventStoreRepository;

@Component
public class JdbcEventStore implements EventStore {
	@Autowired
	private EventStoreRepository repository;
	@Autowired
	private EventNotifiable eventNotifiable;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void appendWith(EventStreamId streamId, List<DomainEvent> events) {
		int index = 0;
		for (DomainEvent event : events) {
			index++;
			EventStoreEntity eventStore = new EventStoreEntity();
			eventStore.setStreamName(streamId.streamName());
			eventStore.setStreamVersion(streamId.streamVersion() + index);
			eventStore.setType(event.getClass().getName());
			try {
				eventStore.setPayload(objectMapper.writeValueAsString(event));
			} catch (JsonProcessingException e) {
				throw new RuntimeException("write payload value failed.", e);
			}
			eventStore.setCreatedAt(event.getOccurredOn());

			repository.save(eventStore);
		}
		eventNotifiable.notifyDispatchableEvents();
	}

	@Override
	public List<DispatchableDomainEvent> eventsSince(long lastReceivedEvent) {
		List<EventStoreEntity> storedEvents = repository.findByEventIdSince(lastReceivedEvent);

		return storedEvents.stream().map(entity -> {
			try {
				@SuppressWarnings("unchecked")
				Class<DomainEvent> eventClass = (Class<DomainEvent>) Class.forName(entity.getType());
				DomainEvent event = objectMapper.readValue(entity.getPayload(), eventClass);
				return new DispatchableDomainEvent(entity.getId(), event);
			} catch (ClassNotFoundException | IOException e) {
				throw new EventStoreException("event serialize faild. id:" + entity.getId(), e);
			}
		})
				.collect(Collectors.toList());
	}

	@Override
	public EventStream eventStreamSince(EventStreamId id) {
		List<EventStoreEntity> storedEvents = repository.findByStreamNameStreamVestionSince(id.streamName(),
				id.streamVersion());

		EventStream result;
		try {
			result = buildEventStream(storedEvents);
		} catch (ClassNotFoundException | IOException e) {
			throw new EventStoreException("event stream query faild." + id, e);
		}

		return result;
	}

	@Override
	public EventStream fullEventStreamFor(EventStreamId id) {
		List<EventStoreEntity> storedEvents = repository.findByStreamNameStream(id.streamName());

		EventStream result;
		try {
			result = buildEventStream(storedEvents);
		} catch (ClassNotFoundException | IOException e) {
			throw new EventStoreException("event stream query faild." + id, e);
		}

		return result;
	}

	private EventStream buildEventStream(List<EventStoreEntity> storedEvents)
			throws ClassNotFoundException, JsonParseException, JsonMappingException, IOException {
		List<DomainEvent> events = new ArrayList<>();
		int latestVersion = 0;
		for (EventStoreEntity entity : storedEvents) {
			latestVersion = entity.getStreamVersion();
			@SuppressWarnings("unchecked")
			Class<DomainEvent> eventClass = (Class<DomainEvent>) Class.forName(entity.getType());
			DomainEvent event = objectMapper.readValue(entity.getPayload(), eventClass);
			events.add(event);
		}
		EventStream result = new DefaultEventStream(events, latestVersion);
		return result;
	}

}
