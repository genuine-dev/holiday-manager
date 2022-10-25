package holiday.manager.rest.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String accountId;
    private Integer userId;
    private String mailAddress;
    private String password;
    private String userName;
    private String userStatus;
    private Date hireDate;
    private Double leftoverHoliday;
    private boolean admin;
    private Integer ruleId;

    public UserResponse(String accountId, Integer userId, String mailAddress, String password, String userName, String userStatus, Date hireDate, Double leftoverHoliday, boolean admin, Integer ruleId) {
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

    public String getAccountId() {
        return accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public Double getLeftoverHoliday() {
        return leftoverHoliday;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Integer getRuleId() {
        return ruleId;
    }
}
