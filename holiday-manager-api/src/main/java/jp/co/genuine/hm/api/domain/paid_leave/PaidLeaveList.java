package jp.co.genuine.hm.api.domain.paid_leave;

import java.util.List;

public class PaidLeaveList {
	private List<PaidLeave> paidLeaveList;

	public PaidLeaveList() {
	}

	public PaidLeaveList(List<PaidLeave> paidLeaveList) {
		this.paidLeaveList = paidLeaveList;
	}

	public List<PaidLeave> getPaidLeaveList() {
		return paidLeaveList;
	}

	public void setPaidLeaveList(List<PaidLeave> paidLeaveList) {
		this.paidLeaveList = paidLeaveList;
	}
}
