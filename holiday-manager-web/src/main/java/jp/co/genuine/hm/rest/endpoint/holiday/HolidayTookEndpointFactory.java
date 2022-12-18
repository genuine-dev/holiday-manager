package jp.co.genuine.hm.rest.endpoint.holiday;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HolidayTookEndpointFactory {
    @Value("${holiday-manager-api.base}")
    private String base;
    @Value("${holiday-manager-api.endpoint.holiday-took.get-holiday-took}")
    private String getHolidayTookEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-took.delete-holiday-took}")
    private String deleteHolidayTookEndPoint;

    public String createGetHolidayTookEndPoint(Integer userId) {
        return base + String.format(getHolidayTookEndPoint, userId);
    }

    public String createDeleteHolidayTookEndPoint(Integer userId, String eventId) {
        return base + String.format(deleteHolidayTookEndPoint, userId, eventId);
    }
}
