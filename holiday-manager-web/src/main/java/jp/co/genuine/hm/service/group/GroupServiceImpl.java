package jp.co.genuine.hm.service.group;

import jp.co.genuine.hm.model.group.*;
import jp.co.genuine.hm.rest.endpoint.group.GroupEndpointFactory;
import jp.co.genuine.hm.rest.response.group.GroupResponse;
import jp.co.genuine.hm.rest.response.group.GroupResponseConverter;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GroupServiceImpl implements GroupService {
	private final RestTemplate restTemplate;

	private final GroupEndpointFactory groupEndpointFactory;

	private final GroupResponseConverter groupResponseConverter;

	public GroupServiceImpl(RestTemplate restTemplate, GroupEndpointFactory groupEndpointFactory, GroupResponseConverter groupResponseConverter) {
		this.restTemplate = restTemplate;
		this.groupEndpointFactory = groupEndpointFactory;
		this.groupResponseConverter = groupResponseConverter;
	}

	@Override
	public GroupList getGroupList() {
		GroupResponse[] groupResponses = restTemplate.getForObject(groupEndpointFactory.createGetGroupListEndpoint(), GroupResponse[].class);
		return groupResponseConverter.convert(groupResponses);
	}

	@Override
	public Group getGroup(GroupId groupId) {
		GroupResponse groupResponse = restTemplate.getForObject(groupEndpointFactory.createGetGroupEndpoint(groupId.getValue()), GroupResponse.class);
		return groupResponseConverter.convert(groupResponse);
	}

	@Override
	public ResponseEntity<Void> deleteGroup(GroupId groupId) {
		HttpEntity httpEntity = httpEntity(null);
		return restTemplate.exchange(groupEndpointFactory.createDeleteGroupEndpoint(groupId.getValue()), HttpMethod.DELETE, httpEntity, Void.class);
	}

	@Override
	public ResponseEntity<Void> postGroup(PostGroupRequest postGroupRequest) {
		HttpEntity httpEntity = httpEntity(postGroupRequest);
		return restTemplate.postForEntity(groupEndpointFactory.createPostGroupEndpoint(), httpEntity, Void.class);
	}

	@Override
	public ResponseEntity<Void> putGroup(PutGroupRequest putGroupRequest, GroupId groupId) {
		HttpEntity<PutGroupRequest> httpEntity = httpEntity(putGroupRequest);
		return restTemplate.exchange(groupEndpointFactory.createPutGroupEndpoint(groupId.getValue()), HttpMethod.PUT, httpEntity, Void.class);
	}

	@Override
	public ResponseEntity<Void> postGroupMembers(GroupId groupId, GroupMemberList groupMemberList) {
		PostGroupMembersRequest postGroupMembersRequest = new PostGroupMembersRequest(groupId.getValue(), groupMemberList.getGroupMemberList());
		HttpEntity httpEntity = httpEntity(postGroupMembersRequest);
		return restTemplate.postForEntity(groupEndpointFactory.createPostGroupMembersEndpoint(), httpEntity, Void.class);
	}

	private HttpEntity httpEntity(Object body) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity(body, httpHeaders);
		return httpEntity;
	}
}
