package holiday.manager.domain.user;

import java.util.List;
import java.util.Optional;

import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.domain.user.group.GroupId;

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
