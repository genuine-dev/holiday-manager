package holiday.manager.batch.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "granting_paid_leave_rule_detail", schema = "\"user\"")
public class GrantingPaiedLeaveRuleDetailEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "elapsed_year", nullable = false)
	private int elapsedYear;

	@Column(name = "elapsed_month", nullable = false)
	private int elapsedMonth;

	@Column(name = "elapsed_days", nullable = false)
	private Integer elapsedDays;

	@Column(name = "grant_days", nullable = false)
	private int grantDays;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "rule_id")
	private GrantingPaiedLeaveRuleEntity rule;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getElapsedYear() {
		return elapsedYear;
	}

	public void setElapsedYear(int elapsedYear) {
		this.elapsedYear = elapsedYear;
	}

	public int getElapsedMonth() {
		return elapsedMonth;
	}

	public void setElapsedMonth(int elapsedMonth) {
		this.elapsedMonth = elapsedMonth;
	}

	public Integer getElapsedDays() {
		return elapsedDays;
	}

	public void setElapsedDays(Integer elapsedDays) {
		this.elapsedDays = elapsedDays;
	}

	public int getGrantDays() {
		return grantDays;
	}

	public void setGrantDays(int grantDays) {
		this.grantDays = grantDays;
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

	public void setUpdatedAt(Date updateedAt) {
		this.updatedAt = updateedAt;
	}

	public GrantingPaiedLeaveRuleEntity getRule() {
		return rule;
	}

	public void setRule(GrantingPaiedLeaveRuleEntity rule) {
		this.rule = rule;
	}

}
