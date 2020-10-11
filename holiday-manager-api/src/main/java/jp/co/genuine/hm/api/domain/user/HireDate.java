package jp.co.genuine.hm.api.domain.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
