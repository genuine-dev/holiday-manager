package jp.co.genuine.hm.rest.response.holiday;

import jp.co.genuine.hm.model.holiday.HolidayAlert;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HolidayAlertResponseConverter {
    public HolidayAlert convert(HolidayAlertResponse holidayAlertResponse) {
        if(holidayAlertResponse.isEmpty())
            return new HolidayAlert();

        Double days = holidayAlertResponse.getDays();
        Date deadline = holidayAlertResponse.getDeadline();
        return new HolidayAlert(days, deadline);
    }
}
