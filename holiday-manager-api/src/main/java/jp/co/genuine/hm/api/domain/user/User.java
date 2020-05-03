package jp.co.genuine.hm.api.domain.user;

import jp.co.genuine.hm.api.domain.common.MailAddress;
import jp.co.genuine.hm.api.domain.paid_leave.PaidLeaveList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class User {
	private UserId userId;
	private MailAddress mailAddress;
	private Password password;
	private UserName userName;
	private UserStatus userStatus;
	private HireDate hireDate;
	private PaidLeaveList paidLeaveList;
	
	public User() {
		userId = new UserId();
		mailAddress = new MailAddress();
		password = new Password();
		userName = new UserName();
		userStatus = UserStatus.ACTIVE;
		hireDate = new HireDate();
		paidLeaveList = new PaidLeaveList();
	}
}
