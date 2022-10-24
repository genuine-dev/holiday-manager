package holiday.manager.domain.user.account;

import holiday.manager.domain.validation.DuplicateAccountId;
import org.hibernate.validator.constraints.Length;

@DuplicateAccountId
public class AccountId {
    @Length(max = 20)
    String value;

    public AccountId(String value) {
        this.value = value;
    }

    public AccountId() {
        this.value = "";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
