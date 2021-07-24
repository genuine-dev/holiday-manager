package jp.co.genuine.hm.api.domain.user;

public class UserId {
	private Integer value;

	public UserId(String value) {
		this.value = Integer.parseInt(value);
	}

	public UserId(Integer value) {
		this.value = value;
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
