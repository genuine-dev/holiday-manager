package jp.co.genuine.hm.api.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import jp.co.genuine.hm.api.domain.user.User;

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
