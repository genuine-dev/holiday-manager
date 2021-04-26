package holiday.manager.repository.repository;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paid_leave_grant_history", schema = "\"user\"")
public class PaidLeaveGrantHistoryEntiry {
	@Id
	@Embedded
	private ID id;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Embeddable
	public static class ID implements Serializable {
		private int userId;

		private int ruleDetailId;

		public ID() {

		}

		public ID(int userId, int ruleDetailId) {
			this.userId = userId;
			this.ruleDetailId = ruleDetailId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int ruleDetailId) {
			this.userId = userId;
		}

		public int getRuleDetailId() {
			return ruleDetailId;
		}

		public void setRuleDetailId(int ruleDetailId) {
			this.ruleDetailId = ruleDetailId;
		}

	}

	public PaidLeaveGrantHistoryEntiry() {

	}

	public PaidLeaveGrantHistoryEntiry(ID id, Date createdAt) {
		this.id = id;
		this.createdAt = createdAt;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
