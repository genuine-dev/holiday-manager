package holiday.manager.domain.holiday.dto;

import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.user.User;

public class HolidayCancelDTO {
	private HolidayApplicationId applicationId;
	private User applicant;
	public HolidayCancelDTO() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public HolidayCancelDTO(HolidayApplicationId applicationId, User applicant) {
		super();
		this.applicationId = applicationId;
		this.applicant = applicant;
	}
	public HolidayApplicationId getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(HolidayApplicationId applicationId) {
		this.applicationId = applicationId;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
}
