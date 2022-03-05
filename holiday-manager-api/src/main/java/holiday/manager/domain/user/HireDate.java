package holiday.manager.domain.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.AssertTrue;

public class HireDate {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private String value;

	public HireDate() {
	}

	public HireDate(String value) {
		this.value = value;
	}

	public Date getValueOfDate() throws ParseException {
		return format.parse(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@AssertTrue(message = "日付の形式が正しくありません。")
	public boolean isValidDateFormat() {
		SimpleDateFormat format = HireDate.format;
		format.setLenient(false);
		try {
			format.parse(value);
		} catch(ParseException e) {
			return false;
		}
		return true;
	}

}
