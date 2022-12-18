package holiday.manager.controller.holiday;

import holiday.manager.rest.response.holiday.HolidayTookResponse;
import holiday.manager.service.holiday.HolidayTookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holiday/took")
public class HolidayTookController {
    private final HolidayTookService holidayTookServiceService;

    public HolidayTookController(HolidayTookService holidayTookServiceService) {
        this.holidayTookServiceService = holidayTookServiceService;
    }

    @ApiOperation("有給実績リスト取得")
    @GetMapping("/userId/{user_id}")
    public ResponseEntity<List<HolidayTookResponse>> getHolidayTook(@PathVariable("user_id") Integer userId) {
        List<HolidayTookResponse> holidayTookResponses = holidayTookServiceService.getHolidayTook(userId);
        return new ResponseEntity<List<HolidayTookResponse>>(holidayTookResponses, HttpStatus.OK);
    }

    @ApiOperation("有給実績削除")
    @DeleteMapping("/userId/{user_id}/eventId/{event_id}")
    public ResponseEntity<Void> deleteHolidayTook(@PathVariable("user_id") Integer userId, @PathVariable("event_id") String eventId) {
        holidayTookServiceService.deleteHolidayTook(userId, eventId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
