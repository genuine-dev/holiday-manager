package jp.co.genuine.hm.service.group;

import jp.co.genuine.hm.model.group.*;
import org.springframework.http.ResponseEntity;

public interface GroupService {
	public GroupList getGroupList( );
	public Group getGroup(GroupId groupId);
	public ResponseEntity<Void> postGroup(PostGroupRequest parameter);
	public ResponseEntity<Void> putGroup(PutGroupRequest parameter, GroupId groupId);
	public ResponseEntity<Void> deleteGroup(GroupId groupId);
	public ResponseEntity<Void> postGroupMembers(GroupId groupId, GroupMemberList groupMemberList);
}
