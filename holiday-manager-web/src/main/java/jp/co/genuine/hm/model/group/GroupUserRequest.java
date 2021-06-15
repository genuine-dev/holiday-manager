package jp.co.genuine.hm.model.group;

import javax.validation.constraints.NotBlank;

import jp.co.genuine.hm.model.user.UserId;

public class GroupUserRequest {

	@NotBlank
	private String userId;

	@NotBlank
	private String groupId;

	public GroupUserRequest(String userId, String groupId) {
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
