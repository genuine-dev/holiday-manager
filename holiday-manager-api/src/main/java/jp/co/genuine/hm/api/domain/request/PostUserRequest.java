package jp.co.genuine.hm.api.domain.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.genuine.hm.api.domain.user.AccountId;
import jp.co.genuine.hm.api.domain.user.HireDate;
import jp.co.genuine.hm.api.domain.user.MailAddress;
import jp.co.genuine.hm.api.domain.user.Password;
import jp.co.genuine.hm.api.domain.user.UserName;
import jp.co.genuine.hm.api.domain.user.UserStatus;

@ApiModel(description = "ユーザー登録リクエスト")
public class PostUserRequest {
	@NotBlank
	@Length(max = 20)
	@ApiModelProperty(example = "20201107_1")
	private String accountId;
	@NotBlank
	@Length(max = 20)
	@ApiModelProperty(example="pass", required = true)
	private String password;
	@NotBlank
	@Email
	@Length(max = 20)
	@ApiModelProperty(example = "example@example.com", required = true)
	private String mailAddress;
	@NotBlank
	@Length(max = 20)
	@ApiModelProperty(example = "山田 太郎", required = true)
	private String userName;
	@NotBlank
	@ApiModelProperty(example = "2020-11-07", required = true)
	private String hireDate;
	@NotBlank
	@Length(max = 20)
	@ApiModelProperty(example = "ACTIVE", required = true)
	private String status;

	public PostUserRequest(String accountId, String password, String mailAddress,
			String userName, String hireDate, String status) {
		this.accountId = accountId;
		this.password = password;
		this.mailAddress = mailAddress;
		this.userName = userName;
		this.hireDate = hireDate;
		this.status = status;
	}

	@AssertTrue(message = "日付の形式が正しくありません。")
	public boolean isValidDateFormat() {
		SimpleDateFormat format = HireDate.format;
		format.setLenient(false);
		try {
			format.parse(hireDate);
		} catch(ParseException e) {
			return false;
		}
		return true;
	}

	@AssertTrue(message = "ステータスの値が正しくありません。")
	public boolean isContainsUserStatus() {
		try {
			UserStatus.valueOf(status);
		} catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	public AccountId getAccountId() {
		return new AccountId(accountId);
	}

	public Password getPassword() {
		return new Password(password);
	}

	public MailAddress getMailAddress() {
		return new MailAddress(mailAddress);
	}

	public UserName getUserName() {
		return new UserName(userName);
	}

	public HireDate getHireDate() throws ParseException {
		return new HireDate(hireDate);
	}

	public UserStatus getStatus() {
		return UserStatus.valueOf(status);
	}

}
