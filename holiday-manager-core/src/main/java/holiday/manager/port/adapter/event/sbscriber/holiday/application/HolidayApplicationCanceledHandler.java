package holiday.manager.port.adapter.event.sbscriber.holiday.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.event.HolidayApplicationCanceled;
import holiday.manager.event.sourcing.EventPublisher;
import holiday.manager.event.sourcing.EventSubscriber;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationEntity;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationQueryRepository;

@Component
public class HolidayApplicationCanceledHandler implements EventSubscriber<HolidayApplicationCanceled> {

	@Autowired
	private EventPublisher publisher;

	@Autowired
	private HolidayApplicationQueryRepository repository;

	@PostConstruct
	public void initialize() {
		publisher.registerSubscriber(this);
	}

	@Override
	public void handleEvent(HolidayApplicationCanceled event) {
		HolidayApplicationEntity entity = repository.findById(event.getId().getValue())
				.orElseThrow(() -> new IllegalStateException());
		entity.setStatus(HolidayApplicationStatus.CANCELED.name());
		entity.setApproverId(event.getApplicantId().getValue());
		entity.setUpdatedAt(event.getOccurredOn());

		repository.save(entity);
	}

	@Override
	public Class<HolidayApplicationCanceled> subscribeToEventType() {
		return HolidayApplicationCanceled.class;
	}

}
