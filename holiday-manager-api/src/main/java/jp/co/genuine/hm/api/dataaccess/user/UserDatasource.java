package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.genuine.hm.api.domain.request.parameter.Sorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupName;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserRepository;

@Repository
public class UserDatasource implements UserRepository {
	@Autowired
	UserMapper userMapper;

	@Override
	public UserId nextUserId() {
		return userMapper.nextUserId();
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public void insertAccount(User user) {
		userMapper.insertAccount(user);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public void updateAccount(User user) {
		userMapper.updateAccount(user);
	}

	@Override
	public UserList findAll(Sorts sorts) {
		List<User> userList = userMapper.findAllUser(sorts);
		return new UserList(userList);
	}

	@Override
	public Group findGroup(GroupId groupId) {
		List<User> managerList = userMapper.findManagerList(groupId);
		List<User> memberList = userMapper.findMemberList(groupId);
		GroupName groupName = userMapper.findGroupName(groupId);
		return new Group(groupId, groupName, new UserList(managerList), new UserList(memberList));
	}

	@Override
	public void insertGroup(GroupId groupId, GroupName groupName) {
		userMapper.insertGroup(groupId, groupName);
	}

	@Override
	public void updateGroup(GroupId groupId, GroupName groupName) {
		userMapper.updateGroup(groupId, groupName);
	}

	@Override
	public UserList findUsersByGroupId(GroupId groupId) {
		List<User> userList = userMapper.findUsersByGroupId(groupId);
		return new UserList(userList);
	}

	@Override
	public GroupId nextGroupId() {
		return userMapper.nextGroupId();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(userMapper.findByEmail(email));
	}

	@Override
	public void insertManager(UserId userId, GroupId groupId) {
		userMapper.insertManager(userId, groupId);
	}

	@Override
	public void insertMember(UserId userId, GroupId groupId) {
		userMapper.insertMember(userId, groupId);
	}

	@Override
	public void deleteManager(UserId userId) {
		userMapper.deleteManager(userId);
	}

	@Override
	public void deleteMember(UserId userId) {
		userMapper.deleteMember(userId);
	}

	@Override
	public void deleteGroup(GroupId groupId) {
		userMapper.deleteGroup(groupId);
	}

	@Override
	public void deleteUser(UserId userId) {
		userMapper.deleteUser(userId);
	}

	@Override
	public User findBy(UserId userId) {
		return userMapper.findBy(userId);
	}

	@Override
	public Boolean existAccountId(AccountId accountId) {
		Optional<User> user = Optional.ofNullable(userMapper.findByAccountId(accountId));
		return user.isPresent();
	}
}
