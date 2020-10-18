package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.NotNull;

public class PutGroupRequest {
	@NotNull
	private String groupName;

	public PutGroupRequest() {
	}

	public PutGroupRequest(@NotNull String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}
}
