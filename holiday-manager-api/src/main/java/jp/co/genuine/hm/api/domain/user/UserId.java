package jp.co.genuine.hm.api.domain.user;

import javax.validation.constraints.Max;

public class UserId {
	@Max(999)
	private Integer value;

	public UserId(String userId) {
		this.value = Integer.valueOf(userId);
	}

	public UserId() {
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
