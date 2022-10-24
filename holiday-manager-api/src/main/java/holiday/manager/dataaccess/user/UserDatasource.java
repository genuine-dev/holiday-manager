package holiday.manager.dataaccess.user;

import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.UserList;
import holiday.manager.domain.user.UserRepository;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public UserList list(UserSorts sorts, UserQueries queries) {
        List<User> userList = userMapper.list(sorts, queries);
        return new UserList(userList);
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
    public Boolean existsAccountId(AccountId accountId) {
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
        if (holidayAlert.isPresent())
            return holidayAlert.get();
        return new HolidayAlert();
    }

    @Override
    public List<Integer> findManagementUserIds(Integer userId) {
        return userMapper.findManagedMembers(userId);
    }

    @Override
    public holiday.manager.domain.model.user.UserId findApplicantUserIdByUserId(holiday.manager.domain.model.user.UserId userId) {
        return userMapper.findApplicantUserIdByUserId(userId);
    }

    @Override
    public List<holiday.manager.domain.model.user.UserId> findManagedMembersByUserId(holiday.manager.domain.model.user.UserId userId) {
        return userMapper.findManagedMembersByUserId(userId);
    }
}
