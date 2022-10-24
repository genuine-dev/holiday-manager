package holiday.manager.domain.user.group;

public class GroupName {
    private String value;

    public GroupName() {
        this.value = "";
    }

    public GroupName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
