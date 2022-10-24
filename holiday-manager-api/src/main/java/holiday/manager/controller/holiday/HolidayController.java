package holiday.manager.controller.holiday;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.rest.request.holiday.PostHolidayGrantRequest;
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
	@GetMapping("/alert/userId/{user_id}")
	public ResponseEntity<HolidayAlert> getHolidayAlert(@PathVariable("user_id") Integer userId) {
		HolidayAlert holidayAlert = holidayService.getHolidayAlert(userId);
		return new ResponseEntity<HolidayAlert>(holidayAlert, HttpStatus.OK);
	}

	@ApiOperation("有給残日数取得")
	@GetMapping("/days/userId/{user_id}")
	public ResponseEntity<Double> getHolicayDays(@PathVariable("user_id") Integer userId,
			@RequestParam(name = "kind", defaultValue = "PAYED_LEAVE") String kind) {
		Double days = holidayService.getHolidayDays(userId, kind);
		return new ResponseEntity<Double>(days, HttpStatus.OK);
	}

	@ApiOperation("有給付与")
	@PostMapping("/grant")
	public ResponseEntity<Void> postHolidayGrant(@RequestBody PostHolidayGrantRequest request) throws ParseException {
		holidayService.postHolidayGrant(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
