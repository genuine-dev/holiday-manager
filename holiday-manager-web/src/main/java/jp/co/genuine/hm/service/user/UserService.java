package jp.co.genuine.hm.service.user;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;

import jp.co.genuine.hm.model.user.RegisterUserRequest;
import jp.co.genuine.hm.model.user.UserList;

public interface UserService {
	public UserList getUserList() throws IOException;
	public CloseableHttpResponse postUser(RegisterUserRequest parameter) throws IOException;
}
