package holiday.manager.domain.model.user;

public class UserName {
	private final String value;

	public UserName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserName)) {
			return false;
		}
		if (value == null) {
			return ((UserName) obj).value == null;
		}

		return value.equals(((UserName) obj).value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value;
	}
}
