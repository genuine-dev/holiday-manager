package jp.co.genuine.hm.service.user;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.genuine.hm.model.user.RegisterUserRequest;
import jp.co.genuine.hm.model.user.UserList;

@Service
public class UserServiceImpl implements UserService {
	CloseableHttpClient client = HttpClients.createDefault();
	ObjectMapper mapper = new ObjectMapper();
	String contentType = "content-type";
	String headerValue = "application/json; charset=UTF-8";
	static String API_ROOT = "http://holiday-manager.genuine-pt.jp/";


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
	public CloseableHttpResponse postUser(RegisterUserRequest parameter) throws IOException {
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

}
