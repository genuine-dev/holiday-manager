package holiday.manager.rest.response.holiday;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HolidayApplicationResponse {

    private String id;

    private String kind;

    private String type;

    private String status;

    private Date date;

    private Integer applicantId;

    private Integer approverId;

    public HolidayApplicationResponse(String id, String kind, String type, String status, Date date, Integer applicantId, Integer approverId) {
        this.id = id;
        this.kind = kind;
        this.type = type;
        this.status = status;
        this.date = date;
        this.applicantId = applicantId;
        this.approverId = approverId;
    }

    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public Integer getApproverId() {
        return approverId;
    }
}
