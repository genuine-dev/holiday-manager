package jp.co.genuine.hm.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.model.user.UserAccount;
import jp.co.genuine.hm.repository.security.UserAccountRepository;

/**
 * ログイン用サービス
 *
 * @author JUNYA
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserAccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = null;

		try {
			user = repository.findOne(username);
			if(user == null) {
				throw new UsernameNotFoundException("User not found. UserId:"+ username);
			}

			if(user.isAdminFlg()) {
				user.setRole("ADMIN");
			} else {
				user.setRole("USER");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("It could not be got the user");
		}

		return new LoginUser(user);
	}
}
