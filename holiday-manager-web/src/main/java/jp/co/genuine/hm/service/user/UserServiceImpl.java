package jp.co.genuine.hm.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jp.co.genuine.hm.model.user.*;
import jp.co.genuine.hm.rest.endpoint.user.UserEndpointFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserEndpointFactory userEndpointFactory;

	@Override
	public UserList getUserList() {
		return restTemplate.getForObject(userEndpointFactory.createGetUserListEndpoint(), UserList.class);
	}

	@Override
	public User getUser(UserId userId) {
		return restTemplate.getForObject(userEndpointFactory.createGetUserEndpoint(userId.getValue()), User.class);
	}

	@Override
	public ResponseEntity<Void> postUser(UserViewModel parameter) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.postForEntity(userEndpointFactory.createPostUserEndpoint(), httpEntity, Void.class);
	}

	@Override
	public ResponseEntity<Void> putUser(UserId userId, UserViewModel parameter) throws IOException {
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

			return restTemplate.exchange(userEndpointFactory.createPutUserEndpoint(userId.getValue()), HttpMethod.PUT, httpEntity, Void.class);

		} catch (JsonProcessingException e) {
			throw e;
		}
	}

	@Override
	public ResponseEntity<Void> deleteUser(UserId userId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

		return restTemplate.exchange(userEndpointFactory.createDeleteUserEndpoint(userId.getValue()), HttpMethod.DELETE, httpEntity, Void.class);
	}

}
