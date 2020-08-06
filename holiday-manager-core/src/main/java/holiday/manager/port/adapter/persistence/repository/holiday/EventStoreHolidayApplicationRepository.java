package holiday.manager.port.adapter.persistence.repository.holiday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import holiday.manager.domain.model.holiday.HolidayApplication;
import holiday.manager.domain.model.holiday.HolidayApplicationId;
import holiday.manager.domain.model.holiday.HolidayApplicationRepository;
import holiday.manager.event.sourcing.EventStore;
import holiday.manager.event.sourcing.EventStream;
import holiday.manager.event.sourcing.EventStreamId;

@Repository
public class EventStoreHolidayApplicationRepository implements HolidayApplicationRepository {

	@Autowired
	private EventStore eventStore;

	@Override
	public HolidayApplication findById(HolidayApplicationId id) {
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(id.getValue()));

		return new HolidayApplication(eventStream.events(), eventStream.version());
	}

	@Override
	public void save(HolidayApplication application) {
		eventStore.appendWith(new EventStreamId(application.getId().getValue(), application.unmutatedVersion()),
				application.mutatingEvents());
	}
}
