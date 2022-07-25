package holiday.manager.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import holiday.manager.rest.request.user.PostUserRequest;
import holiday.manager.rest.request.user.PutUserRequest;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.account.Password;
import holiday.manager.domain.user.rule.RuleId;

@Component
public class UserFactory {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public User create(PostUserRequest request) {
		AccountId accountId = new AccountId(request.getAccountId());
		UserId userId = userRepository.nextUserId();
		MailAddress mailAddress = new MailAddress(request.getMailAddress());
		Password password = encodedPassword(request.getPassword());
		UserName userName = new UserName(request.getUserName());
		UserStatus userStatus = UserStatus.valueOf(request.getStatus());
		HireDate hireDate = new HireDate(request.getHireDate());;
		LeftoverHoliday leftoverHoliday = new LeftoverHoliday(request.getLeftoverHoliday());
		RuleId ruleId = new RuleId();

		return new User(accountId, userId, mailAddress, password, userName, userStatus, hireDate, leftoverHoliday, ruleId);
	}

	private Password encodedPassword(String value) {
		String encodedPassword = passwordEncoder.encode(value);

		return new Password(encodedPassword);
	}

	public User create(UserId userId, PutUserRequest request) {
		MailAddress mailAddress = new MailAddress(request.getMailAddress());
		Password password = password(request.getPassword());
		UserName userName = new UserName(request.getUserName());
		UserStatus userStatus = UserStatus.valueOf(request.getStatus());
		HireDate hireDate = new HireDate(request.getHireDate());;
		LeftoverHoliday leftoverHoliday = new LeftoverHoliday(request.getLeftoverHoliday());

		return new User(userId, mailAddress, password, userName, userStatus, hireDate, leftoverHoliday);
	}

	private Password password(String password) {
		if (password == null || password.isEmpty())
			return new Password();

		return encodedPassword(password);
	}

}
