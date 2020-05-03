package jp.co.genuine.hm.api.repository.user;

import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;

public interface UserRepository {
	void create(UserForm userForm);

	void update(UserId userId, UserForm userForm);

	Group findGroup(GroupId groupId);

	void createGroup(UserForm userForm);

	void updateGroup(GroupId groupId, UserForm userForm);
}
