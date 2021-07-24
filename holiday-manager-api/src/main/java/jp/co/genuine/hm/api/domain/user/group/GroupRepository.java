package jp.co.genuine.hm.api.domain.user.group;

import jp.co.genuine.hm.api.domain.user.UserId;

public interface GroupRepository {

	Group findGroup(GroupId groupId);

	void insertGroup(GroupId groupId, GroupName groupName);

	void updateGroup(GroupId groupId, GroupName groupName);

	GroupId nextGroupId();

	void deleteManager(UserId userId, GroupId groupId);

	void deleteMember(UserId userId, GroupId groupId);

	void deleteGroup(GroupId groupId);

	Group getGroup(GroupId groupId);

	GroupList getGroup();

	void insertManager(UserId userId, GroupId groupId);

	void insertMember(UserId userId, GroupId groupId);

	void deleteMembers(GroupId groupId);

	void deleteManagers(GroupId groupId);

}
