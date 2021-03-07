package jp.co.genuine.hm.api.service.holiday;

import java.text.ParseException;
import java.util.List;

import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayCancelRequest;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayRejectRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayApplyRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayGrantRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PutHolidayApproveRequest;
import jp.co.genuine.hm.api.domain.user.alert.AlertForTakingPaidLeave;

public interface HolidayService {

	public HolidayApplication postHolidayApply(PostHolidayApplyRequest request) throws ParseException;

	public HolidayApplication putHolidayApprove(PutHolidayApproveRequest request);

	public HolidayApplication deleteHolidayReject(DeleteHolidayRejectRequest request);

	public HolidayApplication deleteHolidayCancel(DeleteHolidayCancelRequest request);

	public HolidayList getHoliday(UserId userId);

	public HolidayList postHolidayGrant(PostHolidayGrantRequest request) throws ParseException;

	public List<HolidayApplication> getHolidayApplication(String userId, String valueOf);

	public Double getHolidayDays(String userId, String kind);

	public AlertForTakingPaidLeave getHolidayTakeAlert(String userId);
}
