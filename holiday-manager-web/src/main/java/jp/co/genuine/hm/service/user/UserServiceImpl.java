package jp.co.genuine.hm.service.user;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.genuine.hm.model.user.PutUserRequest;
import jp.co.genuine.hm.model.user.User;
import jp.co.genuine.hm.model.user.UserId;
import jp.co.genuine.hm.model.user.UserList;
import jp.co.genuine.hm.model.user.UserViewModel;

@Service
public class UserServiceImpl implements UserService {
	CloseableHttpClient client = HttpClients.createDefault();
	ObjectMapper mapper = new ObjectMapper();
	String contentType = "content-type";
	String headerValue = "application/json; charset=UTF-8";
	static String API_ROOT = "http://localhost:8082/";


	@Override
	public UserList getUserList() throws IOException {
		String url = API_ROOT + "user";
		UserList result = new UserList();

		HttpGet request = new HttpGet(url);
		request.addHeader(contentType, headerValue);
		try (CloseableHttpResponse response = client.execute(request);) {
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String jsonString = EntityUtils.toString(entity);
				result = mapper.readValue(jsonString, UserList.class);

				return result;
			} else {
				return result;
			}
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public User getUser(UserId userId) throws IOException {
		String url = API_ROOT + "user/" + userId.getValue();
		User result = new User();

		HttpGet request = new HttpGet(url);
		request.addHeader(contentType, headerValue);
		try (CloseableHttpResponse response = client.execute(request);) {
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String jsonString = EntityUtils.toString(entity);
				result = mapper.readValue(jsonString, User.class);

				return result;
			} else {
				return result;
			}
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public CloseableHttpResponse postUser(UserViewModel parameter) throws IOException {
		String url = API_ROOT + "user";
		String json;
		try {
			json = mapper.writeValueAsString(parameter);
			StringEntity entity = new StringEntity(json, "UTF-8");
			HttpPost request = new HttpPost(url);
			request.addHeader(contentType, headerValue);
			request.setEntity(entity);
			try (CloseableHttpResponse response = client.execute(request)) {
				return response;
			} catch (IOException e){
				throw e;
			}
		} catch (JsonProcessingException e) {
			throw e;
		}
	}

	@Override
	public CloseableHttpResponse putUser(UserId userId, UserViewModel parameter) throws IOException {
		String url = API_ROOT + "user/" + userId.getValue();
		String json;
		try {
			// TODO:引数の項目で更新できるようにする
			PutUserRequest param = new PutUserRequest();
			param.setMailAddress(parameter.getMailAddress());
			param.setUserName(parameter.getUserName());
			json = mapper.writeValueAsString(param);
			StringEntity entity = new StringEntity(json, "UTF-8");
			HttpPut request = new HttpPut(url);
			request.addHeader(contentType, headerValue);
			request.setEntity(entity);
			try (CloseableHttpResponse response = client.execute(request)) {
				return response;
			} catch (IOException e){
				throw e;
			}
		} catch (JsonProcessingException e) {
			throw e;
		}
	}

	@Override
	public CloseableHttpResponse deleteUser(UserId userId) throws IOException {
		String url = API_ROOT + "user/" + userId.getValue();
		try {
			HttpDelete request = new HttpDelete(url);
			request.addHeader(contentType, headerValue);
			try (CloseableHttpResponse response = client.execute(request)) {
				return response;
			} catch (IOException e){
				throw e;
			}
		} catch (JsonProcessingException e) {
			throw e;
		}
	}

	@Override
	public Map<String, String> getUserStatus() throws IOException {
		String url = API_ROOT + "user/status";
		Map<String, String>result = new LinkedHashMap<String, String>();

		HttpGet request = new HttpGet(url);
		request.addHeader(contentType, headerValue);
		try (CloseableHttpResponse response = client.execute(request);) {
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String jsonString = EntityUtils.toString(entity);
				result = mapper.readValue(jsonString, Map.class);

				return result;
			} else {
				return result;
			}
		} catch (IOException e) {
			throw e;
		}
	}

}
