package holiday.manager.domain.user;

public class UserId {
    private Integer value;

    public UserId(Integer value) {
        this.value = value;
    }

    public UserId() {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
