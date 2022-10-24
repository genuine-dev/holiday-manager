package holiday.manager.dataaccess.user;

import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.domain.user.group.GroupId;
import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    public UserId nextUserId();

    public void insertUser(@Param("user") User user);

    public void insertAccount(@Param("user") User user);

    public void updateUser(@Param("user") User user);

    public void updateAccount(@Param("user") User user);

    public List<User> list(@Param("sorts") UserSorts sorts, @Param("queries") UserQueries queries);

    public List<User> listManager(@Param("groupId") GroupId groupId);

    public List<User> listMember(@Param("groupId") GroupId groupId);

    public void deleteUser(@Param("userId") UserId userId);

    public User findBy(@Param("userId") UserId userId);

    public User findByAccountId(@Param("accountId") AccountId accountId);

    public void insertRule(@Param("user") User user);

    public HolidayAlert findHolidayAlert(@Param("userId") UserId userId);

    public List<Integer> findManagedMembers(@Param("userId") Integer userId);

    public holiday.manager.domain.model.user.UserId findApplicantUserIdByUserId(@Param("userId") holiday.manager.domain.model.user.UserId userId);

    public List<holiday.manager.domain.model.user.UserId> findManagedMembersByUserId(@Param("userId") holiday.manager.domain.model.user.UserId userId);
}
