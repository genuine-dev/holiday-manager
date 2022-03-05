package holiday.manager.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import holiday.manager.domain.user.User;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<List<String>> handleValidationErrorException(ValidationErrorException exeption) {
		List<String> messages = new ArrayList<>();
		for(ConstraintViolation<User> errors : exeption.getErrorResult()) {
			messages.add(errors.getMessage());
		}
		return new ResponseEntity<List<String>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
