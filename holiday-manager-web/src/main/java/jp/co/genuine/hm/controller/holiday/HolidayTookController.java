package jp.co.genuine.hm.controller.holiday;

import jp.co.genuine.hm.model.holiday.took.HolidayTook;
import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.service.holiday.HolidayTookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("holiday/took")
public class HolidayTookController {

    @Autowired
    private final HolidayTookService holidayTookService;

    public HolidayTookController(HolidayTookService holidayTookService) {
        this.holidayTookService = holidayTookService;
    }

    @RequestMapping(path = "list", method = RequestMethod.GET)
    public String holidayTookList(Model model) {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<HolidayTook> holidayTooks = holidayTookService.getHolidayTook(user.getAccount().getUserId());
        model.addAttribute("holidayTooks", holidayTooks);

        return "holiday/took/list/holiday_took_list";
    }
}
