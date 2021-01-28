package jp.co.genuine.hm.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UserViewModel {
	@NotBlank
	@Length(max = 20)
	private String accountId;
	@NotBlank
	@Length(max = 20)
	private String password;
	@NotBlank
	@Email
	@Length(max = 255)
	private String mailAddress;
	@NotBlank
	@Length(max = 20)
	private String userName;
	@NotBlank
	private String hireDate;
	@NotBlank
	@Length(max = 20)
	private String status;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
