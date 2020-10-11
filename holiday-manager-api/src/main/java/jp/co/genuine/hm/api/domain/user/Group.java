package jp.co.genuine.hm.api.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Group {
	private GroupId groupId;
	private GroupName groupName;
	private UserList managerList;
	private UserList memberList;

	public Group() {
	}

	public Group(GroupId groupId, GroupName groupName, UserList managerList, UserList memberList) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.managerList = managerList;
		this.memberList = memberList;
	}

	public GroupId getGroupId() {
		return groupId;
	}

	public void setGroupId(GroupId groupId) {
		this.groupId = groupId;
	}

	public GroupName getGroupName() {
		return groupName;
	}

	public void setGroupName(GroupName groupName) {
		this.groupName = groupName;
	}

	public UserList getManagerList() {
		return managerList;
	}

	public void setManagerList(UserList managerList) {
		this.managerList = managerList;
	}

	public UserList getMemberList() {
		return memberList;
	}

	public void setMemberList(UserList memberList) {
		this.memberList = memberList;
	}
}
