package holiday.manager.domain.model.user;

public class UserId {
	private final Integer value;

	public UserId() {
		value = 0;
	}

	public UserId(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
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
		return value.toString();
	}
}
