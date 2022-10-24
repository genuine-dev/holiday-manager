package holiday.manager.rest.response.holiday;

import holiday.manager.application.query.holiday.application.dto.HolidayApplicationDto;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class HolidayApplicationResponseConverter {
    public List<HolidayApplicationResponse> convert(List<HolidayApplicationDto> holidayApplicationDtos) {
        List<HolidayApplicationResponse> holidayApplicationResponses = new ArrayList<>();

        for(HolidayApplicationDto holidayApplicationDto : holidayApplicationDtos) {
            holidayApplicationResponses.add(convert(holidayApplicationDto));
        }

        return holidayApplicationResponses;
    }

    public HolidayApplicationResponse convert(HolidayApplicationDto holidayApplicationDto) {
        String id = holidayApplicationDto.getId();
        String kind = holidayApplicationDto.getKind();
        String type = holidayApplicationDto.getType();
        String status = holidayApplicationDto.getStatus();
        Date date = holidayApplicationDto.getDate();
        Integer applicantId = holidayApplicationDto.getAplicantId();

        return new HolidayApplicationResponse(id, kind, type, status, date, applicantId);
    }

    public HolidayApplicationResponse convert(HolidayApplication holidayApplication) {
        String id = holidayApplication.getId().getValue();
        String kind = holidayApplication.getKindOfHoliday().name();
        String type = holidayApplication.getHolidayType().name();
        String status = holidayApplication.getStatus().name();
        Date date = holidayApplication.getDate();
        Integer applicantId = holidayApplication.getApplicantId().getValue();

        return new HolidayApplicationResponse(id, kind, type, status, date, applicantId);
    }
}
