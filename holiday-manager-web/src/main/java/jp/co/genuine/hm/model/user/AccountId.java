package jp.co.genuine.hm.model.user;

import org.hibernate.validator.constraints.Length;

public class AccountId {
	@Length(max = 20)
	String value;

	public AccountId(String value) {
		this.value = value;
	}

	public AccountId() {
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
