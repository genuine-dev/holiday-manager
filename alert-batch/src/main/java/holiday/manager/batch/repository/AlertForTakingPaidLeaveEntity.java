package holiday.manager.batch.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alert_for_taking_paid_leave", schema = "\"user\"")
public class AlertForTakingPaidLeaveEntity {
	@Id
	@Column(name = "user_id")
	private int userId;

	@Column(name = "days", nullable = false)
	private int days;

	@Column(name = "dead_line", nullable = false)
	private Date deadLine;

	@Column(name = "register_datetime")
	private Date createdAt = new Date();

	@Column(name = "update_datetime")
	private Date updateedAt = new Date();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
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
