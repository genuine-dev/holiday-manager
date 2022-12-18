package jp.co.genuine.hm.rest.endpoint.holiday;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HolidayEndpointFactory {
    @Value("${holiday-manager-api.base}")
    private String base;
    @Value("${holiday-manager-api.endpoint.holiday.get-holiday-alert}")
    private String getHolidayAlertEndpoint;
    @Value("${holiday-manager-api.endpoint.holiday.get-holiday-days}")
    private String getHolidayDaysEndpoint;
    @Value("${holiday-manager-api.endpoint.holiday.post-holiday-grant}")
    private String postHolidayGrantEndpoint;

    public String createGetHolidayAlertEndpoint(Integer userId) {
        return base + String.format(getHolidayAlertEndpoint, userId);
    }

    public String createGetHolidayDaysEndpoint(Integer userId) {
        return base + String.format(getHolidayDaysEndpoint, userId);
    }

    public String createPostHolidayGrantEndpoint() {
        return base + postHolidayGrantEndpoint;
    }
}
