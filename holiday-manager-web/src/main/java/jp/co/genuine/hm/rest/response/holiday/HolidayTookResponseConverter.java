package jp.co.genuine.hm.rest.response.holiday;

import jp.co.genuine.hm.model.holiday.KindOfHoliday;
import jp.co.genuine.hm.model.holiday.application.HolidayApplicationId;
import jp.co.genuine.hm.model.holiday.took.HolidayTook;
import jp.co.genuine.hm.model.user.UserId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class HolidayTookResponseConverter {
    public List<HolidayTook> convert(HolidayTookResponse[] holidayTookResponses) {
        List<HolidayTook> result = new ArrayList<>();
        for(HolidayTookResponse holidayTookResponse : holidayTookResponses)
            result.add(convert(holidayTookResponse));
        return result;
    }

    private HolidayTook convert(HolidayTookResponse holidayTookResponse) {
        String eventId = holidayTookResponse.getEventId();
        KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(holidayTookResponse.getKind());
        Date date = holidayTookResponse.getDate();
        double days = holidayTookResponse.getDays();
        HolidayApplicationId holidayApplicationId = new HolidayApplicationId(holidayTookResponse.getApplicationId());
        UserId owner = new UserId(holidayTookResponse.getOwner());
        return new HolidayTook(eventId, kindOfHoliday, date, days, holidayApplicationId, owner);
    }
}
