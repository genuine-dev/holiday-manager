package jp.co.genuine.hm.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserForm;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.service.user.UserService;

@RestController
@RequestMapping("user")
public class UserRestController {
	@Autowired
	UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	void postUser(@RequestBody UserForm userForm) {
		userService.create(userForm);
	}

	@PutMapping("{user_id}")
	@ResponseStatus(HttpStatus.OK)
	void putUser(@PathVariable("user_id") UserId userId, @RequestBody UserForm userForm) {
		userService.update(userId, userForm);
	}

	@GetMapping("{group_id}")
	Group getUsers(@PathVariable("group_id") GroupId groupId) {
		return userService.findGroup(groupId);
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
