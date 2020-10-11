package jp.co.genuine.hm.api.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
