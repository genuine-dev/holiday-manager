package jp.co.genuine.hm.model.group;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PostGroupRequest {
	@NotBlank
	@Length(max = 20)
	private String groupName;

	public PostGroupRequest() {
	}

	public PostGroupRequest(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
