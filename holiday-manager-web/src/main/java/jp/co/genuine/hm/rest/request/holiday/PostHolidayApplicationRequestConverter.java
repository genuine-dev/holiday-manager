package jp.co.genuine.hm.rest.request.holiday;

import jp.co.genuine.hm.model.holiday.application.form.HolidayApplicationForm;
import org.springframework.stereotype.Component;

@Component
public class PostHolidayApplicationRequestConverter {
    public PostHolidayApplicationRequest convert(HolidayApplicationForm holidayApplicationForm, Integer userId) {
        Integer applicantId = userId;
        String kindOfHoliday = holidayApplicationForm.getKindOfHoliday();
        String date = holidayApplicationForm.getDate();
        String holidayType = holidayApplicationForm.getHolidayType();
        return new PostHolidayApplicationRequest(applicantId, kindOfHoliday, date, holidayType);
    }
}
