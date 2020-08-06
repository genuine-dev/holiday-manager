package holiday.manager.port.adapter.persistence.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoreRepository extends CrudRepository<EventStoreEntity, Long> {

	@Query(value = "SELECT * FROM event_store es WHERE es.id > :eventId ORDER BY es.id", nativeQuery = true)
	public List<EventStoreEntity> findByEventIdSince(@Param("eventId") long eventId);

	@Query(value = "SELECT * FROM event_store es WHERE es.stream_name = :name AND es.stream_version >= :version ORDER BY es.stream_version", nativeQuery = true)
	public List<EventStoreEntity> findByStreamNameStreamVestionSince(@Param("name") String name,
			@Param("version") int version);

	@Query(value = "SELECT * FROM event_store es WHERE es.stream_name = :name ORDER BY es.stream_version", nativeQuery = true)
	public List<EventStoreEntity> findByStreamNameStream(@Param("name") String name);
}