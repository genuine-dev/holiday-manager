package jp.co.genuine.hm.api.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserId {
	private Integer value;

	public UserId(String userId) {
		this.value = Integer.valueOf(userId);
	}
}
