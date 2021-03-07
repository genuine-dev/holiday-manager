package jp.co.genuine.hm.api.domain.user;

import java.util.Optional;

import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.account.AccountId;
import jp.co.genuine.hm.api.domain.user.alert.AlertForTakingPaidLeave;
import jp.co.genuine.hm.api.domain.user.group.GroupId;

public interface UserRepository {
	UserId nextUserId();

	void insertUser(User user);

	void insertAccount(User user);

	void updateUser(User user);

	void updateAccount(User user);

	UserList findAll(UserSorts sorts, UserQueries queries);

	UserList findUsersByGroupId(GroupId groupId);

	Optional<User> findByEmail(String email);

	void deleteUser(UserId userId);

	User findBy(UserId userId);

	Boolean existAccountId(AccountId accountId);

	void insertRule(User user);

	AlertForTakingPaidLeave findAlertForTakingPaidLeave(UserId owner);
}
