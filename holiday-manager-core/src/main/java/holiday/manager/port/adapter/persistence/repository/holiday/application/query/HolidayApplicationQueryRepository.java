package holiday.manager.port.adapter.persistence.repository.holiday.application.query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayApplicationQueryRepository extends CrudRepository<HolidayApplicationEntity, String> {

}
