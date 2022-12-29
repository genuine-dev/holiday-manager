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

	public static Map<String, String> asMap(){
		Map<String, String> map = new LinkedHashMap<>();

		for(UserStatusEnum userStatusEnum : values())
			map.put(userStatusEnum.name(), userStatusEnum.getLabel());

		return map;
	}
}
