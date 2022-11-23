package holiday.manager.rest.request.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import holiday.manager.domain.user.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(description = "ユーザー更新リクエスト")
public class PutUserRequest {
    @NotBlank
    @ApiModelProperty(example = "example@example.com", required = true)
    private String mailAddress;
    @NotBlank
    @ApiModelProperty(example = "山田 太郎", required = true)
    private String userName;
    @NotNull
    @ApiModelProperty(example = "ACTIVE", required = true)
    private UserStatus status;
    @NotNull
    @ApiModelProperty(example = "20", required = true)
    private Double leftoverHoliday;
    @NotNull
    @ApiModelProperty(example = "2020-11-07", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    @ApiModelProperty(example = "password", required = false)
    @Length(min = 6, max = 20)
    private String password;

    public PutUserRequest() {
    }

    public PutUserRequest(String mailAddress, String userName, UserStatus status, Double leftoverHoliday, Date hireDate, String password) {
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Double getLeftoverHoliday() {
        return leftoverHoliday;
    }

    public void setLeftoverHoliday(Double leftoverHoliday) {
        this.leftoverHoliday = leftoverHoliday;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
