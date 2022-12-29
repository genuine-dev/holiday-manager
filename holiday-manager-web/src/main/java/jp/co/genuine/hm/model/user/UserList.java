package jp.co.genuine.hm.model.user;

import java.util.List;

public class UserList {
	private List<User> userList;

	public UserList() {
	}

	public UserList(List<User> userList) {
		this.userList = userList;
	}

	public User getByUserId(UserId userId) {
		for(User user : userList)
			if(user.userId().value().equals(userId.value()))
				return user;

		return new User();
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
