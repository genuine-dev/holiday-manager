package holiday.manager.domain.user;

import javax.validation.constraints.Email;

public class MailAddress {
    @Email
    private String value;

    public MailAddress(String value) {
        this.value = value;
    }

    public MailAddress() {
        this.value = "";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
