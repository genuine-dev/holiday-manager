package jp.co.genuine.hm.api.service.holiday;

import java.text.ParseException;

import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayCancelRequest;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayRejectRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayApplyRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PutHolidayApproveRequest;

public interface HolidayService {

	public HolidayApplication postHolidayApply(PostHolidayApplyRequest request) throws ParseException;

	public HolidayApplication putHolidayApprove(PutHolidayApproveRequest request);

	public HolidayApplication deleteHolidayReject(DeleteHolidayRejectRequest request);

	public HolidayApplication deleteHolidayCancel(DeleteHolidayCancelRequest request);

	public HolidayList getHoliday(UserId userId);

}
