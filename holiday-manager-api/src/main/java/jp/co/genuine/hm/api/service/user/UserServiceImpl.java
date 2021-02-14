package jp.co.genuine.hm.api.service.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.request.user.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.user.PutUserRequest;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.GroupId;
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
	}

	@Override
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
}
