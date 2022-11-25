package holiday.manager.dataaccess.group;

import holiday.manager.domain.group.Group;
import holiday.manager.domain.group.GroupId;
import holiday.manager.domain.group.GroupName;
import holiday.manager.domain.user.UserId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
