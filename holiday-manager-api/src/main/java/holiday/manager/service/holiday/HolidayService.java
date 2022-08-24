package holiday.manager.service.holiday;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import holiday.manager.application.query.holiday.application.HolidayApplicationQueryService;
import holiday.manager.application.service.holiday.application.HolidayApplicationService;
import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.holiday.HolidayFactory;
import holiday.manager.domain.holiday.dto.*;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.domain.user.UserRepository;
import holiday.manager.rest.request.holiday.DeleteHolidayCancelRequest;
import holiday.manager.rest.request.holiday.DeleteHolidayRejectRequest;
import holiday.manager.rest.request.holiday.PostHolidayApplyRequest;
import holiday.manager.rest.request.holiday.PostHolidayGrantRequest;
import holiday.manager.rest.request.holiday.PutHolidayApproveRequest;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
	private final HolidayApplicationService holidayApplicationService;
	private final HolidayApplicationQueryService holidayApplicationQueryService;
	private final HolidayFactory holidayFactory;
	private final HolidayListService holidayListService;
	private final UserRepository userRepository;
	private final UserService userService;

	public HolidayService(HolidayApplicationService holidayApplicationService, HolidayApplicationQueryService holidayApplicationQueryService, HolidayFactory holidayFactory, HolidayListService holidayListService, UserRepository userRepository, UserService userService) {
		this.holidayApplicationService = holidayApplicationService;
		this.holidayApplicationQueryService = holidayApplicationQueryService;
		this.holidayFactory = holidayFactory;
		this.holidayListService = holidayListService;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public HolidayApplication postHolidayApply(PostHolidayApplyRequest request) throws ParseException {
		//TODO:DTOの必要性無いのでは
		HolidayApplyDTO holidayApplyDTO = holidayFactory.create(request);
		return holidayApplicationService.apply(holidayApplyDTO.getKindOfHoliday(), holidayApplyDTO.getHolidayType(),
				holidayApplyDTO.getApplyDate(), holidayApplyDTO.getApplicant());
	}

	public HolidayApplication putHolidayApprove(PutHolidayApproveRequest request) {
		//TODO:DTOの必要性無いのでは
		HolidayApproveDTO holidayApproveDTO = holidayFactory.create(request);
		return holidayApplicationService.approve(holidayApproveDTO.getApplicationId(), holidayApproveDTO.getApprover());
	}

	public HolidayApplication deleteHolidayReject(DeleteHolidayRejectRequest request) {
		//TODO:DTOの必要性無いのでは
		HolidayRejectDTO holidayRejectDTO = holidayFactory.create(request);
		return holidayApplicationService.reject(holidayRejectDTO.getApplicationId(), holidayRejectDTO.getApprover());
	}

	public HolidayApplication deleteHolidayCancel(DeleteHolidayCancelRequest request) {
		//TODO:DTOの必要性無いのでは
		HolidayCancelDTO holidayCancelDTO = holidayFactory.create(request);
		return holidayApplicationService.cancel(holidayCancelDTO.getApplicationId(), holidayCancelDTO.getApplicant());
	}

	public Double getHolidayDays(String userId, String kind) {
		UserId owner = new UserId(Integer.parseInt(userId));

		HolidayList holidayList = holidayListService.findHolidayList(owner);

		KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(kind);
		//TODO:timeAPIを使いたい
		Date today = Date.from(ZonedDateTime.now().toInstant());

		return holidayList.getDays(kindOfHoliday, today);
	}

	public HolidayList getHoliday(UserId userId) {
		return holidayListService.findHolidayList(userId);
	}

	public List<HolidayApplication> getHolidayApplication(String userId, String status) {
		UserId applicantId = new UserId(Integer.parseInt(userId));

		if(status.equals("ALL"))
			return holidayApplicationQueryService.findByAplicantId(applicantId);

		HolidayApplicationStatus holidayApplicationStatus = HolidayApplicationStatus.valueOf(status);

		return holidayApplicationQueryService.findByAplicantIdAndStatus(applicantId, holidayApplicationStatus);
	}

	public List<HolidayApplication> getApplyingHoliday(String apploverId) {

		List<HolidayApplication> applyingHolidays = holidayApplicationQueryService.findByStatus(HolidayApplicationStatus.APPLYING);
		List<Integer> managementUserIds = userService.findManagementUserIds(Integer.parseInt(apploverId));
		return applyingHolidays.stream().filter(holidayApplication ->  managementUserIds.contains(holidayApplication.getApplicantId().getValue())).collect(Collectors.toList());
	}

	public HolidayList postHolidayGrant(PostHolidayGrantRequest request) throws ParseException {
		//TODO:DTOの必要性無いのでは
		HolidayGrantDTO holidayGrantDTO = holidayFactory.create(request);
		return holidayListService.grantHoliday(holidayGrantDTO.getUserId(), holidayGrantDTO.getKindOfHoliday(),
				holidayGrantDTO.getDays(), holidayGrantDTO.getGrantedDate(), holidayGrantDTO.getExpirationDate());
	}

	public HolidayAlert getHolidayAlert(String userId) {
		holiday.manager.domain.user.UserId owner = new holiday.manager.domain.user.UserId(userId);

		return userRepository.findHolidayAlert(owner);
	}
}
