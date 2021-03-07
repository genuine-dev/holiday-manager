package jp.co.genuine.hm.api.dataaccess.user.group;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.group.Group;
import jp.co.genuine.hm.api.domain.user.group.GroupId;
import jp.co.genuine.hm.api.domain.user.group.GroupName;

@Mapper
public interface GroupMapper {

	public GroupName findGroupName(@Param("groupId") GroupId groupId);

	public void insertGroup(@Param("groupId") GroupId groupId, @Param("groupName") GroupName groupName);

	public void updateGroup(@Param("groupId") GroupId groupId, @Param("groupName") GroupName groupName);

	public void insertManager(@Param("userId") UserId userId, @Param("groupId") GroupId groupId);

	public void insertMember(@Param("userId") UserId userId, @Param("groupId") GroupId groupId);

	public void deleteManager(@Param("userId") UserId userId, @Param("groupId") GroupId groupId);

	public void deleteMember(@Param("userId") UserId userId, @Param("groupId") GroupId groupId);

	public void deleteGroup(@Param("groupId") GroupId groupId);

	public Group findGroupById(@Param("groupId") GroupId groupId);

	public List<Group> findGroups();

	public GroupId nextGroupId();

}
