package jp.co.genuine.hm.api.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupName;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;

@Mapper
public interface UserMapper {
	@Insert("INSERT INTO \"user\".user (id, status, email, name, hire_date, update_datetime)  VALUES (#{userForm.userId.value}, #{userForm.userStatus} , #{userForm.mailAddress.value}, #{userForm.userName.value}, now(), now())")
	void create(@Param("userForm") UserForm userForm);

	@Update("UPDATE  \"user\".user\r\n" + 
			"SET  email = #{userForm.mailAddress.value},\r\n" + 
			"\"name\" = #{userForm.userName.value}\r\n" + 
			"WHERE  \"id\" = #{userId.value}")
	void update(@Param("userId") UserId userId, @Param("userForm") UserForm userForm);

	@Results(id = "manager", value = {
			@Result(id = true, column = "id", property = "userId.value"),
			@Result(id = false, column = "email", property = "mailAddress.value"),
			@Result(id = false, column = "password", property = "password.value"),
			@Result(id = false, column = "name", property = "userName.value"),
			@Result(id = false, column = "status", property = "userStatus.label"),
			@Result(id = false, column = "hire_date", property = "hireDate.value")
	})
	@Select("SELECT\r\n" + 
			"	\"user\".id,\r\n" + 
			"	\"user\".email,\r\n" + 
			"	account.password,\r\n" + 
			"	\"user\".name,\r\n" + 
			"	\"user\".status,\r\n" + 
			"	\"user\".hire_date\r\n" + 
			"FROM\r\n" + 
			"	\"user\".\"user\"\r\n" + 
			"INNER JOIN\r\n" + 
			"	\"user\".account\r\n" + 
			"	ON account.user_id = \"user\".id\r\n" + 
			"INNER JOIN\r\n" + 
			"	\"user\".manager\r\n" + 
			"	ON manager.user_id = \"user\".id\r\n" + 
			"INNER JOIN\r\n" + 
			"	\"user\".\"group\"\r\n" + 
			"	ON \"group\".id = manager.group_id\r\n" + 
			"WHERE\r\n" + 
			"	\"group\".id = #{groupId.value}")
	List<User> findManagerList(@Param("groupId") GroupId groupId);

	@Results(id = "user", value = {
			@Result(id = true, column = "id", property = "userId.value"),
			@Result(id = false, column = "email", property = "mailAddress.value"),
			@Result(id = false, column = "password", property = "password.value"),
			@Result(id = false, column = "name", property = "userName.value"),
			@Result(id = false, column = "status", property = "userStatus.label"),
			@Result(id = false, column = "hire_date", property = "hireDate.value")
	})
//	@Results(id = "jp.co.genuine.hm.api.domain.user.User")
	@Select("SELECT\r\n" + 
			"	\"user\".id,\r\n" + 
			"	\"user\".email,\r\n" + 
			"	account.password,\r\n" + 
			"	\"user\".name,\r\n" + 
			"	\"user\".status,\r\n" + 
			"	\"user\".hire_date\r\n" + 
			"FROM\r\n" + 
			"	\"user\".\"user\"\r\n" + 
			"INNER JOIN\r\n" + 
			"	\"user\".account\r\n" + 
			"	ON account.user_id = \"user\".id\r\n" + 
			"INNER JOIN\r\n" + 
			"	\"user\".member\r\n" + 
			"	ON member.user_id = \"user\".id\r\n" + 
			"INNER JOIN\r\n" + 
			"	\"user\".\"group\"\r\n" + 
			"	ON \"group\".id = member.group_id\r\n" + 
			"WHERE\r\n" + 
			"	\"group\".id = #{groupId.value}")
	List<User> findMemberList(@Param("groupId") GroupId groupId);

	@Results(id = "jp.co.genuine.hm.api.domain.user.GroupName", value = {
			@Result(id = true, column = "name", property = "value")
	})
	@Select("SELECT\r\n" + 
			"	name\r\n" + 
			"FROM\r\n" + 
			"	\"user\".group\r\n" + 
			"WHERE\r\n" + 
			"	\"group\".id = #{groupId.value}\r\n")
	GroupName findGroupName(@Param("groupId") GroupId groupId);

	@Insert("INSERT INTO \"user\".group (id, name, register_datetime, update_datetime)  VALUES (#{userForm.groupId.value}, #{userForm.groupName.value}, now(), now())")
	void createGroup(@Param("userForm") UserForm userForm);

	@Update("UPDATE  \"user\".group\r\n" + 
			"SET  \"name\" = #{userForm.groupName.value},\r\n" + 
			"update_datetime = now()\r\n" + 
			"WHERE  \"id\" = #{groupId.value}")
	void updateGroup(@Param("groupId") GroupId groupId, @Param("userForm") UserForm userForm);
}
