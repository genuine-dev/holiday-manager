package jp.co.genuine.hm.api.service.user;

import jp.co.genuine.hm.api.domain.request.PostGroupRequest;
import jp.co.genuine.hm.api.domain.request.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.PutGroupRequest;
import jp.co.genuine.hm.api.domain.request.PutUserRequest;
import jp.co.genuine.hm.api.domain.request.parameter.Sorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.Group;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;

public interface UserService {
	public UserList getUser(Sorts sorts);
	public void putUser(UserId userId, PutUserRequest request);
	public Group findGroup(GroupId groupId);
	public void postGroup(PostGroupRequest request);
	public void putGroup(GroupId groupId, PutGroupRequest request);
	public UserList findUsers(GroupId groupId);
	public void postUser(PostUserRequest request);
	public void deleteGroup(GroupId groupId);
	public Boolean existAccountId(AccountId accountId);
}
