package jp.co.genuine.hm.service.user;

import jp.co.genuine.hm.model.user.*;
import jp.co.genuine.hm.rest.endpoint.user.UserEndpointFactory;
import jp.co.genuine.hm.rest.response.user.UserResponse;
import jp.co.genuine.hm.rest.response.user.UserResponseConverter;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
	private final RestTemplate restTemplate;

	private final UserEndpointFactory userEndpointFactory;

	private final UserResponseConverter userResponseConverter;

	public UserServiceImpl(RestTemplate restTemplate, UserEndpointFactory userEndpointFactory, UserResponseConverter userResponseConverter) {
		this.restTemplate = restTemplate;
		this.userEndpointFactory = userEndpointFactory;
		this.userResponseConverter = userResponseConverter;
	}

	@Override
	public UserList getUserList() {
		ResponseEntity<UserResponse[]> responseEntity = restTemplate.getForEntity(userEndpointFactory.createGetUserListEndpoint(), UserResponse[].class);
		return userResponseConverter.convert(responseEntity.getBody());
	}

	@Override
	public User getUser(UserId userId) {
		UserResponse userResponse = restTemplate.getForObject(userEndpointFactory.createGetUserEndpoint(userId.getValue()), UserResponse.class);
		return userResponseConverter.convert(userResponse);
	}

	@Override
	public ResponseEntity<Void> postUser(UserViewModel userViewModel) {
		HttpEntity httpEntity = httpEntity(userViewModel);
		return restTemplate.postForEntity(userEndpointFactory.createPostUserEndpoint(), httpEntity, Void.class);
	}

	@Override
	public ResponseEntity<Void> putUser(UserId userId, UserViewModel parameter) throws IOException {
		PutUserRequest putUserRequest = new PutUserRequest(parameter.getMailAddress(), parameter.getUserName(), parameter.getStatus(), parameter.getLeftoverHoliday(), parameter.getHireDate(), parameter.getPassword());
		HttpEntity httpEntity = httpEntity(putUserRequest);
		return restTemplate.exchange(userEndpointFactory.createPutUserEndpoint(userId.getValue()), HttpMethod.PUT, httpEntity, Void.class);
	}

	@Override
	public ResponseEntity<Void> deleteUser(UserId userId) {
		HttpEntity httpEntity = httpEntity(null);
		return restTemplate.exchange(userEndpointFactory.createDeleteUserEndpoint(userId.getValue()), HttpMethod.DELETE, httpEntity, Void.class);
	}

	private HttpEntity httpEntity(Object body) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		return new HttpEntity<>(body, httpHeaders);
	}
}
