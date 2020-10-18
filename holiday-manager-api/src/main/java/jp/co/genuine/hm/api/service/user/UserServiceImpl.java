package jp.co.genuine.hm.api.service.user;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.common.MailAddress;
import jp.co.genuine.hm.api.domain.request.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.PutGroupRequest;
import jp.co.genuine.hm.api.domain.request.PutUserRequest;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupName;
import jp.co.genuine.hm.api.domain.user.HireDate;
import jp.co.genuine.hm.api.domain.user.Password;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserName;
import jp.co.genuine.hm.api.domain.user.UserRepository;
import jp.co.genuine.hm.api.domain.user.UserStatus;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public UserList getUser() {
		return userRepository.findAllUser();
	}

	public void postUser(PostUserRequest request) {
		UserId userId = userRepository.nextUserId();
		try {
			userRepository.insertUser(userId, UserStatus.valueOf(request.getStatus()),
					new MailAddress(request.getMailAddress()), new UserName(request.getUserName()),
					new HireDate(request.getHireDate()));
		} catch (ParseException e) {
		}
		userRepository.insertAccount(userId, new AccountId(request.getAccountId()),
				new Password(request.getPassword()));
	}

	public void putUser(UserId userId, PutUserRequest request) {
		userRepository.updateUser(userId, new MailAddress(request.getMailAddress()),
				new UserName(request.getUserName()));
	}

	public Group findGroup(GroupId groupId) {
		return userRepository.findGroup(groupId);
	}

	public void postGroup(PostGroupRequest request) {
		GroupId groupId = userRepository.nextGroupId();
		userRepository.insertGroup(groupId, new GroupName(request.getGroupName()));
	}

	public void putGroup(GroupId groupId, PutGroupRequest request) {
		userRepository.updateGroup(groupId, new GroupName(request.getGroupName()));
	}

	public UserList findUsers(GroupId groupId) {
		return userRepository.findUsersByGroupId(groupId);
	}
}
