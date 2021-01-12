package jp.co.genuine.hm.model.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HireDate {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private Date value;

	public HireDate() {
	}

	public HireDate(Date value) {
		this.value = value;
	}

	public HireDate(String hireDate) throws ParseException {
		value = format.parse(hireDate);
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}
}
