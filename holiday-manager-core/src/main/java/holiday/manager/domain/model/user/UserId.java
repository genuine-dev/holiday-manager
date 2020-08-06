package holiday.manager.domain.model.user;

import java.util.UUID;

public class UserId {
	private final String value;

	public UserId() {
		value = UUID.randomUUID().toString();
	}

	public UserId(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof UserId)) {
			return false;
		}
		if(value == null) {
			return ((UserId)obj).value == null;
		}

		return value.equals(((UserId)obj).value);
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
