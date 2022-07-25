package holiday.manager.controller.holiday;

import java.text.ParseException;
import java.util.List;

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
public class HolidayController {
	@Autowired
	HolidayService holidayService;

	@ApiOperation("有給取得アラート")
	@GetMapping("/holiday/alert/{user_id}")
	public ResponseEntity<HolidayAlert> getHolidayAlert(@PathVariable("user_id") String userId) {
		HolidayAlert holidayAlert = holidayService.getHolidayAlert(userId);
		return new ResponseEntity<HolidayAlert>(holidayAlert, HttpStatus.OK);
	}

	@ApiOperation("有給残日数取得")
	@GetMapping("/holiday/{user_id}/days")
	public ResponseEntity<Double> getHolidayDays(@PathVariable("user_id") String userId,
			@RequestParam(name = "kind", defaultValue = "PAYED_LEAVE") String kind) {
		Double days = holidayService.getHolidayDays(userId, kind);
		return new ResponseEntity<Double>(days, HttpStatus.OK);
	}

	@ApiOperation("ステータスから有給申請リスト取得(全取得はALLを指定)")
	@GetMapping("/holiday/{user_id}/application")
	public ResponseEntity<List<HolidayApplication>> getHolidayApplication(@PathVariable("user_id") String userId,
			@RequestParam(name = "status", defaultValue = "ALL") String status) {
		List<HolidayApplication> holidayApplications = holidayService.getHolidayApplication(userId, status);
		return new ResponseEntity<List<HolidayApplication>>(holidayApplications, HttpStatus.OK);
	}

	@ApiOperation("承認待ち有給申請リスト取得")
	@GetMapping("/holiday/applying/{applover_id}")
	public ResponseEntity<List<HolidayApplication>> getApplyingHoliday(@PathVariable("applover_id") String userId) {
		List<HolidayApplication> applyingHolidays = holidayService.getApplyingHoliday(userId);
		return new ResponseEntity<List<HolidayApplication>>(applyingHolidays, HttpStatus.OK);
	}

	@ApiOperation("有給付与")
	@PostMapping("/holiday/grant")
	public ResponseEntity<HolidayList> postHolidayGrant(@RequestBody PostHolidayGrantRequest request)
			throws ParseException {
		HolidayList holidayList = holidayService.postHolidayGrant(request);
		return new ResponseEntity<HolidayList>(holidayList, HttpStatus.OK);
	}

	@ApiOperation("有給申請")
	@PostMapping("/holiday/apply")
	public ResponseEntity<HolidayApplication> postHolidayApply(@RequestBody PostHolidayApplyRequest request)
			throws ParseException {
		HolidayApplication application = holidayService.postHolidayApply(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}

	@ApiOperation("有給承認")
	@PutMapping("/holiday/approve")
	public ResponseEntity<HolidayApplication> putHolidayApprove(@RequestBody PutHolidayApproveRequest request) {
		HolidayApplication application = holidayService.putHolidayApprove(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}

	@ApiOperation("有給却下")
	@DeleteMapping("/holiday/reject")
	public ResponseEntity<HolidayApplication> deleteHolidayReject(@RequestBody DeleteHolidayRejectRequest request) {
		HolidayApplication application = holidayService.deleteHolidayReject(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}

	@ApiOperation("有給キャンセル")
	@DeleteMapping("/holiday/cancel")
	public ResponseEntity<HolidayApplication> deleteHolidayCancel(@RequestBody DeleteHolidayCancelRequest request) {
		HolidayApplication application = holidayService.deleteHolidayCancel(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}
}
