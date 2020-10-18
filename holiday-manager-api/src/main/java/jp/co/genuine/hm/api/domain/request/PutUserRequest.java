package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.NotNull;

public class PutUserRequest {
	@NotNull
	private String mailAddress;

	@NotNull
	private String userName;

	public PutUserRequest(@NotNull String mailAddress, @NotNull String userName) {
		this.mailAddress = mailAddress;
		this.userName = userName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getUserName() {
		return userName;
	}
}
