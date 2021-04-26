package holiday.manager.repository.repository;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"user\"", schema = "\"user\"")
public class UserEntity {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "status", length = 100, nullable = false)
	private String status;

	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "hire_date", nullable = false)
	private Date hireDate;

	@Column(name = "deleted")
	private Boolean deleted;

	@Column(name = "register_datetime")
	private Date createdAt;

	@Column(name = "update_datetime")
	private Date updateedAt;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(
			schema = "\"user\"",
			name = "user_rule",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "rule_id")
	)
	private GrantingPaiedLeaveRuleEntity rule;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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

	public GrantingPaiedLeaveRuleEntity getRule() {
		return rule;
	}

	public void setRule(GrantingPaiedLeaveRuleEntity rule) {
		this.rule = rule;
	}
}
