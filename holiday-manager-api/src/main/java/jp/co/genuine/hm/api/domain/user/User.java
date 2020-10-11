package jp.co.genuine.hm.api.domain.user;

import jp.co.genuine.hm.api.domain.common.MailAddress;
import jp.co.genuine.hm.api.domain.paid_leave.PaidLeaveList;

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

	public User(UserId userId, MailAddress mailAddress, Password password, UserName userName, UserStatus userStatus,
			HireDate hireDate, PaidLeaveList paidLeaveList) {
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.password = password;
		this.userName = userName;
		this.userStatus = userStatus;
		this.hireDate = hireDate;
		this.paidLeaveList = paidLeaveList;
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public MailAddress getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public UserName getUserName() {
		return userName;
	}

	public void setUserName(UserName userName) {
		this.userName = userName;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public HireDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(HireDate hireDate) {
		this.hireDate = hireDate;
	}

	public PaidLeaveList getPaidLeaveList() {
		return paidLeaveList;
	}

	public void setPaidLeaveList(PaidLeaveList paidLeaveList) {
		this.paidLeaveList = paidLeaveList;
	}
}
