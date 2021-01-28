package jp.co.genuine.hm.service.user;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;

import jp.co.genuine.hm.model.user.User;
import jp.co.genuine.hm.model.user.UserId;
import jp.co.genuine.hm.model.user.UserList;
import jp.co.genuine.hm.model.user.UserViewModel;

public interface UserService {
	public UserList getUserList() throws IOException;
	public User getUser(UserId userId) throws IOException;
	public CloseableHttpResponse postUser(UserViewModel parameter) throws IOException;
	public CloseableHttpResponse putUser(UserId userId, UserViewModel parameter)throws IOException;
	public CloseableHttpResponse deleteUser(UserId userId)throws IOException;
	public Map<String, String> getUserStatus() throws IOException;
}
