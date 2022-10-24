package holiday.manager.rest.request.user.parameter;

import holiday.manager.domain.user.request.param.UserSortType;

public enum UserSort {

	user_name
	;

	public boolean isUserName() {
		return this == user_name;
	}
}
