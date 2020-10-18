package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.NotNull;

public class PostGroupRequest {
	@NotNull
	private String groupName;

	public PostGroupRequest() {
	}

	public PostGroupRequest(@NotNull String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}
}
