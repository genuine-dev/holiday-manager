package holiday.manager.controller.holiday;

import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.rest.request.holiday.DeleteHolidayCancelRequest;
import holiday.manager.rest.request.holiday.DeleteHolidayRejectRequest;
import holiday.manager.rest.request.holiday.PostHolidayApplyRequest;
import holiday.manager.rest.request.holiday.PutHolidayApproveRequest;
import holiday.manager.rest.response.holiday.HolidayApplicationResponse;
import holiday.manager.service.holiday.HolidayApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holiday/application")
public class HolidayApplicationController {
    private final HolidayApplicationService holidayApplicationService;

    public HolidayApplicationController(HolidayApplicationService holidayApplicationService) {
        this.holidayApplicationService = holidayApplicationService;
    }

    @ApiOperation("休暇申請リスト取得(全取得はALLを指定)")
    @GetMapping("/applicantId/{applicant_id}")
    public ResponseEntity<List<HolidayApplicationResponse>> getHolidayApplications(
            @PathVariable("applicant_id") Integer applicantId,
            @RequestParam(name = "status", defaultValue = "ALL") String status) {
        List<HolidayApplicationResponse> holidayApplicationResponses = holidayApplicationService.getHolidayApplicationBy(applicantId, status);
        return new ResponseEntity<List<HolidayApplicationResponse>>(holidayApplicationResponses, HttpStatus.OK);
    }

    @ApiOperation("休暇申請取得")
    @GetMapping("/id/{holiday_application_id}")
    public ResponseEntity<HolidayApplicationResponse> getHolidayApplication(
            @PathVariable("applicant_id") String holidayApplicationId) {
        HolidayApplicationResponse holidayApplicationResponse = holidayApplicationService.getHolidayApplicationBy(holidayApplicationId);
        return new ResponseEntity<>(holidayApplicationResponse, HttpStatus.OK);
    }

    @ApiOperation("承認待ち休暇申請リスト取得")
    @GetMapping("/approverId/{applover_id}")
    public ResponseEntity<List<HolidayApplicationResponse>> getHolidayApplication(
            @PathVariable("applover_id") Integer apploverId, @RequestParam(name = "status", defaultValue = "APPLYING") HolidayApplicationStatus holidayApplicationStatus) {
        List<HolidayApplicationResponse> holidayApplicationResponses = holidayApplicationService.getHolidayApplicationBy(apploverId, holidayApplicationStatus);
        return new ResponseEntity<List<HolidayApplicationResponse>>(holidayApplicationResponses, HttpStatus.OK);
    }

    @ApiOperation("休暇申請提出")
    @PostMapping("/apply")
    // TODO:今製作中
    public ResponseEntity<HolidayApplicationResponse> postHolidayApply(@RequestBody PostHolidayApplyRequest request) {
        HolidayApplicationResponse holidayApplicationResponse = holidayApplicationService.postHolidayApply(request);
        return new ResponseEntity<HolidayApplicationResponse>(holidayApplicationResponse, HttpStatus.OK);
    }

    @ApiOperation("休暇申請承認")
    @PutMapping("/approve")
    public ResponseEntity<HolidayApplicationResponse> putHolidayApprove(@RequestBody PutHolidayApproveRequest request) {
        HolidayApplicationResponse holidayApplicationResponse = holidayApplicationService.putHolidayApprove(request);
        return new ResponseEntity<HolidayApplicationResponse>(holidayApplicationResponse, HttpStatus.OK);
    }

    @ApiOperation("休暇申請却下")
    @DeleteMapping("/reject")
    public ResponseEntity<Void> deleteHolidayReject(@RequestBody DeleteHolidayRejectRequest request) {
        holidayApplicationService.deleteHolidayReject(request);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("休暇申請キャンセル")
    @DeleteMapping("/cancel")
    public ResponseEntity<Void> deleteHolidayCancel(@RequestBody DeleteHolidayCancelRequest request) {
        holidayApplicationService.deleteHolidayCancel(request);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
