package holiday.manager.domain.model.holiday.holiday;

public class HolidayListId {
	private final String value;

	@SuppressWarnings("unused")
	private HolidayListId() {
		value = "";
	}

	public HolidayListId(String value) {
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
		if (!(obj instanceof HolidayListId)) {
			return false;
		}
		if (value == null) {
			return ((HolidayListId) obj).value == null;
		}

		return value.equals(((HolidayListId) obj).value);
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
