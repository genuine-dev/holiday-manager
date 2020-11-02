package jp.co.genuine.hm.api.domain.user;

import javax.validation.constraints.Max;

public class GroupId {
	@Max(999)
	private Integer value;

	public GroupId() {
	}

	public GroupId(String groupId) {
		this.value = Integer.valueOf(groupId);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
