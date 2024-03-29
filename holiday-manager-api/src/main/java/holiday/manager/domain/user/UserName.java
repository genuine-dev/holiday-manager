package holiday.manager.domain.user;

import org.hibernate.validator.constraints.Length;

public class UserName {
    @Length(max = 20)
    private String value;

    public UserName() {
        this.value = "";
    }

    public UserName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
