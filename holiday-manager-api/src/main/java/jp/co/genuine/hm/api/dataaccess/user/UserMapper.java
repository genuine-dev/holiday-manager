package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;

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
}
