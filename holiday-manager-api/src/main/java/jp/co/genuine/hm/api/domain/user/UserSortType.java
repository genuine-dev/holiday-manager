package jp.co.genuine.hm.api.domain.user;

public enum UserSortType {
	user_name("user_name");

	String name;

	UserSortType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
