package jp.co.genuine.hm.api.domain.user;

import jp.co.genuine.hm.api.domain.common.MailAddress;

public interface UserRepository {
	UserId nextUserId();

	void insertUser(UserId userId, UserStatus userStatus, MailAddress mailAddress, UserName userName,
			HireDate hireDate);

	void insertAccount(UserId userId, AccountId accountId, Password password);

	void updateUser(UserId userId, MailAddress mailAddress, UserName userName);

	UserList findAllUser();

	Group findGroup(GroupId groupId);

	void insertGroup(GroupId groupId, GroupName groupName);

	void updateGroup(GroupId groupId, GroupName groupName);

	UserList findUsersByGroupId(GroupId groupId);

	GroupId nextGroupId();
}
