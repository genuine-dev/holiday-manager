package jp.co.genuine.hm.api.domain.user;

public class UserId {
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
