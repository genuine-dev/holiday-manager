package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.genuine.hm.api.domain.request.parameter.Sorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupName;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;

@Mapper
public interface UserMapper {

	UserId nextUserId();

	void insertUser(@Param("user") User user);

	void insertAccount(@Param("user") User user);

	void updateUser(@Param("user") User user);

	void updateAccount(@Param("user") User user);

	List<User> findAllUser(@Param("sorts") Sorts sorts);

	List<User> findManagerList(@Param("groupId") GroupId groupId);

	List<User> findMemberList(@Param("groupId") GroupId groupId);

	GroupName findGroupName(@Param("groupId") GroupId groupId);

	void insertGroup(@Param("groupId") GroupId groupId, @Param("groupName") GroupName groupName);

	void updateGroup(@Param("groupId") GroupId groupId, @Param("groupName") GroupName groupName);

	List<User> findUsersByGroupId(@Param("groupId") GroupId groupId);

	GroupId nextGroupId();

	User findByEmail(@Param("email") String email);

	void insertManager(@Param("userId") UserId userId, @Param("groupId") GroupId groupId);

	void insertMember(@Param("userId") UserId userId, @Param("groupId") GroupId groupId);

	void deleteManager(@Param("userId") UserId userId);

	void deleteMember(@Param("userId") UserId userId);

	void deleteGroup(@Param("groupId") GroupId groupId);

	void deleteUser(@Param("userId") UserId userId);

	User findBy(@Param("userId") UserId userId);

	User findByAccountId(@Param("accountId") AccountId accountId);
}
