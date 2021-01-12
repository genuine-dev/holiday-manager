package jp.co.genuine.hm.model.paid_leave;

public enum PaidLeaveSection {
	ALL("�S�x"),
	MORNING("�ߑO�x"),
	AFTERNOON("�ߌ�x");

	private String label;

	public String getLabel() {
		return label;
	}

	private PaidLeaveSection(String label) {
		this.label = label;
	}
}
