package jp.co.genuine.hm.api.dataaccess.holiday;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import holiday.manager.domain.model.user.UserId;
import jp.co.genuine.hm.api.domain.holiday.dto.HolidayRepository;

@Repository
public class HolidayDataaccess implements HolidayRepository {
	@Autowired
	private HolidayMapper mapper;

	@Override
	public UserId findApplicantUserIdByUserId(UserId userId) {
		return mapper.findApplicantUserIdByUserId(userId);
	}

	@Override
	public List<UserId> findManagedMembersByUserId(UserId userId) {
		return mapper.findManagedMembersByUserId(userId);
	}

}
