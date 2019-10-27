package jp.co.genuine.hm.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.model.user.UserAccount;
import jp.co.genuine.hm.repository.user.UserAccountRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserAccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		init();
		UserAccount user = null;

		try {
			user = repository.findOne(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("It could not be got the user");
		}

		if(user == null) {
			throw new UsernameNotFoundException("User not fround. UserId:"+ username);
		}
		return new LoginUser(user);
	}

	private void init() {
		UserAccount user = repository.findOne("admin");
		if(user == null) {
			repository.register("admin", new BCryptPasswordEncoder().encode("pass"));
		}
	}

}
