package jp.co.genuine.hm.api.domain.paid_leave;

import java.util.Date;

import jp.co.genuine.hm.api.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PaidLeave {
	private PaidLeaveId paidLeaveId;
	private User user;
	private PaidLeaveType paidLeaveType;
	private PaidLeaveSection paidleaveLeaveSection;
	private Date grantDate;
	private Date effectiveDate;
	private Date acquisitionDate;

	public PaidLeave() {
	}

	public PaidLeave(PaidLeaveId paidLeaveId, User user, PaidLeaveType paidLeaveType,
			PaidLeaveSection paidleaveLeaveSection, Date grantDate, Date effectiveDate, Date acquisitionDate) {
		this.paidLeaveId = paidLeaveId;
		this.user = user;
		this.paidLeaveType = paidLeaveType;
		this.paidleaveLeaveSection = paidleaveLeaveSection;
		this.grantDate = grantDate;
		this.effectiveDate = effectiveDate;
		this.acquisitionDate = acquisitionDate;
	}

	public PaidLeaveId getPaidLeaveId() {
		return paidLeaveId;
	}

	public void setPaidLeaveId(PaidLeaveId paidLeaveId) {
		this.paidLeaveId = paidLeaveId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PaidLeaveType getPaidLeaveType() {
		return paidLeaveType;
	}

	public void setPaidLeaveType(PaidLeaveType paidLeaveType) {
		this.paidLeaveType = paidLeaveType;
	}

	public PaidLeaveSection getPaidleaveLeaveSection() {
		return paidleaveLeaveSection;
	}

	public void setPaidleaveLeaveSection(PaidLeaveSection paidleaveLeaveSection) {
		this.paidleaveLeaveSection = paidleaveLeaveSection;
	}

	public Date getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(Date grantDate) {
		this.grantDate = grantDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
}
