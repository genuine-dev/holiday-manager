package jp.co.genuine.hm.api.domain.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
	ACTIVE("在籍"),
	LEAVE("休職中"),
	RETIRED("退職");
	
	private String label;
	
	public String getLabel() {
		return label;
	}	
}
