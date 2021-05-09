package jp.co.genuine.hm.service.user;

import java.io.IOException;

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

import jp.co.genuine.hm.model.group.Group;
import jp.co.genuine.hm.model.group.GroupId;
import jp.co.genuine.hm.model.group.GroupList;
import jp.co.genuine.hm.model.group.PostGroupRequest;
import jp.co.genuine.hm.model.group.PutGroupRequest;

@Service
public class GroupServiceImpl implements GroupService {
	CloseableHttpClient client = HttpClients.createDefault();
	ObjectMapper mapper = new ObjectMapper();
	String contentType = "content-type";
	String headerValue = "application/json; charset=UTF-8";
	static String API_ROOT = "http://localhost:8082/group/";


	@Override
	public GroupList getGroupList() throws IOException {
		String url = API_ROOT;
		GroupList result = new GroupList();

		HttpGet request = new HttpGet(url);
		request.addHeader(contentType, headerValue);
		try (CloseableHttpResponse response = client.execute(request);) {
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String jsonString = EntityUtils.toString(entity);
				result = mapper.readValue(jsonString, GroupList.class);

				return result;
			} else {
				return result;
			}
		} catch (IOException e) {
			throw e;
		}
	}


	@Override
	public Group getGroup(GroupId groupId) throws IOException {
		String url = API_ROOT + groupId.getValue();
		Group result = new Group();

		HttpGet request = new HttpGet(url);
		request.addHeader(contentType, headerValue);
		try (CloseableHttpResponse response = client.execute(request);) {
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String jsonString = EntityUtils.toString(entity);
				result = mapper.readValue(jsonString, Group.class);

				return result;
			} else {
				return result;
			}
		} catch (IOException e) {
			throw e;
		}
	}


	@Override
	public CloseableHttpResponse deleteGroup(GroupId groupId) throws IOException {
		String url = API_ROOT + groupId.getValue();
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
	public CloseableHttpResponse postGroup(PostGroupRequest parameter) throws IOException {
		String url = API_ROOT;
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
	public CloseableHttpResponse putGroup(PutGroupRequest parameter, GroupId groupId) throws IOException {
		String url = API_ROOT + groupId.getValue();
		String json;
		try {
			json = mapper.writeValueAsString(parameter);
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

}
