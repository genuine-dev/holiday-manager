package jp.co.genuine.hm.model.user;

import jp.co.genuine.hm.model.paid_leave.PaidLeaveList;

public class User {
	private UserId userId;
	private MailAddress mailAddress;
	private Password password;
	private UserName userName;
	private UserStatusEnum userStatus;
	private HireDate hireDate;
	private PaidLeaveList paidLeaveList;
	private boolean admin;

	public User() {
		userId = new UserId();
		mailAddress = new MailAddress();
		password = new Password();
		userName = new UserName();
		userStatus = UserStatusEnum.ACTIVE;
		hireDate = new HireDate();
		paidLeaveList = new PaidLeaveList();
		admin = false;
	}

	public User(UserId userId, MailAddress mailAddress, Password password, UserName userName, UserStatusEnum userStatus,
			HireDate hireDate, PaidLeaveList paidLeaveList, boolean admin) {
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.password = password;
		this.userName = userName;
		this.userStatus = userStatus;
		this.hireDate = hireDate;
		this.paidLeaveList = paidLeaveList;
		this.admin = admin;
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

	public UserStatusEnum getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusEnum userStatus) {
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
