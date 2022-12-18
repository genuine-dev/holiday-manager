package jp.co.genuine.hm.service.holiday;

import jp.co.genuine.hm.model.holiday.HolidayAlert;
import jp.co.genuine.hm.rest.endpoint.holiday.HolidayEndpointFactory;
import jp.co.genuine.hm.rest.response.holiday.HolidayAlertResponse;
import jp.co.genuine.hm.rest.response.holiday.HolidayAlertResponseConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HolidayService {

    private final RestTemplate restTemplate;

    private final HolidayEndpointFactory holidayEndpointFactory;

    private final HolidayAlertResponseConverter holidayAlertResponseConverter;

    public HolidayService(RestTemplate restTemplate, HolidayEndpointFactory holidayEndpointFactory, HolidayAlertResponseConverter holidayAlertResponseConverter) {
        this.restTemplate = restTemplate;
        this.holidayEndpointFactory = holidayEndpointFactory;
        this.holidayAlertResponseConverter = holidayAlertResponseConverter;
    }

    public double getRemainingDays(Integer userId) {
        return restTemplate.getForObject(holidayEndpointFactory.createGetHolidayDaysEndpoint(userId), Double.class);
    }

    public HolidayAlert getHolidayAlert(Integer userId) {
        HolidayAlertResponse holidayAlertResponse = restTemplate.getForObject(holidayEndpointFactory.createGetHolidayAlertEndpoint(userId), HolidayAlertResponse.class);
        return holidayAlertResponseConverter.convert(holidayAlertResponse);
    }
}
