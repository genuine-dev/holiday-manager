package holiday.manager.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import holiday.manager.domain.user.account.AccountId;
import holiday.manager.service.user.UserService;

public class DuplicateAccountIdValidator implements ConstraintValidator<DuplicateAccountId, AccountId> {

	@Autowired
	UserService userService;

	@Override
	public void initialize(DuplicateAccountId duplicateAccountId) {
	}

	@Override
	public boolean isValid(AccountId value, ConstraintValidatorContext context) {
		if (userService.existAccountId(value)) {
			return false;
		}
		return true;
	}

}
