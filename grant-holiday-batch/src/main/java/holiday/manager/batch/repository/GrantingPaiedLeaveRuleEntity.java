package holiday.manager.batch.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "granting_paid_leave_rule", schema = "\"user\"")
public class GrantingPaiedLeaveRuleEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
	private List<GrantingPaiedLeaveRuleDetailEntity> rules = new ArrayList<>();

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updateedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GrantingPaiedLeaveRuleDetailEntity> getRules() {
		return rules;
	}

	public void setRules(List<GrantingPaiedLeaveRuleDetailEntity> rules) {
		this.rules = rules;
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
