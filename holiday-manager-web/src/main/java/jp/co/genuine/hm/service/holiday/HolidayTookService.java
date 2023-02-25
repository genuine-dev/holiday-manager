package jp.co.genuine.hm.service.holiday;

import jp.co.genuine.hm.model.holiday.took.HolidayTook;
import jp.co.genuine.hm.rest.endpoint.holiday.HolidayTookEndpointFactory;
import jp.co.genuine.hm.rest.response.holiday.HolidayTookResponse;
import jp.co.genuine.hm.rest.response.holiday.HolidayTookResponseConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HolidayTookService {
    private final RestTemplate restTemplate;
    private final HolidayTookEndpointFactory holidayTookEndpointFactory;
    private final HolidayTookResponseConverter holidayTookResponseConverter;

    public HolidayTookService(RestTemplate restTemplate, HolidayTookEndpointFactory holidayTookEndpointFactory, HolidayTookResponseConverter holidayTookResponseConverter) {
        this.restTemplate = restTemplate;
        this.holidayTookEndpointFactory = holidayTookEndpointFactory;
        this.holidayTookResponseConverter = holidayTookResponseConverter;
    }

    public List<HolidayTook> getHolidayTook(Integer userId) {
        ResponseEntity<HolidayTookResponse[]> responseEntity = restTemplate.getForEntity(holidayTookEndpointFactory.createGetHolidayTooksEndPoint(userId), HolidayTookResponse[].class);
        return holidayTookResponseConverter.convert(responseEntity.getBody());
    }
}
