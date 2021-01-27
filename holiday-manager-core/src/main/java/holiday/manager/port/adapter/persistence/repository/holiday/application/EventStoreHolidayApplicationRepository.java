package holiday.manager.port.adapter.persistence.repository.holiday.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.holiday.holiday.HolidayListId;
import holiday.manager.domain.model.holiday.holiday.HolidayListRepository;
import holiday.manager.event.sourcing.EventStore;
import holiday.manager.event.sourcing.EventStream;
import holiday.manager.event.sourcing.EventStreamId;

@Repository
public class EventStoreHolidayApplicationRepository implements HolidayListRepository {

	@Autowired
	private EventStore eventStore;

	@Override
	public HolidayList findById(HolidayListId id){
		EventStream eventStream = eventStore.eventStreamSince(new EventStreamId(id.getValue()));

		return new HolidayList(eventStream.events(), eventStream.version());
	}

	@Override
	public void save(HolidayList holidayList) {
		eventStore.appendWith(new EventStreamId(holidayList.getId().getValue(), holidayList.unmutatedVersion()),
				holidayList.mutatingEvents());
	}
}
