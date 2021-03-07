package jp.co.genuine.hm.api.domain.user.alert;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AlertForTakingPaidLeave {
	private Double days;
	private Date deadline;
}
