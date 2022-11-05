package jp.co.genuine.hm.service.user;

import jp.co.genuine.hm.model.group.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface GroupService {
	public GroupList getGroupList( );
	public Group getGroup(GroupId groupId);
	public ResponseEntity<Void> postGroup(PostGroupRequest parameter);
	public ResponseEntity<Void> putGroup(PutGroupRequest parameter, GroupId groupId);
	public ResponseEntity<Void> deleteGroup(GroupId groupId);
	public CloseableHttpResponse postGroupManager(GroupId groupId)throws IOException;
	public CloseableHttpResponse deleteGroupManager(GroupId groupId)throws IOException;
	public CloseableHttpResponse postGroupMember(GroupId groupId)throws IOException;
	public CloseableHttpResponse deleteGroupMember(GroupId groupId)throws IOException;
	public ResponseEntity<Void> postGroupMembers(GroupId groupId, GroupMemberList groupMemberList);
}
