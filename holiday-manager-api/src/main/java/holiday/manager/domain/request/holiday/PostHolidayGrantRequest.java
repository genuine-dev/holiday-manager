package holiday.manager.domain.request.holiday;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "休暇付与リクエスト")
public class PostHolidayGrantRequest {
	@NotBlank
	@ApiModelProperty(example = "1")
	private String userId;
	@NotBlank
	@ApiModelProperty(example = "PAYED_LEAVE")
	private String kindOfHoliday;
	@NotBlank
	@ApiModelProperty(example = "3.5")
	private String days;
	@NotBlank
	@ApiModelProperty(example = "2021-02-23")
	private String grantedDate;
	@NotBlank
	@ApiModelProperty(example = "2023-04-01")
	private String expirationDate;
	public PostHolidayGrantRequest() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public PostHolidayGrantRequest(@NotBlank String userId, @NotBlank String kindOfHoliday, @NotBlank String days,
			@NotBlank String grantedDate, @NotBlank String expirationDate) {
		super();
		this.userId = userId;
		this.kindOfHoliday = kindOfHoliday;
		this.days = days;
		this.grantedDate = grantedDate;
		this.expirationDate = expirationDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKindOfHoliday() {
		return kindOfHoliday;
	}
	public void setKindOfHoliday(String kindOfHoliday) {
		this.kindOfHoliday = kindOfHoliday;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getGrantedDate() {
		return grantedDate;
	}
	public void setGrantedDate(String grantedDate) {
		this.grantedDate = grantedDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

}
