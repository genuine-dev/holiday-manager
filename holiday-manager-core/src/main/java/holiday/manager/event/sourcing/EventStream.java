package holiday.manager.event.sourcing;

import java.util.List;

import holiday.manager.domain.model.DomainEvent;

public interface EventStream {

	public List<DomainEvent> events();

	public int version();
}