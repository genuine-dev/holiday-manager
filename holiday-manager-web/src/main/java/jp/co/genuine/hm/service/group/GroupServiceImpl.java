package jp.co.genuine.hm.service.group;

import jp.co.genuine.hm.model.group.*;
import jp.co.genuine.hm.rest.endpoint.group.GroupEndpointFactory;
import jp.co.genuine.hm.rest.response.group.GroupResponse;
import jp.co.genuine.hm.rest.response.group.GroupResponseConverter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	GroupEndpointFactory groupEndpointFactory;

	@Autowired
	GroupResponseConverter groupResponseConverter;


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
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

		return restTemplate.exchange(groupEndpointFactory.createDeleteGroupEndpoint(groupId.getValue()), HttpMethod.DELETE, httpEntity, Void.class);
	}


	@Override
	public ResponseEntity<Void> postGroup(PostGroupRequest parameter) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.postForEntity(groupEndpointFactory.createPostGroupEndpoint(), httpEntity, Void.class);
	}


	@Override
	public ResponseEntity<Void> putGroup(PutGroupRequest parameter, GroupId groupId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<PutGroupRequest> httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.exchange(groupEndpointFactory.createPutGroupEndpoint(groupId.getValue()), HttpMethod.PUT, httpEntity, Void.class);
	}


	@Override
	public ResponseEntity<Void> postGroupMembers(GroupId groupId, GroupMemberList groupMemberList) {
		PostGroupMembersRequest parameter = new PostGroupMembersRequest(groupId.getValue(), groupMemberList.getGroupMemberList());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.postForEntity(groupEndpointFactory.createPostGroupMembersEndpoint(), httpEntity, Void.class);
	}


	@Override
	public CloseableHttpResponse deleteGroupManager(GroupId groupId) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public CloseableHttpResponse postGroupMember(GroupId groupId) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public CloseableHttpResponse deleteGroupMember(GroupId groupId) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public CloseableHttpResponse postGroupManager(GroupId groupId) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}



}
