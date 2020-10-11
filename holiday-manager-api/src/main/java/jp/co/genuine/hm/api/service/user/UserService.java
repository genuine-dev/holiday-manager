package jp.co.genuine.hm.api.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.common.MailAddress;
import jp.co.genuine.hm.api.domain.common.Password;
import jp.co.genuine.hm.api.domain.request.PostUserRequest;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserName;
import jp.co.genuine.hm.api.domain.user.UserRepository;
import jp.co.genuine.hm.api.domain.user.UserStatus;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public void createMember(UserForm userForm) {
		userRepository.createMember(userForm);
	}

	public void update(UserId userId, UserForm userForm) {
		userRepository.update(userId, userForm);
	}

	public Group findGroup(GroupId groupId) {
		return userRepository.findGroup(groupId);
	}

	public void createGroup(UserForm userForm) {
		userRepository.createGroup(userForm);
	}

	public void updateGroup(GroupId groupId, UserForm userForm) {
		userRepository.updateGroup(groupId, userForm);
	}

	public UserList findUsers(GroupId groupId) {
		return userRepository.findUsersByGroupId(groupId);
	}

	public void postMember(PostUserRequest request) {
		String userId = request.getUserId();
		String mailAddress = request.getMailAddress();
		String userName = request.getUserName();
		String groupId = request.getGroupId();
		String password = request.getPassword();
		UserForm userForm = new UserForm( new UserId(userId), new MailAddress(mailAddress), new UserName(userName), UserStatus.ACTIVE, new GroupId(groupId), new Password(password));
		createMember(userForm);
	}
}
