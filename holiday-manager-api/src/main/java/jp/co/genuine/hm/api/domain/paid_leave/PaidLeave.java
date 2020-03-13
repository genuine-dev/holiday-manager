package jp.co.genuine.hm.api.domain.paid_leave;

import java.util.Date;

import jp.co.genuine.hm.api.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaidLeave {
	private PaidLeaveId paidLeaveId;
	private User user;
	private PaidLeaveType paidLeaveType;
	private PaidLeaveSection paidleaveLeaveSection;
	private Date grantDate;
	private Date effectiveDate;
	private Date acquisitionDate;
}
