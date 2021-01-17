package jp.co.genuine.hm.model.user;

import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

	private final UserAccount account;

	public LoginUser(UserAccount account) {
		super(account.getAccountId(), account.getPassword(), account.getAuthorities());
		this.account = account;
	}

	public UserAccount getAccount() {
		return account;
	}
}
