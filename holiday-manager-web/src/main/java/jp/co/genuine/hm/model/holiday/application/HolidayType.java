package jp.co.genuine.hm.model.holiday.application;

import java.util.LinkedHashMap;
import java.util.Map;

public enum HolidayType {
    MORNING_OFF("午前休"),
    AFTERNOON_OFF("午後休"),
    FULL_OFF("全休");

    private String label;

    private HolidayType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static Map<String, String> asMap() {
        Map<String, String> map = new LinkedHashMap<>();

        for(HolidayType holidayType : values())
            map.put(holidayType.name(), holidayType.label());

        return map;
    }
}
