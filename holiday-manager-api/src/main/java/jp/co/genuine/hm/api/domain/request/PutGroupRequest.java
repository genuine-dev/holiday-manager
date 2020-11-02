package jp.co.genuine.hm.api.domain.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PutGroupRequest {
	@NotBlank
	@Valid @Length(max = 20)
	private String groupName;

	public PutGroupRequest() {
	}

	public PutGroupRequest(@NotBlank String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}
}
