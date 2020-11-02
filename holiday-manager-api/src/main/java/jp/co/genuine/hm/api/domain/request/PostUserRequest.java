package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PostUserRequest {
	@NotBlank
	@Length(max = 20)
	private String accountId;
	@NotBlank
	@Length(max = 20)
	private String password;
	@NotBlank
	@Email
	@Length(max = 20)
	private String mailAddress;
	@NotBlank
	@Length(max = 20)
	private String userName;
	@NotBlank
	@Length(max = 20)
	private String hireDate;
	@NotBlank
	@Length(max = 20)
	private String status;

	public PostUserRequest(@NotBlank String accountId, @NotBlank String password, @NotBlank String mailAddress,
			@NotBlank String userName, @NotBlank String hireDate, @NotBlank String status) {
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
