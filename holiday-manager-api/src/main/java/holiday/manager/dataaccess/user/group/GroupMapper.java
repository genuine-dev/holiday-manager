package holiday.manager.dataaccess.user.group;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.group.Group;
import holiday.manager.domain.user.group.GroupId;
import holiday.manager.domain.user.group.GroupName;

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

	public void deleteMembers(@Param("groupId") GroupId groupId);

	public void deleteManagers(@Param("groupId") GroupId groupId);

}
