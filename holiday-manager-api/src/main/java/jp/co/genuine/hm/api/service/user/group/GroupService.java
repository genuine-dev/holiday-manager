package jp.co.genuine.hm.api.service.user.group;

import jp.co.genuine.hm.api.domain.request.user.DeleteGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.DeleteGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.user.PutGroupRequest;
import jp.co.genuine.hm.api.domain.user.group.Group;
import jp.co.genuine.hm.api.domain.user.group.GroupId;
import jp.co.genuine.hm.api.domain.user.group.GroupList;

public interface GroupService {
	public Group findGroup(GroupId groupId);
	public void postGroup(PostGroupRequest request);
	public void putGroup(GroupId groupId, PutGroupRequest request);
	public void deleteGroup(GroupId groupId);
	public void postGroupManager(PostGroupManagerRequest request);
	public void postGroupMember(PostGroupMemberRequest request);
	public void deleteGroupManager(DeleteGroupManagerRequest request);
	public void deleteGroupMember(DeleteGroupMemberRequest request);
	public Group getGroup(GroupId groupId);
	public GroupList getGroups();
}
