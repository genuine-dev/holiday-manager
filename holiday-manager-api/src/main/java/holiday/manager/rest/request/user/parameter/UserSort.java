package holiday.manager.domain.request.user.parameter;

import holiday.manager.domain.user.request.param.UserSortType;

public class UserSort {
	private String value;

	public UserSort(String value) {
		this.value = value;
	}

	public boolean isUserName() {
		return value.equals(UserSortType.user_name.getName());
	}

	public String getValue() {
		return value;
	}
}
