package jp.co.genuine.hm.api.domain.user;

public class MailAddress {
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
