package holiday.manager.domain.user;

import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;

import java.util.List;

public interface UserRepository {
    UserId nextUserId();

    void insertUser(User user);

    void insertAccount(User user);

    void updateUser(User user);

    void updateAccount(User user);

    UserList list(UserSorts sorts, UserQueries queries);

    void deleteUser(UserId userId);

    User findBy(UserId userId);

    Boolean existsAccountId(AccountId accountId);

    void insertRule(User user);

    HolidayAlert findHolidayAlert(UserId owner);

    List<Integer> findManagementUserIds(Integer userId);

    holiday.manager.domain.model.user.UserId findApplicantUserIdByUserId(holiday.manager.domain.model.user.UserId userId);

    public List<holiday.manager.domain.model.user.UserId> findManagedMembersByUserId(holiday.manager.domain.model.user.UserId userId);
}
