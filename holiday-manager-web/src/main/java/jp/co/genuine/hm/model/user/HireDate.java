package jp.co.genuine.hm.model.user;

import java.text.SimpleDateFormat;

public class HireDate {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private String value;
	private String valueOfDate;
	private boolean validDateFormat;

	public String getValueOfDate() {
		return valueOfDate;
	}

	public void setValueOfDate(String valueOfDate) {
		this.valueOfDate = valueOfDate;
	}

	public boolean isValidDateFormat() {
		return validDateFormat;
	}

	public void setValidDateFormat(boolean validDateFormat) {
		this.validDateFormat = validDateFormat;
	}

	public HireDate() {
	}

	public HireDate(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
