package jp.co.genuine.hm.model.rule;

public class RuleId {
	private static final Integer DEFAULT = 1;

	private Integer value;

	public RuleId() {
		value = DEFAULT;
	}

	public RuleId(Integer value) {
		this.value = value;
	}
}
