package holiday.manager.domain.user;

import java.math.BigDecimal;

public class LeftoverHoliday {
	BigDecimal value;

	public LeftoverHoliday() {
	}

	public LeftoverHoliday(BigDecimal value) {
		this.value = value;
	}

	public LeftoverHoliday(String value) {
		Double doubleValue = Double.parseDouble(value);
		this.value = BigDecimal.valueOf(doubleValue);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
