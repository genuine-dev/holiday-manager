package holiday.manager.controller.holiday;

import java.text.ParseException;
import java.util.List;

import holiday.manager.application.query.holiday.application.dto.HolidayApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.rest.request.holiday.DeleteHolidayCancelRequest;
import holiday.manager.rest.request.holiday.DeleteHolidayRejectRequest;
import holiday.manager.rest.request.holiday.PostHolidayApplyRequest;
import holiday.manager.rest.request.holiday.PostHolidayGrantRequest;
import holiday.manager.rest.request.holiday.PutHolidayApproveRequest;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.service.holiday.HolidayService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
	private final HolidayService holidayService;

	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	@ApiOperation("有給取得アラート")
	@GetMapping("/alert/{user_id}")
	public ResponseEntity<HolidayAlert> getHolidayAlert(@PathVariable("user_id") String userId) {
		HolidayAlert holidayAlert = holidayService.getHolidayAlert(userId);
		return new ResponseEntity<HolidayAlert>(holidayAlert, HttpStatus.OK);
	}

	@ApiOperation("有給残日数取得")
	@GetMapping("/{user_id}/days")
	public ResponseEntity<Double> getHolidayDays(@PathVariable("user_id") String userId,
			@RequestParam(name = "kind", defaultValue = "PAYED_LEAVE") String kind) {
		Double days = holidayService.getHolidayDays(userId, kind);
		return new ResponseEntity<Double>(days, HttpStatus.OK);
	}

	@ApiOperation("ステータスから有給申請リスト取得(全取得はALLを指定)")
	@GetMapping("/{user_id}/application")
	public ResponseEntity<List<HolidayApplicationDto>> getHolidayApplication(@PathVariable("user_id") String userId,
			@RequestParam(name = "status", defaultValue = "ALL") String status) {
		List<HolidayApplicationDto> holidayApplicationDtos = holidayService.getHolidayApplication(userId, status);
		return new ResponseEntity<List<HolidayApplicationDto>>(holidayApplicationDtos, HttpStatus.OK);
	}

	@ApiOperation("承認待ち有給申請リスト取得")
	@GetMapping("/applying/{applover_id}")
	public ResponseEntity<List<HolidayApplicationDto>> getApplyingHoliday(@PathVariable("applover_id") String userId) {
		List<HolidayApplicationDto> holidayApplicationDtos = holidayService.getApplyingHoliday(userId);
		return new ResponseEntity<List<HolidayApplicationDto>>(holidayApplicationDtos, HttpStatus.OK);
	}

	@ApiOperation("有給付与")
	public ResponseEntity<Void> postHolidayGrant(@RequestBody PostHolidayGrantRequest request)
			throws ParseException {
		holidayService.postHolidayGrant(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ApiOperation("有給申請")
	@PostMapping("/apply")
	public ResponseEntity<HolidayApplicationDto> postHolidayApply(@RequestBody PostHolidayApplyRequest request)
			throws ParseException {
		HolidayApplicationDto holidayApplicationDto = holidayService.postHolidayApply(request);
		return new ResponseEntity<HolidayApplicationDto>(holidayApplicationDto, HttpStatus.OK);
	}

	@ApiOperation("有給承認")
	@PutMapping("/approve")
	public ResponseEntity<HolidayApplicationDto> putHolidayApprove(@RequestBody PutHolidayApproveRequest request) {
		HolidayApplicationDto holidayApplicationDto = holidayService.putHolidayApprove(request);
		return new ResponseEntity<HolidayApplicationDto>(holidayApplicationDto, HttpStatus.OK);
	}

	@ApiOperation("有給却下")
	@DeleteMapping("/reject")
	public ResponseEntity<HolidayApplicationDto> deleteHolidayReject(@RequestBody DeleteHolidayRejectRequest request) {
		HolidayApplicationDto holidayApplicationDto = holidayService.deleteHolidayReject(request);
		return new ResponseEntity<HolidayApplicationDto>(holidayApplicationDto, HttpStatus.OK);
	}

	@ApiOperation("有給キャンセル")
	@DeleteMapping("/cancel")
	public ResponseEntity<HolidayApplicationDto> deleteHolidayCancel(@RequestBody DeleteHolidayCancelRequest request) {
		HolidayApplicationDto holidayApplicationDto = holidayService.deleteHolidayCancel(request);
		return new ResponseEntity<HolidayApplicationDto>(holidayApplicationDto, HttpStatus.OK);
	}
}
