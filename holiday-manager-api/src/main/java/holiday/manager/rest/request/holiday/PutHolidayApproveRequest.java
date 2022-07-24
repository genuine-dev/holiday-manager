package holiday.manager.domain.request.holiday;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "休暇申請承認リクエスト")
public class PutHolidayApproveRequest {
	@NotBlank
	@ApiModelProperty(example = "UUID")
	private String holidayApplicationId;
	@NotBlank
	@ApiModelProperty(example = "3")
	private String approverId;
	public PutHolidayApproveRequest() {
	}
	public PutHolidayApproveRequest(@NotBlank String holidayApplicationId, @NotBlank String approverId) {
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
