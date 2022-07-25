package holiday.manager.service.holiday;

import java.text.ParseException;
import java.util.List;

import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.rest.request.holiday.DeleteHolidayCancelRequest;
import holiday.manager.rest.request.holiday.DeleteHolidayRejectRequest;
import holiday.manager.rest.request.holiday.PostHolidayApplyRequest;
import holiday.manager.rest.request.holiday.PostHolidayGrantRequest;
import holiday.manager.rest.request.holiday.PutHolidayApproveRequest;
import holiday.manager.domain.user.alert.HolidayAlert;

public interface HolidayService {

	public HolidayApplication postHolidayApply(PostHolidayApplyRequest request) throws ParseException;

	public HolidayApplication putHolidayApprove(PutHolidayApproveRequest request);

	public HolidayApplication deleteHolidayReject(DeleteHolidayRejectRequest request);

	public HolidayApplication deleteHolidayCancel(DeleteHolidayCancelRequest request);

	public HolidayList getHoliday(UserId userId);

	public HolidayList postHolidayGrant(PostHolidayGrantRequest request) throws ParseException;

	public List<HolidayApplication> getHolidayApplication(String userId, String valueOf);

	public Double getHolidayDays(String userId, String kind);

	public HolidayAlert getHolidayAlert(String userId);

	public List<HolidayApplication> getApplyingHoliday(String apploverId);
}
