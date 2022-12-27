package jp.co.genuine.hm.rest.response.holiday;

import jp.co.genuine.hm.model.holiday.KindOfHoliday;
import jp.co.genuine.hm.model.holiday.application.HolidayApplication;
import jp.co.genuine.hm.model.holiday.application.HolidayApplicationId;
import jp.co.genuine.hm.model.holiday.application.HolidayApplicationStatus;
import jp.co.genuine.hm.model.holiday.application.HolidayType;
import jp.co.genuine.hm.model.user.UserId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class HolidayApplicationResponseConverter {
    public List<HolidayApplication> convert(HolidayApplicationResponse[] holidayApplicationResponses) {
        ArrayList result = new ArrayList();

        for(HolidayApplicationResponse holidayApplicationResponse : holidayApplicationResponses)
            result.add(convert(holidayApplicationResponse));

        return result;

    }

    public HolidayApplication convert(HolidayApplicationResponse holidayApplicationResponse) {
        HolidayApplicationId holidayApplicationId = new HolidayApplicationId(holidayApplicationResponse.getId());
        KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(holidayApplicationResponse.getKind());
        HolidayType holidayType = HolidayType.valueOf(holidayApplicationResponse.getType());
        HolidayApplicationStatus holidayApplicationStatus = HolidayApplicationStatus.valueOf(holidayApplicationResponse.getStatus());
        Date date = holidayApplicationResponse.getDate();
        UserId applicantId = new UserId(holidayApplicationResponse.getApplicantId());
        return new HolidayApplication(holidayApplicationId, kindOfHoliday, holidayType, holidayApplicationStatus, date, applicantId);
    }
}
