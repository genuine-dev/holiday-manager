package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.NotNull;

public class PostUserRequest {
	@NotNull
	private String userId;
	@NotNull
	private String mailAddress;
	@NotNull
	private String userName;
	@NotNull
	private String groupId;
	@NotNull
	private String password;

	public PostUserRequest(@NotNull String userId, @NotNull String mailAddress, @NotNull String userName,
			@NotNull String groupId, @NotNull String password) {
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.userName = userName;
		this.groupId = groupId;
		this.password = password;
	}

	public PostUserRequest() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
