package holiday.manager.domain.user.rule;

public class RuleId {
	private static final Integer DEFAULT = 1;

	private Integer value;

	public RuleId() {
		value = DEFAULT;
	}

	public Integer getValue() {
		return value;
	}
}
