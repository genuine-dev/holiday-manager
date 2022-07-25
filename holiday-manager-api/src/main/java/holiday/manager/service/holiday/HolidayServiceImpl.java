package holiday.manager.service.holiday;

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
import holiday.manager.domain.holiday.HolidayFactory;
import holiday.manager.domain.holiday.dto.HolidayApplyDTO;
import holiday.manager.domain.holiday.dto.HolidayApproveDTO;
import holiday.manager.domain.holiday.dto.HolidayCancelDTO;
import holiday.manager.domain.holiday.dto.HolidayGrantDTO;
import holiday.manager.domain.holiday.dto.HolidayRejectDTO;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.rest.request.holiday.DeleteHolidayCancelRequest;
import holiday.manager.rest.request.holiday.DeleteHolidayRejectRequest;
import holiday.manager.rest.request.holiday.PostHolidayApplyRequest;
import holiday.manager.rest.request.holiday.PostHolidayGrantRequest;
import holiday.manager.rest.request.holiday.PutHolidayApproveRequest;
import holiday.manager.domain.user.UserRepository;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.service.user.UserService;

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
		holiday.manager.domain.user.UserId owner = new holiday.manager.domain.user.UserId(userId);

		return userRepository.findHolidayAlert(owner);
	}

}
