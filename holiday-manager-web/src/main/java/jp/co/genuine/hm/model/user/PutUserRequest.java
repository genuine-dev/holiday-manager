package jp.co.genuine.hm.model.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PutUserRequest {
	@NotBlank
	private String mailAddress;
	@NotBlank
	private String userName;
	@NotBlank
	private String status;
	@NotBlank
	private String leftoverHoliday;
	@NotBlank
	private String hireDate;
	@Length(max = 20)
	private String password;
	public PutUserRequest() {
	}
	public PutUserRequest( String mailAddress, String userName, String status,
			String leftoverHoliday, String hireDate, String password) {
		this.mailAddress = mailAddress;
		this.userName = userName;
		this.status = status;
		this.leftoverHoliday = leftoverHoliday;
		this.hireDate = hireDate;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLeftoverHoliday() {
		return leftoverHoliday;
	}
	public void setLeftoverHoliday(String leftoverHoliday) {
		this.leftoverHoliday = leftoverHoliday;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
