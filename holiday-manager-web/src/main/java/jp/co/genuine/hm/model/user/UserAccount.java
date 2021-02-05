package jp.co.genuine.hm.model.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccount implements UserDetails {
	private int userId;
	private String accountId;
	private String password;
	private boolean adminFlg;
	private String role;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdminFlg() {
		return adminFlg;
	}
	public void setAdminFlg(boolean adminFlg) {
		this.adminFlg = adminFlg;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_" + this.role));
		return authorityList;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
