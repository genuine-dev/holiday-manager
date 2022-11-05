package jp.co.genuine.hm.service.user;

import jp.co.genuine.hm.model.user.User;
import jp.co.genuine.hm.model.user.UserId;
import jp.co.genuine.hm.model.user.UserList;
import jp.co.genuine.hm.model.user.UserViewModel;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface UserService {
	public UserList getUserList();
	public User getUser(UserId userId);
	public ResponseEntity<Void> postUser(UserViewModel parameter);
	public ResponseEntity<Void> putUser(UserId userId, UserViewModel parameter)throws IOException;
	public ResponseEntity<Void> deleteUser(UserId userId);
}
