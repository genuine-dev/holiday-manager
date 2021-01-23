package jp.co.genuine.hm.api.domain.request.parameter;

import jp.co.genuine.hm.api.domain.user.UserSortType;

public class Sort {
	private String value;

	public Sort(String value) {
		this.value = value;
	}

	public boolean isUserName() {
		return value.equals(UserSortType.user_name.getName());
	}

	public String getValue() {
		return value;
	}
}
