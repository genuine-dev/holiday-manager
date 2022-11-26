package jp.co.genuine.hm.model.user;

import java.util.LinkedHashMap;
import java.util.Map;

public enum UserStatusEnum {
	ACTIVE("在籍"), LEAVE("休職中"), RETIRED("退職");

	private String label;

	public String getLabel() {
		return label;
	}

	private UserStatusEnum(String label) {
			this.label = label;
		}

	public static Map<String, String> statusMap(){
		Map<String, String> statusMap = new LinkedHashMap<>();

		for(UserStatusEnum userStatusEnum : values())
			statusMap.put(userStatusEnum.name(), userStatusEnum.getLabel());

		return statusMap;
	}
}
