package holiday.manager.domain.model.holiday.application;

import java.util.Date;
import java.util.List;

import holiday.manager.domain.model.AggregateRoot;
import holiday.manager.domain.model.AuthenticationException;
import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.event.HolidayApplicationApplied;
import holiday.manager.domain.model.holiday.application.event.HolidayApplicationApproved;
import holiday.manager.domain.model.holiday.application.event.HolidayApplicationCanceled;
import holiday.manager.domain.model.holiday.application.event.HolidayApplicationRejected;
import holiday.manager.domain.model.user.User;
import holiday.manager.domain.model.user.UserId;

/**
 * 休暇申請
 *
 */
public class HolidayApplication extends AggregateRoot {
	private HolidayApplicationId id;
	/** 休暇種別 */
	private KindOfHoliday kindOfHoliday;
	/** 休暇タイプ*/
	private HolidayType holidayType;
	/** 日付*/
	private Date date;
	/** ステータス*/
	private HolidayApplicationStatus status;
	/** 申請者*/
	private UserId applicantId;
	/** 承認者*/
	private UserId approverId;

	public HolidayApplication(List<DomainEvent> eventStream, int streamVersion) {
		super(eventStream, streamVersion);
	}

	public HolidayApplication(KindOfHoliday kindOfHoliday, HolidayType holidayType, Date date, UserId applicant) {
		HolidayApplicationId id = new HolidayApplicationId();
		apply(new HolidayApplicationApplied(id, kindOfHoliday, holidayType, date, applicant));
	}

	public HolidayApplication(HolidayApplicationId id,
			KindOfHoliday kindOfHoliday, HolidayType holidayType, Date date, HolidayApplicationStatus status,
			UserId applicantId, UserId approverId) {
		this.id = id;
		this.kindOfHoliday = kindOfHoliday;
		this.holidayType = holidayType;
		this.date = date;
		this.status = status;
		this.applicantId = applicantId;
		this.approverId = approverId;
	}

	public HolidayApplication approve(User approver) {
		boolean hasAuthority = approver.getManagedMembers().stream()
				.filter(user -> user.getId().equals(applicantId))
				.findFirst()
				.isPresent();

		if (!hasAuthority) {
			throw new AuthenticationException(
					"authentication failed.(approve): holidayApplicationId: " + id + ", approverId: "
							+ approver.getId());
		}
		apply(new HolidayApplicationApproved(id, approver.getId()));

		return this;
	}

	public HolidayApplication reject(User approver) {
		boolean hasAuthority = approver.getManagedMembers().stream()
				.filter(user -> user.getId().equals(applicantId))
				.findFirst()
				.isPresent();

		if (!hasAuthority) {
			throw new AuthenticationException(
					"authentication failed.(reject): holidayApplicationId: " + id + ", approverId: "
							+ approver.getId());
		}
		apply(new HolidayApplicationRejected(id, approver.getId()));

		return this;
	}

	public HolidayApplication cancel(User applicant) {
		if (!applicant.getId().equals(applicantId)) {
			throw new AuthenticationException(
					"authentication failed.(cancel): holidayApplicationId: " + id + ", applicantId: "
							+ applicant.getId());
		}

		apply(new HolidayApplicationCanceled(id, applicant.getId()));

		return this;
	}

	protected void on(HolidayApplicationApplied event) {
		id = event.getId();
		kindOfHoliday = event.getKindOfHoliday();
		holidayType = event.getHolidayType();
		date = event.getDate();
		status = event.getStatus();
		applicantId = event.getApplicantId();
	}

	protected void on(HolidayApplicationApproved event) {
		status = HolidayApplicationStatus.APPROVED;
		approverId = event.getApproverId();
	}

	protected void on(HolidayApplicationRejected event) {
		status = HolidayApplicationStatus.REJECTED;
		approverId = event.getApproverId();
	}

	protected void on(HolidayApplicationCanceled event) {
		status = HolidayApplicationStatus.CANCELED;
	}

	public HolidayApplicationId getId() {
		return id;
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

	public HolidayApplicationStatus getStatus() {
		return status;
	}

	public UserId getApplicantId() {
		return applicantId;
	}

	public UserId getApproverId() {
		return approverId;
	}
}
