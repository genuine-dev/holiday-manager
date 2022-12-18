package jp.co.genuine.hm.rest.endpoint.holiday;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HolidayApplicationEndpointFactory {
    @Value("${holiday-manager-api.base}")
    private String base;
    @Value("${holiday-manager-api.endpoint.holiday-application.get-holiday-application}")
    private String getHolidayApplicationEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.get-applying-holiday-application}")
    private String getApplyingHolidayApplicationEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.post-holiday-apply}")
    private String postHolidayApplyEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.delete-holiday-reject}")
    private String deleteHolidayRejectEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.delete-holiday-cancel}")
    private String deleteHolidayCancelEndPoint;

    public String createGetHolidayApplicationEndPoint(Integer applicantId) {
        return base + String.format(getHolidayApplicationEndPoint, applicantId);
    }

    public String createGetApplyingHolidayApplicationEndPoint(Integer approverId) {
        return base + String.format(getApplyingHolidayApplicationEndPoint, approverId);
    }

    public String createPostHolidayApplyEndPoint() {
        return base + postHolidayApplyEndPoint;
    }

    public String createDeleteHolidayRejectEndPoint() {
        return base + deleteHolidayRejectEndPoint;
    }

    public String createDeleteHolidayCancelEndPoint() {
        return base + deleteHolidayCancelEndPoint;
    }
}
