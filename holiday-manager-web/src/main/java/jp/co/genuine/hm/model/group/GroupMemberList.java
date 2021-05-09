package jp.co.genuine.hm.model.group;

import java.util.List;

public class GroupMemberList {
	private List<GroupMember> groupMemberList;
	private String groupName;
	private int groupId;
	public List<GroupMember> getGroupMemberList() {
		return groupMemberList;
	}
	public void setGroupMemberList(List<GroupMember> groupMemberList) {
		this.groupMemberList = groupMemberList;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
