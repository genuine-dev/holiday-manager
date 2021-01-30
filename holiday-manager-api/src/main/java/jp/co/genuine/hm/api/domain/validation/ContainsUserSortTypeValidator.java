package jp.co.genuine.hm.api.domain.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.genuine.hm.api.domain.request.user.parameter.UserSort;
import jp.co.genuine.hm.api.domain.user.UserSortType;

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
