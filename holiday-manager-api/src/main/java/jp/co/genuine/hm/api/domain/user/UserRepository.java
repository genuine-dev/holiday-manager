package jp.co.genuine.hm.api.domain.user;

import java.util.List;
import java.util.Optional;

import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.account.AccountId;
import jp.co.genuine.hm.api.domain.user.alert.HolidayAlert;
import jp.co.genuine.hm.api.domain.user.group.GroupId;

public interface UserRepository {
	public UserId nextUserId();

	public void insertUser(User user);

	public void insertAccount(User user);

	public void updateUser(User user);

	public void updateAccount(User user);

	public UserList findAll(UserSorts sorts, UserQueries queries);

	public UserList findUsersByGroupId(GroupId groupId);

	public Optional<User> findByEmail(String email);

	public void deleteUser(UserId userId);

	public User findBy(UserId userId);

	public Boolean existAccountId(AccountId accountId);

	public void insertRule(User user);

	public HolidayAlert findHolidayAlert(UserId owner);

	public List<Integer> findManagementUserIds(Integer userId);
}
