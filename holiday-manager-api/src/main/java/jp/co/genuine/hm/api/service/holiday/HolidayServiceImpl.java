package jp.co.genuine.hm.api.service.holiday;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.application.service.holiday.application.HolidayApplicationService;
import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import jp.co.genuine.hm.api.domain.holiday.HolidayApplyDTO;
import jp.co.genuine.hm.api.domain.holiday.HolidayApproveDTO;
import jp.co.genuine.hm.api.domain.holiday.HolidayCancelDTO;
import jp.co.genuine.hm.api.domain.holiday.HolidayFactory;
import jp.co.genuine.hm.api.domain.holiday.HolidayRejectDTO;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayCancelRequest;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayRejectRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayApplyRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PutHolidayApproveRequest;

@Service
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	private HolidayApplicationService holidayApplicationService;
	@Autowired
	private HolidayFactory holidayFactory;
	@Autowired
	private HolidayListService holidayListService;

	@Override
	public HolidayApplication postHolidayApply(PostHolidayApplyRequest request) throws ParseException {
		HolidayApplyDTO holidayApplyDTO = holidayFactory.create(request);
		return holidayApplicationService.apply(holidayApplyDTO.getKindOfHoliday(), holidayApplyDTO.getHolidayType(),
				holidayApplyDTO.getApplyDate(), holidayApplyDTO.getApplicant());
	}

	@Override
	public HolidayApplication putHolidayApprove(PutHolidayApproveRequest request) {
		HolidayApproveDTO holidayApproveDTO = holidayFactory.create(request);
		return holidayApplicationService.approve(holidayApproveDTO.getApplicationId(), holidayApproveDTO.getApprover());
	}

	@Override
	public HolidayApplication deleteHolidayReject(DeleteHolidayRejectRequest request) {
		HolidayRejectDTO holidayRejectDTO = holidayFactory.create(request);
		return holidayApplicationService.reject(holidayRejectDTO.getApplicationId(), holidayRejectDTO.getApprover());
	}

	@Override
	public HolidayApplication deleteHolidayCancel(DeleteHolidayCancelRequest request) {
		HolidayCancelDTO holidayCancelDTO = holidayFactory.create(request);
		return holidayApplicationService.cancel(holidayCancelDTO.getApplicationId(), holidayCancelDTO.getApplicant());
	}

	@Override
	public HolidayList getHoliday(UserId userId) {
		holidayListService.createHolidayList(userId);
		return holidayListService.findHolidayList(userId);
	}

}
