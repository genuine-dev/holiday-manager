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
import jp.co.genuine.hm.api.domain.request.user.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.user.PutUserRequest;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSort;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;
import jp.co.genuine.hm.api.domain.user.UserStatus;
import jp.co.genuine.hm.api.domain.user.account.AccountId;
import jp.co.genuine.hm.api.domain.validation.ContainsUserSortType;
import jp.co.genuine.hm.api.service.user.UserService;

@Validated
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation("全ユーザー取得")
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public ResponseEntity<UserList> getUsers(
			@ContainsUserSortType @RequestParam(value = "sort", required = false) List<UserSort> sortList,
			@RequestParam(value = "queryusername", required = false) String userNameQuery) {
		UserList userList = userService.getUser(new UserSorts(sortList), new UserQueries(userNameQuery));
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@ApiOperation("ユーザー登録")
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void postUser(@RequestBody @Valid PostUserRequest request) {
		userService.postUser(request);
	}

	@ApiOperation("ユーザー更新")
	@RequestMapping(path = "/user/{user_id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void putUser(@PathVariable("user_id") @Valid UserId userId, @RequestBody @Valid PutUserRequest request) {
		userService.putUser(userId, request);
	}

	@ApiOperation("ユーザー削除")
	@RequestMapping(path = "/user/{user_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("user_id") @Valid UserId userId) {
		userService.deleteUser(userId);
	}

	@ApiOperation("UserId（DBのシーケンス番号）から特定の一人の情報を取得")
	@RequestMapping(path = "/user/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("user_id") @Valid UserId userId) {
		User user = userService.findUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation("ユーザーステータスのキーとバリュー一覧取得")
	@RequestMapping(path = "/user/status", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> getUserStatus() {
		Map<String, String> userStatusMap = new HashMap<>();
		for (UserStatus userStatus : UserStatus.values()) {
			userStatusMap.put(userStatus.name(), userStatus.getLabel());
		}
		return new ResponseEntity<Map<String, String>>(userStatusMap, HttpStatus.OK);
	}

	@ApiOperation("DBにアカウントIDが存在するかチェック")
	@RequestMapping(path = "/exist/accountId/{account_id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> getExistAccountId(@PathVariable("account_id") AccountId accountId) {
		Boolean existAccountId = userService.existAccountId(accountId);
		return new ResponseEntity<Boolean>(existAccountId, HttpStatus.OK);
	}
}
