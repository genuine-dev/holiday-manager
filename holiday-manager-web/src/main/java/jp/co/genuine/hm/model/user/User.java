package jp.co.genuine.hm.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jp.co.genuine.hm.model.rule.RuleId;

import javax.validation.Valid;

@JsonInclude(Include.NON_EMPTY)
public class User {
	@Valid
	private AccountId accountId;
	private UserId userId;
	@Valid
	private MailAddress mailAddress;
	private Password password;
	@Valid
	private UserName userName;
	private UserStatus userStatus;
	@Valid
	private HireDate hireDate;
	private LeftoverHoliday leftoverHoliday;
	private boolean admin;
	private boolean emptyPassword;
	private RuleId ruleId;

	public User() {
		userStatus = UserStatus.ACTIVE;
	}

	public User(AccountId accountId, UserId userId, MailAddress mailAddress, Password password, UserName userName,
			UserStatus userStatus, HireDate hireDate, LeftoverHoliday leftoverHoliday, RuleId ruleId) {
		this.accountId = accountId;
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.password = password;
		this.userName = userName;
		this.userStatus = userStatus;
		this.hireDate = hireDate;
		this.leftoverHoliday = leftoverHoliday;
		this.admin = false;
		this.ruleId = ruleId;
	}

	public User(UserId userId, MailAddress mailAddress, Password password, UserName userName,
			UserStatus userStatus, HireDate hireDate, LeftoverHoliday leftoverHoliday) {
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.password = password;
		this.userName = userName;
		this.userStatus = userStatus;
		this.hireDate = hireDate;
		this.leftoverHoliday = leftoverHoliday;
	}

	public boolean isEmptyPassword() {
		return password == null || password.isEmpty();
	}

	public UserId userId() {
		return userId;
	}
	public AccountId getAccountId() {
		return accountId;
	}

	public void setAccountId(AccountId accountId) {
		this.accountId = accountId;
	}

	public LeftoverHoliday getLeftoverHoliday() {
		return leftoverHoliday;
	}

	public void setLeftoverHoliday(LeftoverHoliday leftoverHoliday) {
		this.leftoverHoliday = leftoverHoliday;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public RuleId getRuleId() {
		return ruleId;
	}

	public void setEmptyPassword(boolean emptyPassword) {
		this.emptyPassword = emptyPassword;
	}
}
