package holiday.manager.domain.validation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DuplicateAccountIdValidator.class})
public @interface DuplicateAccountId {
	String message() default "既に存在します";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
