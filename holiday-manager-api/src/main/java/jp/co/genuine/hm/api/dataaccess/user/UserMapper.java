package jp.co.genuine.hm.api.dataaccess.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupName;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;

@Mapper
public interface UserMapper {

	void create(@Param("userForm") UserForm userForm);

	void update(@Param("userId") UserId userId, @Param("userForm") UserForm userForm);

	List<User> findManagerList(@Param("groupId") GroupId groupId);

	List<User> findMemberList(@Param("groupId") GroupId groupId);

	GroupName findGroupName(@Param("groupId") GroupId groupId);

	void createGroup(@Param("userForm") UserForm userForm);

	void updateGroup(@Param("groupId") GroupId groupId, @Param("userForm") UserForm userForm);

	List<User> findUsersByGroupId(@Param("groupId") GroupId groupId);

	void insertMember(@Param("userForm") UserForm userForm);

	void insertAccount(@Param("userForm") UserForm userForm);
}
