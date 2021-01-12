package jp.co.genuine.hm.model.user;

public enum UserStatusEnum {
	ACTIVE("在籍"), LEAVE("休職中"), RETIRED("退職");

	private String label;

	public String getLabel() {
		return label;
	}

	private UserStatusEnum(String label) {
			this.label = label;
		}
}
