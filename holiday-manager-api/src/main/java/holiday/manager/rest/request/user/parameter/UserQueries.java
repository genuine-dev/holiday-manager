package holiday.manager.rest.request.user.parameter;

public class UserQueries {
	private String userName;

	public UserQueries(String userName) {
		this.userName = userName;
	}

	public boolean isEmptyUserName() {
		if(userName == null || userName.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean isNotEmptyUserName() {
		return !isEmptyUserName();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
