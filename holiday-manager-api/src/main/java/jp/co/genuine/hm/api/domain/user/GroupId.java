package jp.co.genuine.hm.api.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupId {
	private Integer value;

	public GroupId(String groupId) {
		this.value = Integer.valueOf(groupId);
	}
}
