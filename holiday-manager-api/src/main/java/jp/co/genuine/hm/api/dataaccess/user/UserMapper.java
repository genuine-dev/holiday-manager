package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.account.AccountId;
import jp.co.genuine.hm.api.domain.user.alert.HolidayAlert;
import jp.co.genuine.hm.api.domain.user.group.GroupId;

@Mapper
public interface UserMapper {

	public UserId nextUserId();

	public void insertUser(@Param("user") User user);

	public void insertAccount(@Param("user") User user);

	public void updateUser(@Param("user") User user);

	public void updateAccount(@Param("user") User user);

	public List<User> findAllUser(@Param("sorts") UserSorts sorts, @Param("queries") UserQueries queries);

	public List<User> findManagerList(@Param("groupId") GroupId groupId);

	public List<User> findMemberList(@Param("groupId") GroupId groupId);

	public List<User> findUsersByGroupId(@Param("groupId") GroupId groupId);

	public User findByEmail(@Param("email") String email);

	public void deleteUser(@Param("userId") UserId userId);

	public User findBy(@Param("userId") UserId userId);

	public User findByAccountId(@Param("accountId") AccountId accountId);

	public void insertRule(@Param("user") User user);

	public HolidayAlert findHolidayAlert(@Param("userId") UserId userId);

	public List<Integer> findManagementUserIds(@Param("userId") Integer userId);
}
