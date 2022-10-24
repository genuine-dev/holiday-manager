package holiday.manager.domain.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.AssertTrue;

public class HireDate {
	private Date value;

	public HireDate() {
	}

	public HireDate(Date value) {
		this.value = value;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

}
