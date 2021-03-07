package jp.co.genuine.hm.api.domain.user.account;

import org.hibernate.validator.constraints.Length;

import jp.co.genuine.hm.api.domain.validation.DuplicateAccountId;

@DuplicateAccountId
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
