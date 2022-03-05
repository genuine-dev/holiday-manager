package holiday.manager.dataaccess.user.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import holiday.manager.dataaccess.user.UserMapper;
import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.UserList;
import holiday.manager.domain.user.group.Group;
import holiday.manager.domain.user.group.GroupId;
import holiday.manager.domain.user.group.GroupList;
import holiday.manager.domain.user.group.GroupName;
import holiday.manager.domain.user.group.GroupRepository;

@Repository
public class GroupDatasource implements GroupRepository {
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public Group findGroup(GroupId groupId) {
		List<User> managerList = userMapper.findManagerList(groupId);
		List<User> memberList = userMapper.findMemberList(groupId);
		GroupName groupName = groupMapper.findGroupName(groupId);
		return new Group(groupId, groupName, new UserList(managerList), new UserList(memberList));
	}

	@Override
	public void insertGroup(GroupId groupId, GroupName groupName) {
		groupMapper.insertGroup(groupId, groupName);
	}

	@Override
	public void updateGroup(GroupId groupId, GroupName groupName) {
		groupMapper.updateGroup(groupId, groupName);
	}

	@Override
	public GroupId nextGroupId() {
		return groupMapper.nextGroupId();
	}

	@Override
	public void deleteManager(UserId userId, GroupId groupId) {
		groupMapper.deleteManager(userId, groupId);
	}

	@Override
	public void deleteMember(UserId userId, GroupId groupId) {
		groupMapper.deleteMember(userId, groupId);
	}

	@Override
	public void deleteGroup(GroupId groupId) {
		groupMapper.deleteGroup(groupId);
	}

	@Override
	public Group getGroup(GroupId groupId) {
		return groupMapper.findGroupById(groupId);
	}

	@Override
	public GroupList getGroup() {
		List<Group> groups = groupMapper.findGroups();
		return new GroupList(groups);
	}

	@Override
	public void insertManager(UserId userId, GroupId groupId) {
		groupMapper.insertManager(userId, groupId);
	}

	@Override
	public void insertMember(UserId userId, GroupId groupId) {
		groupMapper.insertMember(userId, groupId);
	}

	@Override
	public void deleteMembers(GroupId groupId) {
		groupMapper.deleteMembers(groupId);
	}

	@Override
	public void deleteManagers(GroupId groupId) {
		groupMapper.deleteManagers(groupId);
	}

}
