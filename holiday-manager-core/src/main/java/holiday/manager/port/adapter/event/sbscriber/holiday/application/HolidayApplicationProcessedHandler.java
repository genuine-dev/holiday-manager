package holiday.manager.port.adapter.event.sbscriber.holiday.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.event.HolidayApplicationProcessed;
import holiday.manager.event.sourcing.EventPublisher;
import holiday.manager.event.sourcing.EventSubscriber;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationEntity;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationQueryRepository;

@Component
public class HolidayApplicationProcessedHandler implements EventSubscriber<HolidayApplicationProcessed> {

	@Autowired
	private EventPublisher publisher;

	@Autowired
	private HolidayApplicationQueryRepository repository;

	@PostConstruct
	public void initialize() {
		publisher.registerSubscriber(this);
	}

	@Override
	public void handleEvent(HolidayApplicationProcessed event) {
		HolidayApplicationEntity entity = repository.findById(event.getId().getValue())
				.orElseThrow(() -> new IllegalStateException());
		entity.setStatus(HolidayApplicationStatus.PROCESSED.name());
		entity.setUpdatedAt(event.getOccurredOn());

		repository.save(entity);
	}

	@Override
	public Class<HolidayApplicationProcessed> subscribeToEventType() {
		return HolidayApplicationProcessed.class;
	}

}
