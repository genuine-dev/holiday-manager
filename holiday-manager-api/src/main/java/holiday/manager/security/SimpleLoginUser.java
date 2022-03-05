package holiday.manager.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import holiday.manager.domain.user.User;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {

	private User user;

	public User getUser() {
		return user;
	}

	public SimpleLoginUser(User user) {
		super(user.getUserName().getValue(), user.getPassword().getValue(), determineRoles(user.isAdmin()));
		this.user = user;
	}

	private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
	private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

	private static List<GrantedAuthority> determineRoles(boolean admin) {
		if(admin) {
			return ADMIN_ROLES;
		}
		return USER_ROLES;
	}
}
