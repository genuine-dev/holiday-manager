package holiday.manager.domain.model;

public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
