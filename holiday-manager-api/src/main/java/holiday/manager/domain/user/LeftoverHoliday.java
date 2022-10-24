package holiday.manager.domain.user;

import java.math.BigDecimal;

public class LeftoverHoliday {
	Double value;

	public LeftoverHoliday() {
	}

	public LeftoverHoliday(BigDecimal value) {
		this.value = value.doubleValue();
	}

	public LeftoverHoliday(Double value) {
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
