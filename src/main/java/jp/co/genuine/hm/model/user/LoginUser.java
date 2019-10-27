package jp.co.genuine.hm.model.user;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

	private final UserAccount account;

	public LoginUser(UserAccount account) {
		super(account.getUserId(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.account = account;
	}

	public UserAccount getAccount() {
		return account;
	}
}
