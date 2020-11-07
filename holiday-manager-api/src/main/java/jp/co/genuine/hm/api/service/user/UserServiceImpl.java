package jp.co.genuine.hm.api.service.user;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.request.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.PutGroupRequest;
import jp.co.genuine.hm.api.domain.request.PutUserRequest;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static Log log = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	public UserList getUser() {
		return userRepository.findAll();
	}

	public void postUser(PostUserRequest request) {
		UserId userId = userRepository.nextUserId();
		insertUser(userId, request);
		userRepository.insertAccount(userId, request.getAccountId(), request.getPassword());
	}

	private void insertUser(UserId userId, PostUserRequest request) {
		try {
			userRepository.insertUser(userId, request.getStatus(),
					request.getMailAddress(), request.getUserName(),
					request.getHireDate());
		} catch (ParseException e) {
			logError(e);
			throw new RuntimeException(e);
		}
	}

	public void putUser(UserId userId, PutUserRequest request) {
		userRepository.updateUser(userId, request.getMailAddress(), request.getUserName());
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

	private void logError(ParseException e) {
		log.error(e);
	}
}
