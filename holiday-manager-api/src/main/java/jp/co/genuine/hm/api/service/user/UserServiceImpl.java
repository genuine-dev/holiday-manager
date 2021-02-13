package jp.co.genuine.hm.api.service.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.request.user.DeleteGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.DeleteGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.user.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.user.PutGroupRequest;
import jp.co.genuine.hm.api.domain.request.user.PutUserRequest;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupList;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserFactory;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserRepository;
import jp.co.genuine.hm.api.service.validation.ValidateService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserFactory userFactory;

	@Autowired
	ValidateService validateService;

	public UserList getUser(UserSorts sorts, UserQueries queries) {
		return userRepository.findAll(sorts, queries);
	}

	public void postUser(PostUserRequest request) {
		User user = userFactory.create(request);
		validateService.validate(user);
		userRepository.insertUser(user);
		userRepository.insertAccount(user);
	}

	public void putUser(UserId userId, PutUserRequest request) {
		User user = userFactory.create(userId, request);
		validateService.validate(user);
		userRepository.updateUser(user);
		updateAccount(user);
	}

	private void updateAccount(User user) {
		if(user.getPassword().isEmpty()) {
			return;
		}
		userRepository.updateAccount(user);
	}

	public Group findGroup(GroupId groupId) {
		return userRepository.findGroup(groupId);
	}

	public void postGroup(PostGroupRequest request) {
		GroupId groupId = userRepository.nextGroupId();
		userRepository.insertGroup(groupId, request.getGroupName());
	}

	public void putGroup(GroupId groupId, PutGroupRequest request) {
		userRepository.updateGroup(groupId, request.getGroupName());
	}

	public UserList findUsers(GroupId groupId) {
		return userRepository.findUsersByGroupId(groupId);
	}

	public void postGroupManager(PostGroupManagerRequest request) {
		userRepository.insertManager(request.getUserId(), request.getGroupId());
	}

	public void postGroupMember(PostGroupMemberRequest request) {
		userRepository.insertMember(request.getUserId(), request.getGroupId());
	}

	public void deleteGroup(GroupId groupId) {
		userRepository.deleteGroup(groupId);
	}

	public void deleteUser(@Valid UserId userId) {
		userRepository.deleteUser(userId);
	}

	public User findUser(UserId userId) {
		return userRepository.findBy(userId);
	}

	public Boolean existAccountId(AccountId accountId) {
		return userRepository.existAccountId(accountId);
	}

	public void deleteGroupManager(DeleteGroupManagerRequest request) {
		userRepository.deleteManager(request.getUserId(), request.getGroupId());
	}

	public void deleteGroupMemr(DeleteGroupMemberRequest request) {
		userRepository.deleteManager(request.getUserId(), request.getGroupId());
	}


	public void deleteGroupMember(DeleteGroupMemberRequest request) {
		userRepository.deleteMember(request.getUserId(), request.getGroupId());
	}

	public Group getGroup(GroupId groupId) {
		return userRepository.getGroup(groupId);
	}

	public GroupList getGroups() {
		return userRepository.getGroup();
	}
}
