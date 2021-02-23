package holiday.manager.port.adapter.persistence.repository.holiday.application.query;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayApplicationQueryRepository extends CrudRepository<HolidayApplicationEntity, String> {

	List<HolidayApplicationEntity> findByAplicantId(Integer aplicantId);

	List<HolidayApplicationEntity> findByAplicantIdAndStatus(Integer aplicantId, String status);
}
