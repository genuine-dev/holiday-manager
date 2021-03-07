package jp.co.genuine.hm.api.domain.holiday.dto;

import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.user.User;

public class HolidayApproveDTO {
	private HolidayApplicationId applicationId;
	private User approver;
	public HolidayApproveDTO() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public HolidayApproveDTO(HolidayApplicationId applicationId, User approver) {
		super();
		this.applicationId = applicationId;
		this.approver = approver;
	}
	public HolidayApplicationId getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(HolidayApplicationId applicationId) {
		this.applicationId = applicationId;
	}
	public User getApprover() {
		return approver;
	}
	public void setApprover(User approver) {
		this.approver = approver;
	}
}
