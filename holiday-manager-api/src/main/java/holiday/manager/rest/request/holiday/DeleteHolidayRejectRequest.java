package holiday.manager.rest.request.holiday;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "休暇申請却下リクエスト")
public class DeleteHolidayRejectRequest {
	@NotBlank
	@ApiModelProperty(example = "UUID")
	private String holidayApplicationId;
	@NotBlank
	@ApiModelProperty(example = "3")
	private String approverId;
	public DeleteHolidayRejectRequest() {
	}
	public DeleteHolidayRejectRequest(@NotBlank String holidayApplicationId, @NotBlank String approverId) {
		super();
		this.holidayApplicationId = holidayApplicationId;
		this.approverId = approverId;
	}
	public String getHolidayApplicationId() {
		return holidayApplicationId;
	}
	public void setHolidayApplicationId(String holidayApplicationId) {
		this.holidayApplicationId = holidayApplicationId;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
}
