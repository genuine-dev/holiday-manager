package holiday.manager.domain.model.holiday;

import java.util.UUID;

public class HolidayApplicationId {
	private final String value;

	public HolidayApplicationId() {
		value = UUID.randomUUID().toString();
	}

	public HolidayApplicationId(String value) {
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
		if (!(obj instanceof HolidayApplicationId)) {
			return false;
		}
		if (value == null) {
			return ((HolidayApplicationId) obj).value == null;
		}

		return value.equals(((HolidayApplicationId) obj).value);
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
