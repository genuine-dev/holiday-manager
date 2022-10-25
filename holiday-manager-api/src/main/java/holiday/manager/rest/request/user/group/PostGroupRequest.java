package holiday.manager.rest.request.user.group;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import holiday.manager.domain.user.group.GroupName;
import io.swagger.annotations.ApiModelProperty;

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

	public String getGroupName() {
		return groupName;
	}
}
