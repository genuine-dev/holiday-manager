package holiday.manager.rest.request.user.group;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class PostGroupMembersRequest {
	@NotBlank
	@ApiModelProperty(example = "2", required = true)
	private Integer groupId;

	@NotNull
	@ApiModelProperty(required = true)
	private List<GroupMember> groupMemberList;

	public PostGroupMembersRequest(Integer groupId, List<GroupMember> groupMemberList) {
		this.groupId = groupId;
		this.groupMemberList = groupMemberList;
	}

	public List<GroupMember> getGroupMemberList() {
		return groupMemberList;
	}

	public Integer getGroupId() {
		return groupId;
	}

}
