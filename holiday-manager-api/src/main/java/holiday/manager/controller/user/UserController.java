package holiday.manager.controller.user;

import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.rest.request.user.PostUserRequest;
import holiday.manager.rest.request.user.PutUserRequest;
import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSort;
import holiday.manager.rest.request.user.parameter.UserSorts;
import holiday.manager.rest.response.user.UserResponse;
import holiday.manager.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("全ユーザー取得")
    @GetMapping("/user")
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(value = "sort", required = false) List<UserSort> sortList,
            @RequestParam(value = "username", required = false) String userName) {
        List<UserResponse> userResponses = userService.getUser(new UserSorts(sortList), new UserQueries(userName));
        return new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.OK);
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
    public void putUser(@PathVariable("user_id") @Valid Integer userId, @RequestBody @Valid PutUserRequest request) {
        userService.putUser(new UserId(userId), request);
    }

    @ApiOperation("ユーザー削除")
    @DeleteMapping("/user/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("user_id") @Valid Integer userId) {
        userService.deleteUser(new UserId(userId));
    }

    @ApiOperation("ユーザーIDからユーザーを取得")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("user_id") Integer userId) {
        UserResponse userResponse = userService.findUser(new UserId(userId));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @ApiOperation("ユーザーステータスのキーとバリュー一覧取得")
    @GetMapping("/user/status")
    public ResponseEntity<Map<String, String>> getUserStatus() {
        Map<String, String> userStatusMap = userService.getUserStatus();
        return new ResponseEntity<Map<String, String>>(userStatusMap, HttpStatus.OK);
    }

    @ApiOperation("DBにアカウントIDが存在するかチェック")
    @GetMapping("/exist/accountId/{account_id}")
    public ResponseEntity<Boolean> getExistAccountId(@PathVariable("account_id") String accountId) {
        Boolean existAccountId = userService.existsAccountId(new AccountId(accountId));
        return new ResponseEntity<Boolean>(existAccountId, HttpStatus.OK);
    }
}
