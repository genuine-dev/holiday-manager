package jp.co.genuine.hm.api.domain.user;

import jp.co.genuine.hm.api.domain.common.MailAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
	private UserId userId;
	private UserStatus userStatus;
	private MailAddress mailAddress;
	private UserName userName;
	private GroupId groupId;
	private GroupName groupName;
}
