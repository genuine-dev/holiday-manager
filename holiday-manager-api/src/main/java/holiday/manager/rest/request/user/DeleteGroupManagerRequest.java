package holiday.manager.domain.request.user;

import javax.validation.constraints.NotBlank;

import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.group.GroupId;
import io.swagger.annotations.ApiModelProperty;

public class DeleteGroupManagerRequest {
	@NotBlank
	@ApiModelProperty(example = "5", required = true)
	private String userId;

	@NotBlank
	@ApiModelProperty(example = "2", required = true)
	private String groupId;

	public DeleteGroupManagerRequest(String userId, String groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	public UserId getUserId() {
		return new UserId(userId);
	}

	public GroupId getGroupId() {
		return new GroupId(groupId);
	}

}
