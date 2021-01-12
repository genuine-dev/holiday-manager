package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import jp.co.genuine.hm.api.domain.user.MailAddress;
import jp.co.genuine.hm.api.domain.user.UserName;

public class PutUserRequest {
	@Email
	@NotBlank
	@Length(max = 255)
	@ApiModelProperty(example = "example@example.com", required = true)
	private String mailAddress;

	@NotBlank
	@Length(max = 20)
	@ApiModelProperty(example = "山田 太郎", required = true)
	private String userName;

	public PutUserRequest(String mailAddress, String userName) {
		this.mailAddress = mailAddress;
		this.userName = userName;
	}

	public MailAddress getMailAddress() {
		return new MailAddress(mailAddress);
	}

	public UserName getUserName() {
		return new UserName(userName);
	}
}
