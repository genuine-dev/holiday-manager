package holiday.manager.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import holiday.manager.domain.user.User;

public class ValidationErrorException extends RuntimeException {
	private Set<ConstraintViolation<User>> errorResult;

	public ValidationErrorException(Set<ConstraintViolation<User>> errorResult) {
		this.errorResult = errorResult;
	}

	public Set<ConstraintViolation<User>> getErrorResult() {
		return errorResult;
	}

	public void setErrorResult(Set<ConstraintViolation<User>> errorResult) {
		this.errorResult = errorResult;
	}

}
