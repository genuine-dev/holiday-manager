package holiday.manager.controller.user.group;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import holiday.manager.domain.request.user.DeleteGroupManagerRequest;
import holiday.manager.domain.request.user.DeleteGroupMemberRequest;
import holiday.manager.domain.request.user.PostGroupManagerRequest;
import holiday.manager.domain.request.user.PostGroupMemberRequest;
import holiday.manager.domain.request.user.PostGroupMembersRequest;
import holiday.manager.domain.request.user.PostGroupRequest;
import holiday.manager.domain.request.user.PutGroupRequest;
import holiday.manager.domain.user.group.Group;
import holiday.manager.domain.user.group.GroupId;
import holiday.manager.domain.user.group.GroupList;
import holiday.manager.service.user.group.GroupService;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
public class GroupController {
	@Autowired
	GroupService groupService;

	@ApiOperation("グループ一覧")
	@GetMapping("/group")
	public ResponseEntity<GroupList> getGroups() {
		GroupList groupList = groupService.getGroups();
		return new ResponseEntity<GroupList>(groupList, HttpStatus.OK);
	}

	@ApiOperation("グループ詳細")
	@GetMapping("/group/{group_id}")
	public ResponseEntity<Group> getGroup(@PathVariable("group_id") @Valid GroupId groupId) {
		Group group = groupService.getGroup(groupId);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

	@ApiOperation("グループ登録")
	@PostMapping("/group")
	public void postGroup(@RequestBody @Valid PostGroupRequest request) {
		groupService.postGroup(request);
	}

	@ApiOperation("グループ更新")
	@PutMapping("/group/{group_id}")
	public void putGroup(@PathVariable("group_id") @Valid GroupId groupId,
			@RequestBody @Valid PutGroupRequest request) {
		groupService.putGroup(groupId, request);
	}

	@ApiOperation("グループ削除")
	@DeleteMapping("/group/{group_id}")
	public void deleteGroup(@PathVariable("group_id") @Valid GroupId groupId) {
		groupService.deleteGroup(groupId);
	}

	@ApiOperation("グループへのメンバー登録")
	@PostMapping("/group/members")
	public void postGroupMembers(@RequestBody @Valid PostGroupMembersRequest request) {
		groupService.postGroupMembers(request);
	}

	@ApiOperation("マネージャーのグループ登録")
	@PostMapping("/group/manager")
	public void postGroupManager(@RequestBody @Valid PostGroupManagerRequest request) {
		groupService.postGroupManager(request);
	}

	@ApiOperation("マネージャーのグループ削除")
	@DeleteMapping("/group/manager")
	public void deleteGroupManager(@RequestBody @Valid DeleteGroupManagerRequest request) {
		groupService.deleteGroupManager(request);
	}

	@ApiOperation("メンバーのグループ登録")
	@PostMapping("/group/member")
	public void postGroupMember(@RequestBody @Valid PostGroupMemberRequest request) {
		groupService.postGroupMember(request);
	}

	@ApiOperation("メンバーのグループ削除")
	@DeleteMapping("/group/member")
	public void postGroupMember(@RequestBody @Valid DeleteGroupMemberRequest request) {
		groupService.deleteGroupMember(request);
	}

}
