package jp.co.genuine.hm.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PutUserRequest {
	@Email
	@NotBlank
	@Length(max = 255)
	private String mailAddress;

	@NotBlank
	@Length(max = 20)
	private String userName;

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

}
