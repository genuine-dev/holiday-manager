package holiday.manager.service.user;

import java.util.List;
import java.util.Map;

import holiday.manager.rest.request.user.PostUserRequest;
import holiday.manager.rest.request.user.PutUserRequest;
import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;
import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.UserList;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.group.GroupId;

public interface UserService {
	public UserList getUser(UserSorts sorts, UserQueries queries);
	public void putUser(UserId userId, PutUserRequest request);
	public UserList findUsers(GroupId groupId);
	public void postUser(PostUserRequest request);
	public Boolean existAccountId(AccountId accountId);
	public void deleteUser(UserId userId);
	public User findUser(UserId userId);
	public Map<String, String> getUserStatus();
	public List<Integer> findManagementUserIds(Integer userId);
}
