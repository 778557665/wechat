package com.wengzhoujun.wechat.enums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum SubjectEnum {

    MONDAY(2, "周一了，阿堡也很想和主人一起上班呢"),
    TUESDAY(3, "坚持就是胜利，蛋堡今天也是元气满满呢~"),
    WEDNESDAY(4, "苦逼的日子已经过去一半拉！咬王会在家里乖乖的"),
    THURSDAY(5, "还有一天咯~蛋堡也很激动呢"),
    FRIDAY(6, "嘻嘻！今天主人会早点回来呢"),
    SATURDAY(7, "又到了老追最期待的周末了，因为可以和主人在一起更久~"),
    SUNDAY(1, "明天要上班了，溜溜堡提醒主人要好好休息哦");

    private Integer code;
    private String value;

    SubjectEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private static Set<Integer> mobileSet = new HashSet<>();
    private static Map<Integer, SubjectEnum> map = new HashMap<>();

    static {
        for (SubjectEnum subjectEnum : SubjectEnum.values()) {
            map.put(subjectEnum.getCode(), subjectEnum);
        }
        mobileSet.add(MONDAY.getCode());
        mobileSet.add(TUESDAY.getCode());
        mobileSet.add(WEDNESDAY.getCode());
        mobileSet.add(THURSDAY.getCode());
        mobileSet.add(FRIDAY.getCode());
        mobileSet.add(SATURDAY.getCode());
        mobileSet.add(SUNDAY.getCode());
    }

    public static SubjectEnum getEnumByCode(Integer code) {
        return map.get(code);
    }
}
