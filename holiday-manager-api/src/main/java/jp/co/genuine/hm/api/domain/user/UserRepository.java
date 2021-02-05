package jp.co.genuine.hm.api.domain.user;

import java.util.Optional;

import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;

public interface UserRepository {
	UserId nextUserId();

	void insertUser(User user);

	void insertAccount(User user);

	void updateUser(User user);

	void updateAccount(User user);

	UserList findAll(UserSorts sorts, UserQueries queries);

	Group findGroup(GroupId groupId);

	void insertGroup(GroupId groupId, GroupName groupName);

	void updateGroup(GroupId groupId, GroupName groupName);

	UserList findUsersByGroupId(GroupId groupId);

	GroupId nextGroupId();

	Optional<User> findByEmail(String email);

	void insertManager(UserId userId, GroupId groupId);

	void insertMember(UserId userId, GroupId groupId);

	void deleteManager(UserId userId);

	void deleteMember(UserId userId);

	void deleteGroup(GroupId groupId);

	void deleteUser(UserId userId);

	User findBy(UserId userId);

	Boolean existAccountId(AccountId accountId);
}
