package jp.co.genuine.hm.api.domain.paid_leave;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaidLeaveSection {
	ALL("‘S‹x"),
	MORNING("Œß‘O‹x"),
	AFTERNOON("ŒßŒã‹x");
	
	private String label;
	
	public String getLabel() {
		return label;
	}
}
