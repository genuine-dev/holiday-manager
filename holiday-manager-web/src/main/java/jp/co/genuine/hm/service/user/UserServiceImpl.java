package jp.co.genuine.hm.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jp.co.genuine.hm.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
	ObjectMapper mapper = new ObjectMapper();
	static String API_ROOT = "http://localhost:8082/user/";

	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserList getUserList() {
		String url = API_ROOT;

		return restTemplate.getForObject(url, UserList.class);
	}

	@Override
	public User getUser(UserId userId) {
		String url = API_ROOT + userId.getValue();

		return restTemplate.getForObject(url, User.class);
	}

	@Override
	public ResponseEntity<Void> postUser(UserViewModel parameter) {
		String url = API_ROOT;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.postForEntity(url, httpEntity, Void.class);
	}

	@Override
	public ResponseEntity<Void> putUser(UserId userId, UserViewModel parameter) throws IOException {
		String url = API_ROOT + userId.getValue();
		String json;
		try {
			PutUserRequest param = new PutUserRequest(parameter.getMailAddress(), parameter.getUserName(), parameter.getStatus(), parameter.getLeftoverHoliday(), parameter.getHireDate(), parameter.getPassword());
			json = mapper.writeValueAsString(param);
			ObjectNode node = (ObjectNode)mapper.readTree(json);
			if(node.get("password").asText().isEmpty()) {
				node.remove("password");
			}
			json = mapper.writeValueAsString(node);

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

			HttpEntity httpEntity = new HttpEntity<>(json, httpHeaders);

			return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Void.class);

		} catch (JsonProcessingException e) {
			throw e;
		}
	}

	@Override
	public ResponseEntity<Void> deleteUser(UserId userId) {
		String url = API_ROOT + userId.getValue();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

		return restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
	}

}
