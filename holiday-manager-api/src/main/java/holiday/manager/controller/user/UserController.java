package holiday.manager.controller.user;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import holiday.manager.domain.request.user.PostUserRequest;
import holiday.manager.domain.request.user.PutUserRequest;
import holiday.manager.domain.request.user.parameter.UserQueries;
import holiday.manager.domain.request.user.parameter.UserSort;
import holiday.manager.domain.request.user.parameter.UserSorts;
import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.UserList;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.validation.ContainsUserSortType;
import holiday.manager.service.user.UserService;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation("全ユーザー取得")
	@GetMapping("/user")
	public ResponseEntity<UserList> getUsers(
			@ContainsUserSortType @RequestParam(value = "sort", required = false) List<UserSort> sortList,
			@RequestParam(value = "queryusername", required = false) String userNameQuery) {
		UserList userList = userService.getUser(new UserSorts(sortList), new UserQueries(userNameQuery));
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@ApiOperation("ユーザー登録")
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.OK)
	public void postUser(@RequestBody @Valid PostUserRequest request) {
		userService.postUser(request);
	}

	@ApiOperation("ユーザー更新")
	@PutMapping("/user/{user_id}")
	@ResponseStatus(HttpStatus.OK)
	public void putUser(@PathVariable("user_id") @Valid UserId userId, @RequestBody @Valid PutUserRequest request) {
		userService.putUser(userId, request);
	}

	@ApiOperation("ユーザー削除")
	@DeleteMapping("/user/{user_id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("user_id") @Valid UserId userId) {
		userService.deleteUser(userId);
	}

	@ApiOperation("UserId（DBのシーケンス番号）から特定の一人の情報を取得")
	@GetMapping("/user/{user_id}")
	public ResponseEntity<User> getUser(@PathVariable("user_id") @Valid UserId userId) {
		User user = userService.findUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation("ユーザーステータスのキーとバリュー一覧取得")
	@GetMapping("/user/status")
	public ResponseEntity<Map<String, String>> getUserStatus() {
		Map<String, String> userStatusMap = userService.getUserStatus();
		return new ResponseEntity<Map<String, String>>(userStatusMap, HttpStatus.OK);
	}

	@ApiOperation("DBにアカウントIDが存在するかチェック")
	@GetMapping("/exist/accountId/{account_id}")
	public ResponseEntity<Boolean> getExistAccountId(@PathVariable("account_id") AccountId accountId) {
		Boolean existAccountId = userService.existAccountId(accountId);
		return new ResponseEntity<Boolean>(existAccountId, HttpStatus.OK);
	}
}
