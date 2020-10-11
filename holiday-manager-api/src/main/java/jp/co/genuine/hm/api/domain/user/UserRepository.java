package jp.co.genuine.hm.api.domain.user;

public interface UserRepository {
	void createMember(UserForm userForm);

	void update(UserId userId, UserForm userForm);

	Group findGroup(GroupId groupId);

	void createGroup(UserForm userForm);

	void updateGroup(GroupId groupId, UserForm userForm);

	UserList findUsersByGroupId(GroupId groupId);
}
