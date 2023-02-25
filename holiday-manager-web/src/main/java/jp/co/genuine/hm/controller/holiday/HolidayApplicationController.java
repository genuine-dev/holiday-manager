package jp.co.genuine.hm.controller.holiday;

import jp.co.genuine.hm.model.holiday.KindOfHoliday;
import jp.co.genuine.hm.model.holiday.application.HolidayApplication;
import jp.co.genuine.hm.model.holiday.application.HolidayApplicationStatus;
import jp.co.genuine.hm.model.holiday.application.HolidayType;
import jp.co.genuine.hm.model.holiday.application.form.HolidayApplicationApplyForm;
import jp.co.genuine.hm.model.holiday.application.form.HolidayApplicationForm;
import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.service.holiday.HolidayApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("holiday/application")
public class HolidayApplicationController {

    @Autowired
    private final HolidayApplicationService holidayApplicationService;

    public HolidayApplicationController(HolidayApplicationService holidayApplicationService) {
        this.holidayApplicationService = holidayApplicationService;
    }

    @RequestMapping(path = "applying/list", method = RequestMethod.GET)
    public String holidayApplicationList(Model model) {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<HolidayApplication> applyingHolidayApplications = holidayApplicationService.getApplyingHolidayApplication(user.getAccount().getUserId());
        model.addAttribute("applyingHolidayApplications", applyingHolidayApplications);

        return "holiday/application/applying/list/holiday_application_applying_list";
    }

    @RequestMapping(path = "applying/apply/id/{id}", method = RequestMethod.POST)
    public String holidayApplicationApply(@PathVariable(value = "id", required = true) String holidayApplicationId, @ModelAttribute("holidayApplicationApplyForm") HolidayApplicationApplyForm holidayApplicationApplyForm, Model model) {
        HolidayApplication holidayApplication = holidayApplicationService.getHolidayApplicationById(holidayApplicationId);
        model.addAttribute("kindOfHolidayMap", KindOfHoliday.asMap());
        model.addAttribute("holidayTypeMap", HolidayType.asMap());
        model.addAttribute("holidayApplicationStatusMap", HolidayApplicationStatus.asMap());
        return "holiday/application/apply/holiday_application_apply";
    }

    @RequestMapping(path = "register", method = RequestMethod.GET)
    public String holidayApplicationRegister(@ModelAttribute("holidayApplicationForm") HolidayApplicationForm holidayApplicationForm, Model model) {
        model.addAttribute("kindOfHolidayMap", KindOfHoliday.asMap());
        model.addAttribute("holidayTypeMap", HolidayType.asMap());
        return "holiday/application/register/holiday_application_register";
    }

    @RequestMapping(path = "register/confirm", method = RequestMethod.POST)
    public String holidayApplicationRegisterConfirm(@ModelAttribute("holidayApplicationForm") HolidayApplicationForm holidayApplicationForm, Model model) {
        model.addAttribute("kindOfHolidayMap", KindOfHoliday.asMap());
        model.addAttribute("holidayTypeMap", HolidayType.asMap());
        return "holiday/application/register/holiday_application_register_confirm";
    }

    @RequestMapping(path = "register/complete", method = RequestMethod.POST)
    public String holidayApplicationRegisterComplete(@ModelAttribute("holidayApplicationForm") HolidayApplicationForm holidayApplicationForm, Model model) {
        LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        holidayApplicationService.postHolidayApplication(holidayApplicationForm, user.getAccount().getUserId());
        return "holiday/application/register/holiday_application_register_complete";
    }
}
