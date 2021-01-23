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
import jp.co.genuine.hm.api.domain.user.HireDate;
import jp.co.genuine.hm.api.domain.user.MailAddress;
import jp.co.genuine.hm.api.domain.user.Password;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserName;
import jp.co.genuine.hm.api.domain.user.UserRepository;
import jp.co.genuine.hm.api.domain.user.UserStatus;

@Repository
public class UserDatasource implements UserRepository {
	@Autowired
	UserMapper userMapper;

	@Override
	public UserId nextUserId() {
		return userMapper.nextUserId();
	}

	@Override
	public void insertUser(UserId userId, UserStatus userStatus, MailAddress mailAddress, UserName userName,
			HireDate hireDate) {
		userMapper.insertUser(userId, userStatus, mailAddress, userName, hireDate);
	}

	@Override
	public void insertAccount(UserId userId, AccountId accountId, Password password) {
		userMapper.insertAccount(userId, accountId, password);
	}

	@Override
	public void updateUser(UserId userId, MailAddress mailAddress, UserName userName) {
		userMapper.updateUser(userId, mailAddress, userName);
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
}
