package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupName;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserRepository;

@Repository
public class UserDatasource implements UserRepository {
	@Autowired
	UserMapper userMapper;
	
	@Override
	public void createMember(UserForm userForm) {
		userMapper.create(userForm);
		userMapper.insertMember(userForm);
		userMapper.insertAccount(userForm);
	}

	@Override
	public void update(UserId userId, UserForm userForm) {
		userMapper.update(userId, userForm);
	}

	@Override
	public Group findGroup(GroupId groupId) {
		List<User> managerList = userMapper.findManagerList(groupId);
		List<User> memberList = userMapper.findMemberList(groupId);
		GroupName groupName = userMapper.findGroupName(groupId);
		return new Group(groupId, groupName, new UserList(managerList), new UserList(memberList));
	}

	@Override
	public void createGroup(UserForm userForm) {
		userMapper.createGroup(userForm);
	}

	@Override
	public void updateGroup(GroupId groupId, UserForm userForm) {
		userMapper.updateGroup(groupId, userForm);
	}

	@Override
	public UserList findUsersByGroupId(GroupId groupId) {
		List<User> userList = userMapper.findUsersByGroupId(groupId);
		return new UserList(userList);
	}
}
