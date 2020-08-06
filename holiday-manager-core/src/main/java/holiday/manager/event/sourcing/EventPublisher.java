package holiday.manager.event.sourcing;

import java.util.List;

import holiday.manager.domain.model.DomainEvent;

public interface EventPublisher {
	public <T> void publish(T event);

	public <T> void publishAll(List<T> event);

	public void registerSubscriber(EventSubscriber<? extends DomainEvent> subscriber);
}
