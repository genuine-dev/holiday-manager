package holiday.manager.dataaccess.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;
import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.UserList;
import holiday.manager.domain.user.UserRepository;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.domain.user.group.GroupId;

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

	@Override
	public void insertRule(User user) {
		userMapper.insertRule(user);
	}

	@Override
	public HolidayAlert findHolidayAlert(UserId userId) {
		Optional<HolidayAlert> holidayAlert = Optional.ofNullable(userMapper.findHolidayAlert(userId));
		if(holidayAlert.isPresent())
			return holidayAlert.get();
		return new HolidayAlert();
	}

	@Override
	public List<Integer> findManagementUserIds(Integer userId) {
		return userMapper.findManagementUserIds(userId);
	}
}
