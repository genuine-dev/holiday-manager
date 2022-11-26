package holiday.manager.rest.request.group;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PostGroupMembersRequest {
	@NotNull
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
