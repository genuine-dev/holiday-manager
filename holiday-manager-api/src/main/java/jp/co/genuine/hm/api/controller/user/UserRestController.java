package jp.co.genuine.hm.api.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import jp.co.genuine.hm.api.domain.request.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.PutGroupRequest;
import jp.co.genuine.hm.api.domain.request.PutUserRequest;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.service.user.UserServiceImpl;

@RestController
@RequestMapping("user")
@Validated
public class UserRestController {
	@Autowired
	UserServiceImpl userService;

	@GetMapping
	@ResponseBody
	@ApiOperation("全ユーザー取得")
	ResponseEntity<UserList> getUser() {
		UserList userList = userService.getUser();
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー登録")
	void postUser(@RequestBody @Valid PostUserRequest request) {
		userService.postUser(request);
	}

	@PutMapping("{user_id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("ユーザー更新")
	void putUser(@PathVariable("user_id") @Valid UserId userId, @RequestBody @Valid PutUserRequest request) {
		userService.putUser(userId, request);
	}

	@GetMapping("{group_id}")
	@ResponseBody
	@ApiOperation("グループIDに紐づくユーザー取得")
	ResponseEntity<UserList> getUsers(@PathVariable("group_id") @Valid GroupId groupId) {
		UserList userList = userService.findUsers(groupId);
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@PostMapping("group")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ登録")
	void postGroup(@RequestBody @Valid PostGroupRequest request) {
		userService.postGroup(request);
	}

	@PutMapping("group/{group_id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("グループ更新")
	void putGroup(@PathVariable("group_id") @Valid GroupId groupId,
			@RequestBody @Valid PutGroupRequest request) {
		userService.putGroup(groupId, request);
	}
}
