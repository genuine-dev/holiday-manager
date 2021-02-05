package jp.co.genuine.hm.api.domain.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "ユーザー登録リクエスト")
public class PostUserRequest {
	@NotBlank
	@ApiModelProperty(example = "20201107_1")
	private String accountId;
	@NotBlank
	@ApiModelProperty(example="pass", required = true)
	@Length(min = 6,max = 20)
	private String password;
	@NotBlank
	@ApiModelProperty(example = "example@example.com", required = true)
	private String mailAddress;
	@NotBlank
	@ApiModelProperty(example = "山田 太郎", required = true)
	private String userName;
	@NotBlank
	@ApiModelProperty(example = "2020-11-07", required = true)
	private String hireDate;
	@NotBlank
	@ApiModelProperty(example = "ACTIVE", required = true)
	private String status;
	@NotBlank
	@ApiModelProperty(example = "20", required = true)
	private String leftoverHoliday;

	public PostUserRequest(String accountId, String password, String mailAddress,
			String userName, String hireDate, String status, String leftoverHoliday) {
		this.accountId = accountId;
		this.password = password;
		this.mailAddress = mailAddress;
		this.userName = userName;
		this.hireDate = hireDate;
		this.status = status;
		this.leftoverHoliday = leftoverHoliday;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getPassword() {
		return password;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public String getHireDate() {
		return hireDate;
	}

	public String getStatus() {
		return status;
	}

	public String getLeftoverHoliday() {
		return leftoverHoliday;
	}

}
