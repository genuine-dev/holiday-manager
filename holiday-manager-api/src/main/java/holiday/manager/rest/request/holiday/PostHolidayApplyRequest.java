package holiday.manager.rest.request.holiday;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "休暇申請申請リクエスト")
public class PostHolidayApplyRequest {
	@NotBlank
	@ApiModelProperty(example = "1")
	private Integer applicantId;
	@NotBlank
	@ApiModelProperty(example = "PAYED_LEAVE")
	private KindOfHoliday kindOfHoliday;
	@NotBlank
	@ApiModelProperty(example = "FULL_OFF")
	private HolidayType holidayType;
	@NotBlank
	@ApiModelProperty(example = "2021-02-01")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	public PostHolidayApplyRequest() {
	}

	public PostHolidayApplyRequest(Integer applicantId, KindOfHoliday kindOfHoliday, HolidayType holidayType, Date date) {
		this.applicantId = applicantId;
		this.kindOfHoliday = kindOfHoliday;
		this.holidayType = holidayType;
		this.date = date;
	}

	public Integer getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}

	public KindOfHoliday getKindOfHoliday() {
		return kindOfHoliday;
	}

	public void setKindOfHoliday(KindOfHoliday kindOfHoliday) {
		this.kindOfHoliday = kindOfHoliday;
	}

	public HolidayType getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(HolidayType holidayType) {
		this.holidayType = holidayType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
