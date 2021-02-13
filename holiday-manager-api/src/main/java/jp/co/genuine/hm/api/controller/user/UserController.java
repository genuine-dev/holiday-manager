package jp.co.genuine.hm.api.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import jp.co.genuine.hm.api.domain.request.user.DeleteGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.DeleteGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupManagerRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupMemberRequest;
import jp.co.genuine.hm.api.domain.request.user.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.user.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.user.PutGroupRequest;
import jp.co.genuine.hm.api.domain.request.user.PutUserRequest;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSort;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.GroupList;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserStatus;
import jp.co.genuine.hm.api.domain.validation.ContainsUserSortType;
import jp.co.genuine.hm.api.service.user.UserServiceImpl;

@RestController
@Validated
public class UserController {
	@Autowired
	UserServiceImpl userService;

	@RequestMapping(path = "/user", method = RequestMethod.GET)
	@ApiOperation("全ユーザー取得")
	public ResponseEntity<UserList> getUsers(
			@ContainsUserSortType @RequestParam(value = "sort", required = false) List<UserSort> sortList,
			@RequestParam(value = "queryusername", required = false) String userNameQuery) {
		UserList userList = userService.getUser(new UserSorts(sortList), new UserQueries(userNameQuery));
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@RequestMapping(path = "/user", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー登録")
	public void postUser(@RequestBody @Valid PostUserRequest request) {
		userService.postUser(request);
	}

	@RequestMapping(path = "/user/{user_id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー更新")
	public void putUser(@PathVariable("user_id") @Valid UserId userId, @RequestBody @Valid PutUserRequest request) {
		userService.putUser(userId, request);
	}

	@RequestMapping(path = "/user/{user_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー削除")
	public void deleteUser(@PathVariable("user_id") @Valid UserId userId) {
		userService.deleteUser(userId);
	}

	@RequestMapping(path = "/user/{user_id}", method = RequestMethod.GET)
	@ApiOperation("UserId（DBのシーケンス番号）から特定の一人の情報を取得")
	public ResponseEntity<User> getUser(@PathVariable("user_id") @Valid UserId userId) {
		User user = userService.findUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(path = "/user/status", method = RequestMethod.GET)
	@ApiOperation("ユーザーステータスのキーとバリュー一覧取得")
	public ResponseEntity<Map<String, String>> getUserStatus() {
		Map<String, String> userStatusMap = new HashMap<>();
		for(UserStatus userStatus : UserStatus.values()) {
			userStatusMap.put(userStatus.name(), userStatus.getLabel());
		}
		return new ResponseEntity<Map<String, String>>(userStatusMap, HttpStatus.OK);
	}

	@RequestMapping(path = "/group", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ一覧")
	public ResponseEntity<GroupList> getGroups() {
		GroupList groupList = userService.getGroups();
		return new ResponseEntity<GroupList>(groupList, HttpStatus.OK);
	}

	@RequestMapping(path = "/group/{group_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ詳細")
	public ResponseEntity<Group> getGroup(@PathVariable("group_id") @Valid GroupId groupId) {
		Group group = userService.getGroup(groupId);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

	@RequestMapping(path = "/group", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ登録")
	public void postGroup(@RequestBody @Valid PostGroupRequest request) {
		userService.postGroup(request);
	}

	@RequestMapping(path = "/group/{group_id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ更新")
	public void putGroup(@PathVariable("group_id") @Valid GroupId groupId,
			@RequestBody @Valid PutGroupRequest request) {
		userService.putGroup(groupId, request);
	}

	@RequestMapping(path = "/group/{group_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ削除")
	public void deleteGroup(@PathVariable("group_id") @Valid GroupId groupId) {
		userService.deleteGroup(groupId);
	}

	@RequestMapping(path = "/group/manager", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("マネージャーのグループ登録")
	public void postGroupManager(@RequestBody @Valid PostGroupManagerRequest request) {
		userService.postGroupManager(request);
	}

	@RequestMapping(path = "/group/manager", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("マネージャーのグループ削除")
	public void deleteGroupManager(@RequestBody @Valid DeleteGroupManagerRequest request) {
		userService.deleteGroupManager(request);
	}

	@RequestMapping(path = "/group/member", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("メンバーのグループ登録")
	public void postGroupMember(@RequestBody @Valid PostGroupMemberRequest request) {
		userService.postGroupMember(request);
	}

	@RequestMapping(path = "/group/member", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("メンバーのグループ削除")
	public void postGroupMember(@RequestBody @Valid DeleteGroupMemberRequest request) {
		userService.deleteGroupMember(request);
	}

	@RequestMapping(path = "/exist/accountId/{account_id}", method = RequestMethod.GET)
	@ApiOperation("DBにアカウントIDが存在するかチェック")
	public ResponseEntity<Boolean> getExistAccountId(@PathVariable("account_id") AccountId accountId) {
		Boolean existAccountId = userService.existAccountId(accountId);
		return new ResponseEntity<Boolean>(existAccountId, HttpStatus.OK);
	}
}
