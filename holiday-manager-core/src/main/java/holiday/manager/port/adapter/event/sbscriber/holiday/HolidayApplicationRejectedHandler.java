package holiday.manager.port.adapter.event.sbscriber.holiday;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.event.HolidayApplicationRejected;
import holiday.manager.event.sourcing.EventPublisher;
import holiday.manager.event.sourcing.EventSubscriber;
import holiday.manager.port.adapter.persistence.repository.holiday.query.HolidayApplicationEntity;
import holiday.manager.port.adapter.persistence.repository.holiday.query.HolidayApplicationQueryRepository;

@Component
public class HolidayApplicationRejectedHandler implements EventSubscriber<HolidayApplicationRejected> {

	@Autowired
	private EventPublisher publisher;

	@Autowired
	private HolidayApplicationQueryRepository repository;

	@PostConstruct
	public void initialize() {
		publisher.registerSubscriber(this);
	}

	@Override
	public void handleEvent(HolidayApplicationRejected event) {
		HolidayApplicationEntity entity = repository.findById(event.getId().getValue())
				.orElseThrow(() -> new IllegalStateException());
		entity.setStatus(HolidayApplicationStatus.REJECTED.name());
		entity.setApproverId(event.getApproverId().getValue());
		entity.setUpdateedAt(event.getOccurredOn());

		repository.save(entity);
	}

	@Override
	public Class<HolidayApplicationRejected> subscribeToEventType() {
		return HolidayApplicationRejected.class;
	}

}
