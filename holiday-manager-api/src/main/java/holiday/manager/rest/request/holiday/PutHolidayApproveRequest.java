package holiday.manager.rest.request.holiday;

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
	private Integer approverId;
	public PutHolidayApproveRequest() {
	}
	public PutHolidayApproveRequest(@NotBlank String holidayApplicationId, @NotBlank Integer approverId) {
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
	public Integer getApproverId() {
		return approverId;
	}
	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}
}
