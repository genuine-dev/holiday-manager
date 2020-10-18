package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.NotNull;

public class PostUserRequest {
	@NotNull
	private String accountId;
	@NotNull
	private String password;
	@NotNull
	private String mailAddress;
	@NotNull
	private String userName;
	@NotNull
	private String hireDate;
	@NotNull
	private String status;

	public PostUserRequest(@NotNull String accountId, @NotNull String password, @NotNull String mailAddress,
			@NotNull String userName, @NotNull String hireDate, @NotNull String status) {
		this.accountId = accountId;
		this.password = password;
		this.mailAddress = mailAddress;
		this.userName = userName;
		this.hireDate = hireDate;
		this.status = status;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getPassword() {
		return password;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public String getHireDate() {
		return hireDate;
	}

	public String getStatus() {
		return status;
	}

}
