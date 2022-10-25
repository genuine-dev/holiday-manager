package holiday.manager.rest.request.holiday;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "休暇付与リクエスト")
public class PostHolidayGrantRequest {
	@NotBlank
	@ApiModelProperty(example = "1")
	private Integer userId;
	@NotBlank
	@ApiModelProperty(example = "PAYED_LEAVE")
	private KindOfHoliday kindOfHoliday;
	@NotBlank
	@ApiModelProperty(example = "3.5")
	private Double days;
	@NotBlank
	@ApiModelProperty(example = "2021-02-23")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date grantedDate;
	@NotBlank
	@ApiModelProperty(example = "2023-04-01")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	public PostHolidayGrantRequest() {
	}

	public PostHolidayGrantRequest(Integer userId, KindOfHoliday kindOfHoliday, Double days, Date grantedDate, Date expirationDate) {
		this.userId = userId;
		this.kindOfHoliday = kindOfHoliday;
		this.days = days;
		this.grantedDate = grantedDate;
		this.expirationDate = expirationDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public KindOfHoliday getKindOfHoliday() {
		return kindOfHoliday;
	}

	public void setKindOfHoliday(KindOfHoliday kindOfHoliday) {
		this.kindOfHoliday = kindOfHoliday;
	}

	public Double getDays() {
		return days;
	}

	public void setDays(Double days) {
		this.days = days;
	}

	public Date getGrantedDate() {
		return grantedDate;
	}

	public void setGrantedDate(Date grantedDate) {
		this.grantedDate = grantedDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
