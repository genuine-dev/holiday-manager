package holiday.manager.service.user.group;

import holiday.manager.domain.request.user.DeleteGroupManagerRequest;
import holiday.manager.domain.request.user.DeleteGroupMemberRequest;
import holiday.manager.domain.request.user.PostGroupManagerRequest;
import holiday.manager.domain.request.user.PostGroupMemberRequest;
import holiday.manager.domain.request.user.PostGroupMembersRequest;
import holiday.manager.domain.request.user.PostGroupRequest;
import holiday.manager.domain.request.user.PutGroupRequest;
import holiday.manager.domain.user.group.Group;
import holiday.manager.domain.user.group.GroupId;
import holiday.manager.domain.user.group.GroupList;

public interface GroupService {
	public Group findGroup(GroupId groupId);
	public void postGroup(PostGroupRequest request);
	public void putGroup(GroupId groupId, PutGroupRequest request);
	public void deleteGroup(GroupId groupId);
	public void postGroupManager(PostGroupManagerRequest request);
	public void postGroupMembers(PostGroupMembersRequest request);
	public void postGroupMember(PostGroupMemberRequest request);
	public void deleteGroupManager(DeleteGroupManagerRequest request);
	public void deleteGroupMember(DeleteGroupMemberRequest request);
	public Group getGroup(GroupId groupId);
	public GroupList getGroups();
}