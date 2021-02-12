package jp.co.genuine.hm.api.domain.holiday;

import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.user.User;

public class HolidayRejectDTO {
	private HolidayApplicationId applicationId;
	private User approver;
	public HolidayRejectDTO() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public HolidayRejectDTO(HolidayApplicationId applicationId, User approver) {
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
