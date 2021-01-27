package holiday.manager.domain.model.holiday.holiday;

public interface HolidayListRepository {
	public HolidayList findById(HolidayListId id);

	public void save(HolidayList holidayList);
}
