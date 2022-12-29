package holiday.manager.rest.request.holiday;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(description = "休暇申請申請リクエスト")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostHolidayApplyRequest {
	@NotNull
	@ApiModelProperty(example = "1")
	private Integer applicantId;
	@NotNull
	@ApiModelProperty(example = "PAYED_LEAVE")
	private KindOfHoliday kindOfHoliday;
	@NotNull
	@ApiModelProperty(example = "FULL_OFF")
	private HolidayType holidayType;
	@NotNull
	@ApiModelProperty(example = "2021-02-01")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public PostHolidayApplyRequest(Integer applicantId, KindOfHoliday kindOfHoliday, HolidayType holidayType, Date date) {
		this.applicantId = applicantId;
		this.kindOfHoliday = kindOfHoliday;
		this.holidayType = holidayType;
		this.date = date;
	}

	public Integer getApplicantId() {
		return applicantId;
	}

	public KindOfHoliday getKindOfHoliday() {
		return kindOfHoliday;
	}

	public HolidayType getHolidayType() {
		return holidayType;
	}

	public Date getDate() {
		return date;
	}
}
