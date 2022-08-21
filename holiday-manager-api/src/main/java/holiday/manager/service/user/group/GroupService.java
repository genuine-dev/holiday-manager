package holiday.manager.service.user.group;

import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.group.*;
import holiday.manager.rest.request.user.DeleteGroupManagerRequest;
import holiday.manager.rest.request.user.DeleteGroupMemberRequest;
import holiday.manager.rest.request.user.PostGroupManagerRequest;
import holiday.manager.rest.request.user.PostGroupMemberRequest;
import holiday.manager.rest.request.user.PostGroupMembersRequest;
import holiday.manager.rest.request.user.PostGroupRequest;
import holiday.manager.rest.request.user.PutGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
	private final GroupRepository groupRepository;

	public GroupService(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	public Group findGroup(GroupId groupId) {
		return groupRepository.findGroup(groupId);
	}

	public void postGroup(PostGroupRequest request) {
		GroupId groupId = groupRepository.nextGroupId();
		groupRepository.insertGroup(groupId, request.getGroupName());
	}

	public void putGroup(GroupId groupId, PutGroupRequest request) {
		groupRepository.updateGroup(groupId, request.getGroupName());
	}

	public void postGroupMembers(PostGroupMembersRequest request) {
		groupRepository.deleteMembers(request.getGroupId());
		groupRepository.deleteManagers(request.getGroupId());
		for(GroupMember groupMember : request.getGroupMemberList())
		{
			if(groupMember.isManager())
				groupRepository.insertManager(new UserId(groupMember.getUserId()), request.getGroupId());
			if(groupMember.isMember())
				groupRepository.insertMember(new UserId(groupMember.getUserId()), request.getGroupId());
		}
	}

	public void postGroupManager(PostGroupManagerRequest request) {
		groupRepository.insertManager(request.getUserId(), request.getGroupId());
	}

	public void postGroupMember(PostGroupMemberRequest request) {
		groupRepository.insertMember(request.getUserId(), request.getGroupId());
	}

	public void deleteGroup(GroupId groupId) {
		groupRepository.deleteGroup(groupId);
	}

	public void deleteGroupManager(DeleteGroupManagerRequest request) {
		groupRepository.deleteManager(request.getUserId(), request.getGroupId());
	}

	public void deleteGroupMember(DeleteGroupMemberRequest request) {
		groupRepository.deleteMember(request.getUserId(), request.getGroupId());
	}

	public Group getGroup(GroupId groupId) {
		return groupRepository.getGroup(groupId);
	}

	public GroupList getGroups() {
		return groupRepository.getGroup();
	}
}
