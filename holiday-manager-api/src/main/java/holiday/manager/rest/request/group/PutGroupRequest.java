package holiday.manager.rest.request.group;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class PutGroupRequest {
	@NotBlank
	@Valid
	@Length(max = 20)
	@ApiModelProperty(example = "システム開発本部", required = true)
	private String groupName;

	public PutGroupRequest() {
	}

	public PutGroupRequest(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}
}
