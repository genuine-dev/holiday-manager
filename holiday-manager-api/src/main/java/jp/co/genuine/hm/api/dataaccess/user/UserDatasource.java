package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.GroupId;
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
	public UserList findAll(UserSorts sorts, UserQueries queries) {
		List<User> userList = userMapper.findAllUser(sorts, queries);
		return new UserList(userList);
	}

	@Override
	public UserList findUsersByGroupId(GroupId groupId) {
		List<User> userList = userMapper.findUsersByGroupId(groupId);
		return new UserList(userList);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(userMapper.findByEmail(email));
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
