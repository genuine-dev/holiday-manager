package holiday.manager.domain.user;

import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.account.Password;
import holiday.manager.domain.user.rule.RuleId;

import javax.validation.Valid;

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
    private RuleId ruleId;

    public User() {
        this.accountId = new AccountId();
        this.userId = new UserId();
        this.mailAddress = new MailAddress();
        this.password = new Password();
        this.userName = new UserName();
        this.userStatus = UserStatus.RETIRED;
        this.hireDate = new HireDate();
        this.leftoverHoliday = new LeftoverHoliday();
        this.admin = false;
        this.ruleId = new RuleId();
    }

    public User(AccountId accountId, UserId userId, MailAddress mailAddress, Password password, UserName userName, UserStatus userStatus, HireDate hireDate, LeftoverHoliday leftoverHoliday, boolean admin, RuleId ruleId) {
        this.accountId = accountId;
        this.userId = userId;
        this.mailAddress = mailAddress;
        this.password = password;
        this.userName = userName;
        this.userStatus = userStatus;
        this.hireDate = hireDate;
        this.leftoverHoliday = leftoverHoliday;
        this.admin = admin;
        this.ruleId = ruleId;
    }

    public boolean isEmptyPassword() {
        return password == null || password.isEmpty();
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
}
