package holiday.manager.port.adapter.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import holiday.manager.domain.model.DomainEvent;
import holiday.manager.event.sourcing.EventPublisher;
import holiday.manager.event.sourcing.EventSubscriber;

@Component
public class DomainEventPublisher implements EventPublisher {
	@SuppressWarnings("rawtypes")
	private List<EventSubscriber> subscribers = new ArrayList<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> void publish(T event) {
		Class<?> eventClass = event.getClass();
		subscribers.stream()
				.filter(subscriber -> subscriber.subscribeToEventType() == eventClass)
				.forEach(subscriber -> subscriber.handleEvent(event));
	}

	@Override
	public <T> void publishAll(List<T> event) {
		event.stream().forEach(this::publish);
	}

	@Override
	public void registerSubscriber(EventSubscriber<? extends DomainEvent> subscriber) {
		subscribers.add(subscriber);
	}

}
