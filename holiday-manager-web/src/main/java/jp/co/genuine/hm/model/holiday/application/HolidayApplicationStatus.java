package jp.co.genuine.hm.model.holiday.application;

import java.util.LinkedHashMap;
import java.util.Map;

public enum HolidayApplicationStatus {
    APPLYING("申請中"),
    APPROVED("承認"),
    REJECTED("却下"),
    CANCELED("取り消し"),
    PROCESSED("処理済み"),
    PROCESS_FAILED("処理失敗");

    private String label;

    private HolidayApplicationStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static Map<String, String> asMap() {
        Map<String, String> map = new LinkedHashMap<>();

        for(HolidayApplicationStatus holidayApplicationStatus : values())
            map.put(holidayApplicationStatus.name(), holidayApplicationStatus.label());

        return map;
    }
}
