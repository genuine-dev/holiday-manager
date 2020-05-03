package jp.co.genuine.hm.api.domain.paid_leave;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaidLeaveType {
	PAID("—L‹‹‹x"),
	SPECIAL("“Á•Ê‹x"),
	ABSENT("Œ‡‹Î");
	
	private String label;
	
	public String getLabel() {
		return label;
	}
}
