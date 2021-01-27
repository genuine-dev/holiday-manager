package holiday.manager.port.adapter.persistence.repository.holiday.application.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holiday_applicatoin", schema="user")
public class HolidayApplicationEntity {
	@Id
	@Column(name = "id", length = 36)
	private String id;

	@Column(name = "kind", length = 20)
	private String kind;

	@Column(name = "type", length = 20)
	private String type;

	@Column(name = "status", length = 20)
	private String status;

	@Column(name = "date")
	private Date date;

	@Column(name = "applicant_id", length = 36)
	private String aplicantId;

	@Column(name = "approver_id", length = 36)
	private String approverId;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updateedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAplicantId() {
		return aplicantId;
	}

	public void setAplicantId(String aplicantId) {
		this.aplicantId = aplicantId;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateedAt() {
		return updateedAt;
	}

	public void setUpdateedAt(Date updateedAt) {
		this.updateedAt = updateedAt;
	}

}
