package jp.co.genuine.hm.api.domain.request.user.parameter;

public class UserQueries {
	private String userNameQuery;

	public UserQueries(String userNameQuery) {
		this.userNameQuery = userNameQuery;
	}

	public boolean isEmptyUserNameQuery() {
		if(userNameQuery == null || userNameQuery.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean isNotEmptyUserNameQuery() {
		return !isEmptyUserNameQuery();
	}

	public String getUserNameQuery() {
		return userNameQuery;
	}

	public void setUserNameQuery(String userNameQuery) {
		this.userNameQuery = userNameQuery;
	}
}
