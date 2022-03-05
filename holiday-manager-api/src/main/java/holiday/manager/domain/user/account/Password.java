package holiday.manager.domain.user.account;

public class Password {
	private String value;

	public Password() {
	}

	public Password(String value) {
		this.value = value;
	}

	public boolean isEmpty() {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
