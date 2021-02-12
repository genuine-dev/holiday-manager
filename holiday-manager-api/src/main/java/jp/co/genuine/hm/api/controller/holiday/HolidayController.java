package jp.co.genuine.hm.api.controller.holiday;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import holiday.manager.domain.model.holiday.application.HolidayApplication;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayCancelRequest;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayRejectRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayApplyRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PutHolidayApproveRequest;
import jp.co.genuine.hm.api.service.holiday.HolidayService;

@RestController
public class HolidayController {
	@Autowired
	HolidayService holidayService;

	@RequestMapping(path = "holiday/apply", method = RequestMethod.POST)
	public ResponseEntity<HolidayApplication> postHolidayApply(@RequestBody PostHolidayApplyRequest request) throws ParseException {
		HolidayApplication application = holidayService.postHolidayApply(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}

	@RequestMapping(path = "holiday/approve", method = RequestMethod.PUT)
	public ResponseEntity<HolidayApplication> putHolidayApprove(@RequestBody PutHolidayApproveRequest request) {
		HolidayApplication application = holidayService.putHolidayApprove(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}

	@RequestMapping(path = "holiday/reject", method = RequestMethod.DELETE)
	public ResponseEntity<HolidayApplication> deleteHolidayReject(@RequestBody DeleteHolidayRejectRequest request) {
		HolidayApplication application = holidayService.deleteHolidayReject(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}

	@RequestMapping(path = "holiday/cancel", method = RequestMethod.DELETE)

	public ResponseEntity<HolidayApplication> deleteHolidayCancel(@RequestBody DeleteHolidayCancelRequest request) {
		HolidayApplication application = holidayService.deleteHolidayCancel(request);
		return new ResponseEntity<HolidayApplication>(application, HttpStatus.OK);
	}
}
