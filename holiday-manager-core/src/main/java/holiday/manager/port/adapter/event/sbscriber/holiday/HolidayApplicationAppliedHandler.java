package holiday.manager.port.adapter.event.sbscriber.holiday;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holiday.manager.domain.model.holiday.event.HolidayApplicationApplied;
import holiday.manager.event.sourcing.EventPublisher;
import holiday.manager.event.sourcing.EventSubscriber;
import holiday.manager.port.adapter.persistence.repository.holiday.query.HolidayApplicationEntity;
import holiday.manager.port.adapter.persistence.repository.holiday.query.HolidayApplicationQueryRepository;

@Component
public class HolidayApplicationAppliedHandler implements EventSubscriber<HolidayApplicationApplied> {

	@Autowired
	private EventPublisher publisher;

	@Autowired
	private HolidayApplicationQueryRepository repository;

	@PostConstruct
	public void initialize() {
		publisher.registerSubscriber(this);
	}

	@Override
	public void handleEvent(HolidayApplicationApplied event) {
		HolidayApplicationEntity entity = new HolidayApplicationEntity();
		entity.setId(event.getId().getValue());
		entity.setKind(event.getKindOfHoliday().name());
		entity.setType(event.getHolidayType().name());
		entity.setDate(event.getDate());
		entity.setStatus(event.getStatus().name());
		entity.setAplicantId(event.getApplicantId().getValue());
		entity.setCreatedAt(event.getOccurredOn());

		repository.save(entity);
	}

	@Override
	public Class<HolidayApplicationApplied> subscribeToEventType() {
		return HolidayApplicationApplied.class;
	}

}
