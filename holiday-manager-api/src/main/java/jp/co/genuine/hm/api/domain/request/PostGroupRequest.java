package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PostGroupRequest {
	@NotBlank
	@Length(max = 20)
	private String groupName;

	public PostGroupRequest() {
	}

	public PostGroupRequest(@NotBlank String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}
}
