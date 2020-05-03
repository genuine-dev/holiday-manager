package jp.co.genuine.hm.api.domain.paid_leave;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaidLeaveList {
	private List<PaidLeave> paidLeaveList;
}
