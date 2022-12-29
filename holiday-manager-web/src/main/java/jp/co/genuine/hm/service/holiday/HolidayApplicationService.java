package jp.co.genuine.hm.service.holiday;

import jp.co.genuine.hm.model.holiday.application.HolidayApplication;
import jp.co.genuine.hm.model.holiday.application.form.HolidayApplicationForm;
import jp.co.genuine.hm.rest.endpoint.holiday.HolidayApplicationEndpointFactory;
import jp.co.genuine.hm.rest.request.holiday.PostHolidayApplicationRequest;
import jp.co.genuine.hm.rest.request.holiday.PostHolidayApplicationRequestConverter;
import jp.co.genuine.hm.rest.response.holiday.HolidayApplicationResponse;
import jp.co.genuine.hm.rest.response.holiday.HolidayApplicationResponseConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HolidayApplicationService {

    private final RestTemplate restTemplate;

    private final HolidayApplicationEndpointFactory holidayApplicationEndpointFactory;

    private final HolidayApplicationResponseConverter holidayApplicationResponseConverter;

    private final PostHolidayApplicationRequestConverter postHolidayApplicationRequestConverter;

    public HolidayApplicationService(RestTemplate restTemplate, HolidayApplicationEndpointFactory holidayApplicationEndpointFactory, HolidayApplicationResponseConverter holidayApplicationResponseConverter, PostHolidayApplicationRequestConverter postHolidayApplicationRequestConverter) {
        this.restTemplate = restTemplate;
        this.holidayApplicationEndpointFactory = holidayApplicationEndpointFactory;
        this.holidayApplicationResponseConverter = holidayApplicationResponseConverter;
        this.postHolidayApplicationRequestConverter = postHolidayApplicationRequestConverter;
    }

    public List<HolidayApplication> getHolidayApplication(Integer applicantId) {
        ResponseEntity<HolidayApplicationResponse[]> responseEntity = restTemplate.getForEntity(holidayApplicationEndpointFactory.createGetHolidayApplicationEndPoint(applicantId), HolidayApplicationResponse[].class);
        return holidayApplicationResponseConverter.convert(responseEntity.getBody());
    }

    public List<HolidayApplication> getApplyingHolidayApplication(Integer approverId) {
        ResponseEntity<HolidayApplicationResponse[]> responseEntity = restTemplate.getForEntity(holidayApplicationEndpointFactory.createGetApplyingHolidayApplicationEndPoint(approverId), HolidayApplicationResponse[].class);
        return holidayApplicationResponseConverter.convert(responseEntity.getBody());
    }

    public void postHolidayApplication(HolidayApplicationForm holidayApplicationForm, Integer userId) {
        PostHolidayApplicationRequest postHolidayApplicationRequest = postHolidayApplicationRequestConverter.convert(holidayApplicationForm, userId);
        HttpEntity httpEntity = httpEntity(postHolidayApplicationRequest);
        restTemplate.postForEntity(holidayApplicationEndpointFactory.createPostHolidayApplyEndPoint(), httpEntity, Void.class);
    }

    private HttpEntity httpEntity(Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return new HttpEntity(body, httpHeaders);
    }
}
