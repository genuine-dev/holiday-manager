package jp.co.genuine.hm.model.holiday;

import java.util.LinkedHashMap;
import java.util.Map;

public enum KindOfHoliday {
    PAYED_LEAVE("有給休暇"),
    SPECIAL_HOLYDAY("特別休暇"),
    ABSENCE("欠勤");

    public String label;

    private KindOfHoliday(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static Map<String, String> asMap() {
        Map<String, String> map = new LinkedHashMap<>();

        for(KindOfHoliday kindOfHoliday : values())
            map.put(kindOfHoliday.name(), kindOfHoliday.label());

        return map;
    }
}
