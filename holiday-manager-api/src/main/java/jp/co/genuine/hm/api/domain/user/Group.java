package jp.co.genuine.hm.api.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
	private GroupId groupId;
	private GroupName groupName;
	private UserList managerList;
	private UserList memberList;
}
