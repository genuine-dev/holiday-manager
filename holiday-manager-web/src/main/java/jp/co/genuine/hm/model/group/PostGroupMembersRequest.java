package jp.co.genuine.hm.model.group;

import java.util.List;

public class PostGroupMembersRequest {
	private Integer groupId;

	private List<GroupMember> groupMemberList;


	public PostGroupMembersRequest(Integer groupId, List<GroupMember> groupMemberList) {
		this.groupId = groupId;
		this.groupMemberList = groupMemberList;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public List<GroupMember> getGroupMemberList() {
		return groupMemberList;
	}

}
