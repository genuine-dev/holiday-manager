package jp.co.genuine.hm.api.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import jp.co.genuine.hm.api.domain.request.user.DeleteGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.DeleteGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.user.PutGroupRequest;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupList;
import jp.co.genuine.hm.api.service.user.GroupService;

@RestController
@Validated
public class GroupController {
	@Autowired
	GroupService groupService;

	@RequestMapping(path = "/group", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ一覧")
	public ResponseEntity<GroupList> getGroups() {
		GroupList groupList = groupService.getGroups();
		return new ResponseEntity<GroupList>(groupList, HttpStatus.OK);
	}

	@RequestMapping(path = "/group/{group_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ詳細")
	public ResponseEntity<Group> getGroup(@PathVariable("group_id") @Valid GroupId groupId) {
		Group group = groupService.getGroup(groupId);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

	@RequestMapping(path = "/group", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ登録")
	public void postGroup(@RequestBody @Valid PostGroupRequest request) {
		groupService.postGroup(request);
	}

	@RequestMapping(path = "/group/{group_id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ更新")
	public void putGroup(@PathVariable("group_id") @Valid GroupId groupId,
			@RequestBody @Valid PutGroupRequest request) {
		groupService.putGroup(groupId, request);
	}

	@RequestMapping(path = "/group/{group_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ削除")
	public void deleteGroup(@PathVariable("group_id") @Valid GroupId groupId) {
		groupService.deleteGroup(groupId);
	}

	@RequestMapping(path = "/group/manager", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("マネージャーのグループ登録")
	public void postGroupManager(@RequestBody @Valid PostGroupManagerRequest request) {
		groupService.postGroupManager(request);
	}

	@RequestMapping(path = "/group/manager", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("マネージャーのグループ削除")
	public void deleteGroupManager(@RequestBody @Valid DeleteGroupManagerRequest request) {
		groupService.deleteGroupManager(request);
	}

	@RequestMapping(path = "/group/member", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("メンバーのグループ登録")
	public void postGroupMember(@RequestBody @Valid PostGroupMemberRequest request) {
		groupService.postGroupMember(request);
	}

	@RequestMapping(path = "/group/member", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("メンバーのグループ削除")
	public void postGroupMember(@RequestBody @Valid DeleteGroupMemberRequest request) {
		groupService.deleteGroupMember(request);
	}

}
