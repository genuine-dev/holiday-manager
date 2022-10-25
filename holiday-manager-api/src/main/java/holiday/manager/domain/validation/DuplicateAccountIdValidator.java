package holiday.manager.domain.validation;

import holiday.manager.domain.user.account.AccountId;
import holiday.manager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicateAccountIdValidator implements ConstraintValidator<DuplicateAccountId, AccountId> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(DuplicateAccountId duplicateAccountId) {
    }

    @Override
    public boolean isValid(AccountId value, ConstraintValidatorContext context) {
        if (userService.existsAccountId(value))
            return false;

        return true;
    }

}
