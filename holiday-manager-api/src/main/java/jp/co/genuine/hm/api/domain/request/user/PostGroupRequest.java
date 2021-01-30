package jp.co.genuine.hm.api.domain.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import jp.co.genuine.hm.api.domain.user.GroupName;

public class PostGroupRequest {
	@NotBlank
	@Length(max = 20)
	@ApiModelProperty(example = "システム開発本部", required = true)
	private String groupName;

	public PostGroupRequest() {
	}

	public PostGroupRequest(String groupName) {
		this.groupName = groupName;
	}

	public GroupName getGroupName() {
		return new GroupName(groupName);
	}
}
