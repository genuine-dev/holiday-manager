package jp.co.genuine.hm.model.user;

import javax.validation.constraints.Email;

public class MailAddress {
	@Email
	private String value;

	public MailAddress(String value) {
		this.value = value;
	}

	public MailAddress() {
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
