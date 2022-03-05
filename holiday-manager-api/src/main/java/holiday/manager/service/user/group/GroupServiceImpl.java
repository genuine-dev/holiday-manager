package holiday.manager.service.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.domain.request.user.DeleteGroupManagerRequest;
import holiday.manager.domain.request.user.DeleteGroupMemberRequest;
import holiday.manager.domain.request.user.PostGroupManagerRequest;
import holiday.manager.domain.request.user.PostGroupMemberRequest;
import holiday.manager.domain.request.user.PostGroupMembersRequest;
import holiday.manager.domain.request.user.PostGroupRequest;
import holiday.manager.domain.request.user.PutGroupRequest;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.group.Group;
import holiday.manager.domain.user.group.GroupId;
import holiday.manager.domain.user.group.GroupList;
import holiday.manager.domain.user.group.GroupMember;
import holiday.manager.domain.user.group.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public Group findGroup(GroupId groupId) {
		return groupRepository.findGroup(groupId);
	}

	@Override
	public void postGroup(PostGroupRequest request) {
		GroupId groupId = groupRepository.nextGroupId();
		groupRepository.insertGroup(groupId, request.getGroupName());
	}

	@Override
	public void putGroup(GroupId groupId, PutGroupRequest request) {
		groupRepository.updateGroup(groupId, request.getGroupName());
	}

	@Override
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

	@Override
	public void postGroupManager(PostGroupManagerRequest request) {
		groupRepository.insertManager(request.getUserId(), request.getGroupId());
	}

	@Override
	public void postGroupMember(PostGroupMemberRequest request) {
		groupRepository.insertMember(request.getUserId(), request.getGroupId());
	}

	@Override
	public void deleteGroup(GroupId groupId) {
		groupRepository.deleteGroup(groupId);
	}

	@Override
	public void deleteGroupManager(DeleteGroupManagerRequest request) {
		groupRepository.deleteManager(request.getUserId(), request.getGroupId());
	}

	@Override
	public void deleteGroupMember(DeleteGroupMemberRequest request) {
		groupRepository.deleteMember(request.getUserId(), request.getGroupId());
	}

	@Override
	public Group getGroup(GroupId groupId) {
		return groupRepository.getGroup(groupId);
	}

	@Override
	public GroupList getGroups() {
		return groupRepository.getGroup();
	}

}
