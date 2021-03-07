package jp.co.genuine.hm.api.domain.holiday.dto;

import java.util.List;

import holiday.manager.domain.model.user.UserId;

public interface HolidayRepository {
	public UserId findApplicantUserIdByUserId(UserId userId);
	public List<UserId> findManagedMembersByUserId(UserId userId);
}
