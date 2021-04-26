package holiday.manager.repository.repository;

import org.springframework.data.repository.CrudRepository;

import holiday.manager.repository.repository.PaidLeaveGrantHistoryEntiry.ID;

public interface PaidLeaveGrantHistoryRepository extends CrudRepository<PaidLeaveGrantHistoryEntiry, ID> {
	public Iterable<PaidLeaveGrantHistoryEntiry> findByIdUserId(int userId);
}
