package jp.co.genuine.hm.api.domain.user.group;

public class GroupId {
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
