package jp.co.genuine.hm.service.holiday;

import jp.co.genuine.hm.model.holiday.application.HolidayApplication;
import jp.co.genuine.hm.rest.endpoint.holiday.HolidayApplicationEndpointFactory;
import jp.co.genuine.hm.rest.response.holiday.HolidayApplicationResponse;
import jp.co.genuine.hm.rest.response.holiday.HolidayApplicationResponseConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HolidayApplicationService {

    private final RestTemplate restTemplate;

    private final HolidayApplicationEndpointFactory holidayApplicationEndpointFactory;

    private final HolidayApplicationResponseConverter holidayApplicationResponseConverter;

    public HolidayApplicationService(RestTemplate restTemplate, HolidayApplicationEndpointFactory holidayApplicationEndpointFactory, HolidayApplicationResponseConverter holidayApplicationResponseConverter) {
        this.restTemplate = restTemplate;
        this.holidayApplicationEndpointFactory = holidayApplicationEndpointFactory;
        this.holidayApplicationResponseConverter = holidayApplicationResponseConverter;
    }

    public List<HolidayApplication> getHolidayApplication(Integer applicantId) {
        ResponseEntity<HolidayApplicationResponse[]> responseEntity = restTemplate.getForEntity(holidayApplicationEndpointFactory.createGetHolidayApplicationEndPoint(applicantId), HolidayApplicationResponse[].class);
        return holidayApplicationResponseConverter.convert(responseEntity.getBody());
    }

    public List<HolidayApplication> getApplyingHolidayApplication(Integer approverId) {
        ResponseEntity<HolidayApplicationResponse[]> responseEntity = restTemplate.getForEntity(holidayApplicationEndpointFactory.createGetApplyingHolidayApplicationEndPoint(approverId), HolidayApplicationResponse[].class);
        return holidayApplicationResponseConverter.convert(responseEntity.getBody());
    }

}
