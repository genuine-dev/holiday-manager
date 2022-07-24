package holiday.manager.domain.request.user;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import holiday.manager.domain.user.group.GroupId;
import holiday.manager.domain.user.group.GroupMember;
import io.swagger.annotations.ApiModelProperty;

public class PostGroupMembersRequest {
	@NotBlank
	@ApiModelProperty(example = "2", required = true)
	private String groupId;

	@NotNull
	@ApiModelProperty(required = true)
	private List<GroupMember> groupMemberList;

	public PostGroupMembersRequest(String groupId, List<GroupMember> groupMemberList) {
		this.groupId = groupId;
		this.groupMemberList = groupMemberList;
	}

	public List<GroupMember> getGroupMemberList() {
		return groupMemberList;
	}

	public GroupId getGroupId() {
		return new GroupId(groupId);
	}

}
