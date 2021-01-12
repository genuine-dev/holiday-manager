package jp.co.genuine.hm.model.paid_leave;

public enum PaidLeaveType {
	PAID("�L���x"),
	SPECIAL("���ʋx"),
	ABSENT("����");

	private String label;

	public String getLabel() {
		return label;
	}

	private PaidLeaveType(String label) {
		this.label = label;
	}
}
