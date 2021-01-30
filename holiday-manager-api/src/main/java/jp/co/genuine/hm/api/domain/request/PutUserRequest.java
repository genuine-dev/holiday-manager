package jp.co.genuine.hm.api.domain.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "ユーザー更新リクエスト")
public class PutUserRequest {
	@NotBlank
	@ApiModelProperty(example = "example@example.com", required = true)
	private String mailAddress;
	@NotBlank
	@ApiModelProperty(example = "山田 太郎", required = true)
	private String userName;
	@NotBlank
	@ApiModelProperty(example = "ACTIVE", required = true)
	private String status;
	@NotBlank
	@ApiModelProperty(example = "20", required = true)
	private String leftoverHoliday;
	@NotBlank
	@ApiModelProperty(example = "2020-11-07", required = true)
	private String hireDate;
	@ApiModelProperty(example="pass", required = true)
	@Length(min = 6,max = 20)
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
