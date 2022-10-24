package holiday.manager.service.holiday;

import holiday.manager.application.query.holiday.application.HolidayApplicationQueryService;
import holiday.manager.application.query.holiday.application.dto.HolidayApplicationDto;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.user.User;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.rest.request.holiday.DeleteHolidayCancelRequest;
import holiday.manager.rest.request.holiday.DeleteHolidayRejectRequest;
import holiday.manager.rest.request.holiday.PostHolidayApplyRequest;
import holiday.manager.rest.request.holiday.PutHolidayApproveRequest;
import holiday.manager.rest.response.holiday.HolidayApplicationResponse;
import holiday.manager.rest.response.holiday.HolidayApplicationResponseConverter;
import holiday.manager.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("ApiHolidayApplicationService")
public class HolidayApplicationService {
    private final holiday.manager.application.service.holiday.application.HolidayApplicationService holidayApplicationService;
    private final HolidayApplicationQueryService holidayApplicationQueryService;
    private final HolidayApplicationResponseConverter holidayApplicationResponseConverter;
    private final UserService userService;

    public HolidayApplicationService(holiday.manager.application.service.holiday.application.HolidayApplicationService holidayApplicationService, HolidayApplicationQueryService holidayApplicationQueryService, HolidayApplicationResponseConverter holidayApplicationResponseConverter, UserService userService) {
        this.holidayApplicationService = holidayApplicationService;
        this.holidayApplicationQueryService = holidayApplicationQueryService;
        this.holidayApplicationResponseConverter = holidayApplicationResponseConverter;
        this.userService = userService;
    }

    public List<HolidayApplicationResponse> getHolidayApplicationBy(Integer applicantId, String status) {
        UserId userId = new UserId(applicantId);

        if (status.equals("ALL")) {
            List<HolidayApplicationDto> holidayApplicationDtos = holidayApplicationQueryService.findByAplicantId(userId);
            return holidayApplicationResponseConverter.convert(holidayApplicationDtos);
        }

        HolidayApplicationStatus holidayApplicationStatus = HolidayApplicationStatus.valueOf(status);
        List<HolidayApplicationDto> holidayApplicationDtos = holidayApplicationQueryService.findByAplicantIdAndStatus(userId, holidayApplicationStatus);
        return holidayApplicationResponseConverter.convert(holidayApplicationDtos);
    }

    public List<HolidayApplicationResponse> getHolidayApplicationBy(Integer apploverId, HolidayApplicationStatus holidayApplicationStatus) {
        List<HolidayApplicationDto> applyingHolidays = holidayApplicationQueryService.findByStatus(holidayApplicationStatus);
        List<Integer> managementUserIds = userService.findManagementUserIds(apploverId);
        List<HolidayApplicationDto> holidayApplicationDtos = applyingHolidays.stream().filter(holidayApplication -> managementUserIds.contains(holidayApplication.getAplicantId())).collect(Collectors.toList());
        return holidayApplicationResponseConverter.convert(holidayApplicationDtos);
    }

    public HolidayApplicationResponse postHolidayApply(PostHolidayApplyRequest request) {
        User user = userService.applicantByUserId(new UserId(request.getApplicantId()));
        KindOfHoliday kindOfHoliday = request.getKindOfHoliday();
        HolidayType holidayType = request.getHolidayType();
        Date date = request.getDate();

        HolidayApplication holidayApplication = holidayApplicationService.apply(kindOfHoliday, holidayType, date, user);
        return holidayApplicationResponseConverter.convert(holidayApplication);
    }

    public HolidayApplicationResponse putHolidayApprove(PutHolidayApproveRequest request) {
        HolidayApplicationId applicationId = new HolidayApplicationId(request.getHolidayApplicationId());
        User user = userService.approverByUserId(new UserId(request.getApproverId()));

        HolidayApplication holidayApplication = holidayApplicationService.approve(applicationId, user);
        return holidayApplicationResponseConverter.convert(holidayApplication);
    }

    public void deleteHolidayReject(DeleteHolidayRejectRequest request) {
        HolidayApplicationId applicationId = new HolidayApplicationId(request.getHolidayApplicationId());
        User user = userService.approverByUserId(new UserId(request.getApproverId()));

        holidayApplicationService.reject(applicationId, user);
    }

    public void deleteHolidayCancel(DeleteHolidayCancelRequest request) {
        HolidayApplicationId applicationId = new HolidayApplicationId(request.getHolidayApplicationId());
        User user = userService.approverByUserId(new UserId(request.getApplicantId()));

        holidayApplicationService.cancel(applicationId, user);
    }
}
