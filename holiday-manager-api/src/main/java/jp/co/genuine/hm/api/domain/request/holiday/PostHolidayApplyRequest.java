package jp.co.genuine.hm.api.domain.request.holiday;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "休暇申請申請リクエスト")
public class PostHolidayApplyRequest {
	@NotBlank
	@ApiModelProperty(example = "1")
	private String applicantId;
	@NotBlank
	@ApiModelProperty(example = "PAYED_LEAVE")
	private String kindOfHoliday;
	@NotBlank
	@ApiModelProperty(example = "FULL_OFF")
	private String holidayType;
	@NotBlank
	@ApiModelProperty(example = "2021-02-01")
	private String date;
	public PostHolidayApplyRequest() {
	}
	public PostHolidayApplyRequest( String applicantId,  String kindOfHoliday,
			 String holidayType,  String date) {
		super();
		this.applicantId = applicantId;
		this.kindOfHoliday = kindOfHoliday;
		this.holidayType = holidayType;
		this.date = date;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public String getKindOfHoliday() {
		return kindOfHoliday;
	}
	public void setKindOfHoliday(String kindOfHoliday) {
		this.kindOfHoliday = kindOfHoliday;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
