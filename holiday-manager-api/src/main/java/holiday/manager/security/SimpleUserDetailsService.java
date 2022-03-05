package holiday.manager.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserRepository;

@Service("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("user not found.");
		}
		return new SimpleLoginUser(user.get());
	}

}
