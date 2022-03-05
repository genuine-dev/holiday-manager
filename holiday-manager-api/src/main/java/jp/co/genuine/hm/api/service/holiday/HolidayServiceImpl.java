package jp.co.genuine.hm.api.service.holiday;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.application.service.holiday.application.HolidayApplicationService;
import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import jp.co.genuine.hm.api.domain.holiday.HolidayFactory;
import jp.co.genuine.hm.api.domain.holiday.dto.HolidayApplyDTO;
import jp.co.genuine.hm.api.domain.holiday.dto.HolidayApproveDTO;
import jp.co.genuine.hm.api.domain.holiday.dto.HolidayCancelDTO;
import jp.co.genuine.hm.api.domain.holiday.dto.HolidayGrantDTO;
import jp.co.genuine.hm.api.domain.holiday.dto.HolidayRejectDTO;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayCancelRequest;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayRejectRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayApplyRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayGrantRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PutHolidayApproveRequest;
import jp.co.genuine.hm.api.domain.user.UserRepository;
import jp.co.genuine.hm.api.domain.user.alert.HolidayAlert;
import jp.co.genuine.hm.api.service.user.UserService;

@Service
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	private HolidayApplicationService holidayApplicationService;
	@Autowired
	private HolidayFactory holidayFactory;
	@Autowired
	private HolidayListService holidayListService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

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
	public Double getHolidayDays(String userId, String kind) {
		UserId owner = new UserId(Integer.parseInt(userId));

		HolidayList holidayList = holidayListService.findHolidayList(owner);

		KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(kind);
		Date today = Date.from(ZonedDateTime.now().toInstant());

		return holidayList.getDays(kindOfHoliday, today);
	}

	@Override
	public HolidayList getHoliday(UserId userId) {
		return holidayListService.findHolidayList(userId);
	}

	@Override
	public List<HolidayApplication> getHolidayApplication(String userId, String status) {
		UserId applicantId = new UserId(Integer.parseInt(userId));

		if(status.equals("ALL"))
			return holidayApplicationService.findByAplicantId(applicantId);

		HolidayApplicationStatus holidayApplicationStatus = HolidayApplicationStatus.valueOf(status);

		return holidayApplicationService.findByAplicantIdAndStatus(applicantId, holidayApplicationStatus);
	}

	@Override
	public List<HolidayApplication> getApplyingHoliday(String apploverId) {

		List<HolidayApplication> applyingHolidays = holidayApplicationService.findByStatus(HolidayApplicationStatus.APPLYING);
		List<Integer> managementUserIds = userService.findManagementUserIds(Integer.parseInt(apploverId));

		Stream<HolidayApplication> applyingHolidaysStream = applyingHolidays.stream();
		Stream<HolidayApplication> managedApplyingHolidaysStream = applyingHolidaysStream.filter(holidayApplication ->  managementUserIds.contains(holidayApplication.getApplicantId().getValue()));

		return managedApplyingHolidaysStream.collect(Collectors.toList());
	}

	@Override
	public HolidayList postHolidayGrant(PostHolidayGrantRequest request) throws ParseException {
		HolidayGrantDTO holidayGrantDTO = holidayFactory.create(request);
		return holidayListService.grantHoliday(holidayGrantDTO.getUserId(), holidayGrantDTO.getKindOfHoliday(),
				holidayGrantDTO.getDays(), holidayGrantDTO.getGrantedDate(), holidayGrantDTO.getExpirationDate());
	}

	@Override
	public HolidayAlert getHolidayAlert(String userId) {
		jp.co.genuine.hm.api.domain.user.UserId owner = new jp.co.genuine.hm.api.domain.user.UserId(userId);

		return userRepository.findHolidayAlert(owner);
	}

}
