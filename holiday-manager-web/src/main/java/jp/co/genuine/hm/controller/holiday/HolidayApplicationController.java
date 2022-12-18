package jp.co.genuine.hm.controller.holiday;

import jp.co.genuine.hm.service.holiday.HolidayApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("holiday/application")
public class HolidayApplicationController {

    @Autowired
    private final HolidayApplicationService holidayApplicationService;

    public HolidayApplicationController(HolidayApplicationService holidayApplicationService) {
        this.holidayApplicationService = holidayApplicationService;
    }
}
