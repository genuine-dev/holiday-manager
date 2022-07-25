package holiday.manager.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.rest.request.user.PostUserRequest;
import holiday.manager.rest.request.user.PutUserRequest;
import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;
import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserFactory;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.UserList;
import holiday.manager.domain.user.UserRepository;
import holiday.manager.domain.user.UserStatus;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.group.GroupId;
import holiday.manager.service.validation.ValidateService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserFactory userFactory;

	@Autowired
	ValidateService validateService;

	@Override
	public UserList getUser(UserSorts sorts, UserQueries queries) {
		return userRepository.findAll(sorts, queries);
	}

	@Override
	public void postUser(PostUserRequest request) {
		User user = userFactory.create(request);
		validateService.validate(user);
		userRepository.insertUser(user);
		userRepository.insertAccount(user);
		userRepository.insertRule(user);
	}

	@Override
	public void putUser(UserId userId, PutUserRequest request) {
		User user = userFactory.create(userId, request);
		validateService.validate(user);
		userRepository.updateUser(user);
		updateAccount(user);
	}

	private void updateAccount(User user) {
		if(user.isEmptyPassword()) {
			return;
		}
		userRepository.updateAccount(user);
	}

	@Override
	public UserList findUsers(GroupId groupId) {
		return userRepository.findUsersByGroupId(groupId);
	}

	@Override
	public void deleteUser(@Valid UserId userId) {
		userRepository.deleteUser(userId);
	}

	@Override
	public User findUser(UserId userId) {
		return userRepository.findBy(userId);
	}

	@Override
	public Boolean existAccountId(AccountId accountId) {
		return userRepository.existAccountId(accountId);
	}

	@Override
	public Map<String, String> getUserStatus() {
		Map<String, String> userStatusMap = new HashMap<>();
		for (UserStatus userStatus : UserStatus.values()) {
			userStatusMap.put(userStatus.name(), userStatus.getLabel());
		}
		return userStatusMap;
	}

	@Override
	public List<Integer> findManagementUserIds(Integer userId) {
		return userRepository.findManagementUserIds(userId);
	}
}
