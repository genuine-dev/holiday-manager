package holiday.manager.domain.request.holiday;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "休暇申請キャンセルリクエスト")
public class DeleteHolidayCancelRequest {
	@NotBlank
	@ApiModelProperty(example = "UUID")
	private String holidayApplicationId;
	@NotBlank
	@ApiModelProperty(example = "1")
	private String applicantId;
	public DeleteHolidayCancelRequest() {
	}
	public DeleteHolidayCancelRequest(@NotBlank String holidayApplicationId, @NotBlank String applicantId) {
		super();
		this.holidayApplicationId = holidayApplicationId;
		this.applicantId = applicantId;
	}
	public String getHolidayApplicationId() {
		return holidayApplicationId;
	}
	public void setHolidayApplicationId(String holidayApplicationId) {
		this.holidayApplicationId = holidayApplicationId;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
}
