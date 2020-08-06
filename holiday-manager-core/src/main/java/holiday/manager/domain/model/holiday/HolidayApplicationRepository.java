package holiday.manager.domain.model.holiday;

import org.springframework.stereotype.Repository;

@Repository
public interface HolidayApplicationRepository {
	public HolidayApplication findById(HolidayApplicationId id);

	public void save(HolidayApplication application);
}
