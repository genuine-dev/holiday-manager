package jp.co.genuine.hm.api.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.repository.user.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public void create(UserForm userForm) {
		userRepository.create(userForm);
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
}
