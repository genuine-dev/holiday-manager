package jp.co.genuine.hm.api.domain.paid_leave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PaidLeaveId {
	private Integer value;

	public PaidLeaveId() {
	}

	public PaidLeaveId(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
