package jp.co.genuine.hm.api.service.user;

import jp.co.genuine.hm.api.domain.request.user.PostUserRequest;
import jp.co.genuine.hm.api.domain.request.user.PutUserRequest;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserQueries;
import jp.co.genuine.hm.api.domain.request.user.parameter.UserSorts;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.GroupId;
import jp.co.genuine.hm.api.domain.user.User;
import jp.co.genuine.hm.api.domain.user.UserId;
import jp.co.genuine.hm.api.domain.user.UserList;

public interface UserService {
	public UserList getUser(UserSorts sorts, UserQueries queries);
	public void putUser(UserId userId, PutUserRequest request);
	public UserList findUsers(GroupId groupId);
	public void postUser(PostUserRequest request);
	public Boolean existAccountId(AccountId accountId);
	public void deleteUser(UserId userId);
	public User findUser(UserId userId);
}
