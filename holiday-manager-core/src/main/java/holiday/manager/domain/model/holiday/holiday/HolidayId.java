package holiday.manager.domain.model.holiday.holiday;

import java.util.UUID;

public class HolidayId {
	private final String value;

	public HolidayId() {
		value = UUID.randomUUID().toString();
	}

	public HolidayId(String value) {
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
		if (!(obj instanceof HolidayId)) {
			return false;
		}
		if (value == null) {
			return ((HolidayId) obj).value == null;
		}

		return value.equals(((HolidayId) obj).value);
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
