package jp.co.genuine.hm.api.domain.user;

import jp.co.genuine.hm.api.domain.common.MailAddress;
import jp.co.genuine.hm.api.domain.common.Password;

public class UserForm {
	private UserId userId;
	private UserStatus userStatus;
	private MailAddress mailAddress;
	private UserName userName;
	private GroupId groupId;
	private GroupName groupName;
	private Password password;

	public UserForm(UserId userId, MailAddress mailAddress, UserName userName, UserStatus userStatus, GroupId groupId, Password password) {
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.userName = userName;
		this.userStatus = userStatus;
		this.groupId = groupId;
		this.password = password;
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public MailAddress getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}

	public UserName getUserName() {
		return userName;
	}

	public void setUserName(UserName userName) {
		this.userName = userName;
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

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}
}
