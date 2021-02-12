package holiday.manager.port.adapter.persistence.repository.holiday.application.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holiday_applicatoin", schema="\"user\"")
public class HolidayApplicationEntity {
	@Id
	@Column(name = "id", length = 36, nullable = false)
	private String id;

	@Column(name = "kind", length = 20, nullable = false)
	private String kind;

	@Column(name = "type", length = 20, nullable = false)
	private String type;

	@Column(name = "status", length = 20, nullable = false)
	private String status;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "applicant_id", nullable = false)
	private Integer aplicantId;

	@Column(name = "approver_id")
	private Integer approverId;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

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

	public Integer getAplicantId() {
		return aplicantId;
	}

	public void setAplicantId(Integer aplicantId) {
		this.aplicantId = aplicantId;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
