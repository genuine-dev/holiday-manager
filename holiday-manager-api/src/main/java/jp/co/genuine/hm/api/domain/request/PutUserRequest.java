package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PutUserRequest {
	@Email
	@NotBlank
	@Length(max = 20)
	private String mailAddress;

	@NotBlank
	@Length(max = 20)
	private String userName;

	public PutUserRequest(@NotBlank String mailAddress, @NotBlank String userName) {
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
