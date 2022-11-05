package jp.co.genuine.hm.service.user;

import jp.co.genuine.hm.model.group.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GroupServiceImpl implements GroupService {
	static String API_ROOT = "http://localhost:8082/group/";

	@Autowired
	RestTemplate restTemplate;


	@Override
	public GroupList getGroupList() {
		String url = API_ROOT;

		return restTemplate.getForObject(url, GroupList.class);
	}


	@Override
	public Group getGroup(GroupId groupId) {
		String url = API_ROOT + groupId.getValue();

		return restTemplate.getForObject(url, Group.class);
	}


	@Override
	public ResponseEntity<Void> deleteGroup(GroupId groupId) {
		String url = API_ROOT + groupId.getValue();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

		return restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
	}


	@Override
	public ResponseEntity<Void> postGroup(PostGroupRequest parameter) {
		String url = API_ROOT;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.postForEntity(url, httpEntity, Void.class);
	}


	@Override
	public ResponseEntity<Void> putGroup(PutGroupRequest parameter, GroupId groupId) {
		String url = API_ROOT + groupId.getValue();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<PutGroupRequest> httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Void.class);
	}


	@Override
	public ResponseEntity<Void> postGroupMembers(GroupId groupId, GroupMemberList groupMemberList) {
		String url = API_ROOT + "members";

		PostGroupMembersRequest parameter = new PostGroupMembersRequest(groupId.getValue(), groupMemberList.getGroupMemberList());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity httpEntity = new HttpEntity<>(parameter, httpHeaders);

		return restTemplate.postForEntity(url, httpEntity, Void.class);
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
