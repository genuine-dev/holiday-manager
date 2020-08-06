package holiday.manager.port.adapter.persistence.repository.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipatcherLatestEventRepository extends CrudRepository<DipatcherLatestEventEntity, String> {

}
