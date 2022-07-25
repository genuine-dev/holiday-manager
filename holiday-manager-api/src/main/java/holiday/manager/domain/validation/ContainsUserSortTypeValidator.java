package holiday.manager.domain.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import holiday.manager.rest.request.user.parameter.UserSort;
import holiday.manager.domain.user.request.param.UserSortType;

public class ContainsUserSortTypeValidator implements ConstraintValidator<ContainsUserSortType, List<UserSort>> {
	@Override
	public void initialize(ContainsUserSortType containsEnumeration) {
	}

	@Override
	public boolean isValid(List<UserSort> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		for (UserSort sort : value) {
			try {
				UserSortType.valueOf(sort.getValue());
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return true;
	}

}
