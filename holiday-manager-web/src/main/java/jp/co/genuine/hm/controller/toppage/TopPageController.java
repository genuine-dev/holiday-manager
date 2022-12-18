package jp.co.genuine.hm.controller.toppage;

import jp.co.genuine.hm.model.holiday.HolidayAlert;
import jp.co.genuine.hm.model.holiday.application.HolidayApplication;
import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.service.holiday.HolidayApplicationService;
import jp.co.genuine.hm.service.holiday.HolidayService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TopPageController {

    private final HolidayService holidayService;
    private final HolidayApplicationService holidayApplicationService;

    public TopPageController(HolidayService holidayService, HolidayApplicationService holidayApplicationService) {
        this.holidayService = holidayService;
        this.holidayApplicationService = holidayApplicationService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String topPage(Model model) {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        double remainingDays = holidayService.getRemainingDays(user.getAccount().getUserId());
        model.addAttribute("remainingDays", remainingDays);

        HolidayAlert holidayAlert = holidayService.getHolidayAlert(user.getAccount().getUserId());
        model.addAttribute("holidayAlert", holidayAlert);

        List<HolidayApplication> holidayApplications = holidayApplicationService.getHolidayApplication(user.getAccount().getUserId());
        model.addAttribute("holidayApplications", holidayApplications);

        List<HolidayApplication> applyingHolidayApplications = holidayApplicationService.getApplyingHolidayApplication(user.getAccount().getUserId());
        model.addAttribute("applyingHolidayApplications", holidayApplications);

        return "index";
    }
}
