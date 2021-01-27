package holiday.manager.domain.model.holiday.application;

public interface HolidayApplicationRepository {
	public HolidayApplication findById(HolidayApplicationId id);

	public void save(HolidayApplication application);
}
