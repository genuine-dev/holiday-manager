package jp.co.genuine.hm.api.domain.annotation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.genuine.hm.api.domain.request.parameter.Sort;
import jp.co.genuine.hm.api.domain.user.UserSortType;

public class ContainsUserSortTypeValidator implements ConstraintValidator<ContainsUserSortType, List<Sort>> {
	@Override
	public void initialize(ContainsUserSortType containsEnumeration) {
	}

	@Override
	public boolean isValid(List<Sort> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		for (Sort sort : value) {
			try {
				UserSortType.valueOf(sort.getValue());
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return true;
	}

}
