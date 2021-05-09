package jp.co.genuine.hm.service.user;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;

import jp.co.genuine.hm.model.group.Group;
import jp.co.genuine.hm.model.group.GroupId;
import jp.co.genuine.hm.model.group.GroupList;
import jp.co.genuine.hm.model.group.PostGroupRequest;
import jp.co.genuine.hm.model.group.PutGroupRequest;

public interface GroupService {
	public GroupList getGroupList( )throws IOException;
	public Group getGroup(GroupId groupId)throws IOException;
	public CloseableHttpResponse postGroup(PostGroupRequest parameter)throws IOException;
	public CloseableHttpResponse putGroup(PutGroupRequest parameter, GroupId groupId)throws IOException;
	public CloseableHttpResponse deleteGroup(GroupId groupId)throws IOException;
}
