package jp.co.genuine.hm.api.domain.user;

public enum UserStatus {
	ACTIVE("在籍"),
	LEAVE("休職中"),
	RETIRED("退職");

	private String label;

	public String getLabel() {
		return label;
	}

	private UserStatus(String label) {
		this.label = label;
	}
}