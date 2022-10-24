package holiday.manager.service.holiday;

import holiday.manager.application.service.holiday.holiday.HolidayListService;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.holiday.holiday.event.HolidayTook;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.rest.response.holiday.HolidayTookResponse;
import holiday.manager.rest.response.holiday.HolidayTookResponseConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayTookService {
    private final HolidayListService holidayListService;
    private final HolidayTookResponseConverter holidayTookResponseConverter;

    public HolidayTookService(HolidayListService holidayListService, HolidayTookResponseConverter holidayTookResponseConverter) {
        this.holidayListService = holidayListService;
        this.holidayTookResponseConverter = holidayTookResponseConverter;
    }

    public List<HolidayTookResponse> getHolidayTook(UserId userId) {
        HolidayList holidayList = holidayListService.findHolidayList(userId);
        List<HolidayTook> holidayTooks = holidayList.takeHistory();

        return holidayTookResponseConverter.convert(holidayTooks);
    }

    public void deleteHolidayTook(UserId userId, String eventId) {
        holidayListService.cancelTakeHoliday(userId, eventId);
    }
}
