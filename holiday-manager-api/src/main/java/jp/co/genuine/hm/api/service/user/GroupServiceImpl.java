package jp.co.genuine.hm.api.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.request.user.DeleteGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.DeleteGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.user.PutGroupRequest;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupList;
import jp.co.genuine.hm.api.domain.user.GroupRepository;

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
