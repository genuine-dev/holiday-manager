package holiday.manager.service.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.domain.user.User;
import holiday.manager.exception.ValidationErrorException;

@Service
public class ValidateServiceImpl implements ValidateService {
	@Autowired
	Validator validator;

	@Override
	public void validate(User user) {
		Set<ConstraintViolation<User>> errorResult = validator.validate(user);
		if (errorResult.size() > 0) {
			throw new ValidationErrorException(errorResult);
		}
	}

}
