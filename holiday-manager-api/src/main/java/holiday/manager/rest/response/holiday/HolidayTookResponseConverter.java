package holiday.manager.rest.response.holiday;

import holiday.manager.domain.model.holiday.holiday.event.HolidayTook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class HolidayTookResponseConverter {

    public List<HolidayTookResponse> convert(List<HolidayTook> holidayTooks) {
        List<HolidayTookResponse> holidayTookResponses = new ArrayList<>();

        for(HolidayTook holidayTook : holidayTooks)
            holidayTookResponses.add(convert(holidayTook));

        return holidayTookResponses;
    }

    public HolidayTookResponse convert(HolidayTook holidayTook) {
        String eventId = holidayTook.getEventId();
        String kind = holidayTook.getKind().name();
        Date date = holidayTook.getDate();
        double days = holidayTook.getDays();
        String applicationId = holidayTook.getApplicationId().getValue();
        int userId = holidayTook.getOwner().getValue();

        return new HolidayTookResponse(eventId, kind, date, days, applicationId, userId);
    }
}
