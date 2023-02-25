package jp.co.genuine.hm.rest.endpoint.holiday;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HolidayApplicationEndpointFactory {
    @Value("${holiday-manager-api.base}")
    private String base;
    @Value("${holiday-manager-api.endpoint.holiday-application.get-holiday-application}")
    private String getHolidayApplicationEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.get-holiday-applications}")
    private String getHolidayApplicationsEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.get-applying-holiday-applications}")
    private String getApplyingHolidayApplicationsEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.post-holiday-apply}")
    private String postHolidayApplyEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.put-holiday-approve}")
    private String putHolidayApproveEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.delete-holiday-reject}")
    private String deleteHolidayRejectEndPoint;
    @Value("${holiday-manager-api.endpoint.holiday-application.delete-holiday-cancel}")
    private String deleteHolidayCancelEndPoint;

    public String createGetHolidayApplicationEndPoint(String holidayApplicationId) {
        return base + String.format(getHolidayApplicationEndPoint, holidayApplicationId);
    }

    public String createGetHolidayApplicationsEndPoint(Integer applicantId) {
        return base + String.format(getHolidayApplicationsEndPoint, applicantId);
    }

    public String createGetApplyingHolidayApplicationsEndPoint(Integer approverId) {
        return base + String.format(getApplyingHolidayApplicationsEndPoint, approverId);
    }

    public String createPostHolidayApplyEndPoint() {
        return base + postHolidayApplyEndPoint;
    }

    public String createPutHolidayApproveEndPoint() {
        return base + putHolidayApproveEndPoint;
    }

    public String createDeleteHolidayRejectEndPoint() {
        return base + deleteHolidayRejectEndPoint;
    }

    public String createDeleteHolidayCancelEndPoint() {
        return base + deleteHolidayCancelEndPoint;
    }
}
