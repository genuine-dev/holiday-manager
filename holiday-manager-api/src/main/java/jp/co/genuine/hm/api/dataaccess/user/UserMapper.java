package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.genuine.hm.api.domain.common.MailAddress;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupName;
import jp.co.genuine.hm.api.domain.user.HireDate;
import jp.co.genuine.hm.api.domain.user.Password;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserName;
import jp.co.genuine.hm.api.domain.user.UserStatus;

@Mapper
public interface UserMapper {

	UserId nextUserId();

	void insertUser(@Param("userId") UserId userId, @Param("userStatus") UserStatus userStatus,
			@Param("mailAddress") MailAddress mailAddress, @Param("userName") UserName userName,
			@Param("hireDate") HireDate hireDate);

	void insertAccount(@Param("userId") UserId userId, @Param("accountId") AccountId accountId,
			@Param("password") Password password);

	void updateUser(@Param("userId") UserId userId, @Param("mailAddress") MailAddress mailAddress,
			@Param("userName") UserName userName);

	List<User> findAllUser();

	List<User> findManagerList(@Param("groupId") GroupId groupId);

	List<User> findMemberList(@Param("groupId") GroupId groupId);

	GroupName findGroupName(@Param("groupId") GroupId groupId);

	void insertGroup(@Param("groupId") GroupId groupId, @Param("groupName") GroupName groupName);

	void updateGroup(@Param("groupId") GroupId groupId, @Param("groupName") GroupName groupName);

	List<User> findUsersByGroupId(@Param("groupId") GroupId groupId);

	void insertMember(@Param("userForm") UserForm userForm);

	GroupId nextGroupId();
}
