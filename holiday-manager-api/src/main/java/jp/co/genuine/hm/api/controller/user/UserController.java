package jp.co.genuine.hm.api.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import jp.co.genuine.hm.api.domain.annotation.ContainsUserSortType;
import jp.co.genuine.hm.api.domain.request.PostGroupOfManagerRequest;
import jp.co.genuine.hm.api.domain.request.PostGroupOfMemberRequest;
import jp.co.genuine.hm.api.domain.request.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.PutGroupRequest;
import jp.co.genuine.hm.api.domain.request.PutUserRequest;
import jp.co.genuine.hm.api.domain.request.parameter.Sort;
import jp.co.genuine.hm.api.domain.request.parameter.Sorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserStatus;
import jp.co.genuine.hm.api.service.user.UserServiceImpl;

@RestController
@Validated
public class UserController {
	@Autowired
	UserServiceImpl userService;

	@GetMapping("user")
	@ResponseBody
	@ApiOperation("全ユーザー取得")
	public ResponseEntity<UserList> getUsers(@ContainsUserSortType @RequestParam(value = "sort", required = false) List<Sort> sortList) {
		UserList userList = userService.getUser(new Sorts(sortList));
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@PostMapping("user")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー登録")
	public void postUser(@RequestBody @Valid PostUserRequest request) {
		userService.postUser(request);
	}

	@PutMapping("user/{user_id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー更新")
	public void putUser(@PathVariable("user_id") @Valid UserId userId, @RequestBody @Valid PutUserRequest request) {
		userService.putUser(userId, request);
	}

	@DeleteMapping("user/{user_id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー削除")
	public void deleteUser(@PathVariable("user_id") @Valid UserId userId) {
		userService.deleteUser(userId);
	}

	@GetMapping("user/{user_id}")
	@ResponseBody
	@ApiOperation("UserId（DBのシーケンス番号）から特定の一人の情報を取得")
	public ResponseEntity<User> getUser(@PathVariable("user_id") @Valid UserId userId) {
		User user = userService.findUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("user/status")
	@ResponseBody
	@ApiOperation("ユーザーステータスのキーとバリュー一覧取得")
	public ResponseEntity<Map<String, String>> getUserStatus() {
		Map<String, String> userStatusMap = new HashMap<>();
		for(UserStatus userStatus : UserStatus.values()) {
			userStatusMap.put(userStatus.name(), userStatus.getLabel());
		}
		return new ResponseEntity<Map<String, String>>(userStatusMap, HttpStatus.OK);
	}

	@PostMapping("group")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ登録")
	public void postGroup(@RequestBody @Valid PostGroupRequest request) {
		userService.postGroup(request);
	}

	@PutMapping("group/{group_id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ更新")
	public void putGroup(@PathVariable("group_id") @Valid GroupId groupId,
			@RequestBody @Valid PutGroupRequest request) {
		userService.putGroup(groupId, request);
	}

	@DeleteMapping("group/{group_id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ削除")
	public void deleteGroup(@PathVariable("group_id") @Valid GroupId groupId) {
		userService.deleteGroup(groupId);
	}

	@PostMapping("group/manager")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("マネージャーのグループ登録")
	public void postGroupOfManager(@RequestBody @Valid PostGroupOfManagerRequest request) {
		userService.postGroupOfManager(request);
	}

	@PostMapping("group/member")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("メンバーのグループ登録")
	public void postGroupOfMember(@RequestBody @Valid PostGroupOfMemberRequest request) {
		userService.postGroupOfMember(request);
	}

	@GetMapping("exist/accountId/{account_id}")
	@ResponseBody
	@ApiOperation("DBにアカウントIDが存在するかチェック")
	public ResponseEntity<Boolean> getExistAccountId(@PathVariable("account_id") AccountId accountId) {
		Boolean existAccountId = userService.existAccountId(accountId);
		return new ResponseEntity<Boolean>(existAccountId, HttpStatus.OK);
	}
}
