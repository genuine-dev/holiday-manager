package jp.co.genuine.hm.api.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.genuine.hm.api.domain.request.PostUserRequest;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.service.user.UserService;

@RestController
@RequestMapping("user")
public class UserRestController {
	@Autowired
	UserService userService;

	@PostMapping("member")
	@ResponseStatus(HttpStatus.OK)
	void postMember(@RequestBody @Valid PostUserRequest request) {
		userService.postMember(request);
	}

	@PutMapping("{user_id}")
	@ResponseStatus(HttpStatus.OK)
	void putUser(@PathVariable("user_id") UserId userId, @RequestBody UserForm userForm) {
		userService.update(userId, userForm);
	}

	@GetMapping("{group_id}")
	@ResponseBody
	ResponseEntity<UserList> getUsers(@PathVariable("group_id") GroupId groupId) {
		UserList userList = userService.findUsers(groupId);
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@PostMapping("group")
	@ResponseStatus(HttpStatus.OK)
	void postGroup(@RequestBody UserForm userForm) {
		userService.createGroup(userForm);
	}

	@PutMapping("group/{group_id}")
	@ResponseStatus(HttpStatus.OK)
	void putGroup(@PathVariable("group_id") GroupId groupId, @RequestBody UserForm userForm) {
		userService.updateGroup(groupId, userForm);
	}
}
