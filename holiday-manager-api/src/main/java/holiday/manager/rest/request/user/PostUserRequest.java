package holiday.manager.rest.request.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import holiday.manager.domain.user.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(description = "ユーザー登録リクエスト")
public class PostUserRequest {
    @NotBlank
    @ApiModelProperty(example = "20201107_1", required = true)
    private String accountId;
    @NotBlank
    @ApiModelProperty(example = "password", required = true)
    @Length(min = 6, max = 20)
    private String password;
    @NotBlank
    @ApiModelProperty(example = "example@example.com", required = true)
    private String mailAddress;
    @NotBlank
    @ApiModelProperty(example = "山田 太郎", required = true)
    private String userName;
    @NotNull
    @ApiModelProperty(example = "2020-11-07", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    @NotNull
    @ApiModelProperty(example = "ACTIVE", required = true)
    private UserStatus status;
    @NotNull
    @ApiModelProperty(example = "20", required = true)
    private Double leftoverHoliday;

    public PostUserRequest(String accountId, String password, String mailAddress, String userName, Date hireDate, UserStatus status, Double leftoverHoliday) {
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

    public Date getHireDate() {
        return hireDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Double getLeftoverHoliday() {
        return leftoverHoliday;
    }
}
