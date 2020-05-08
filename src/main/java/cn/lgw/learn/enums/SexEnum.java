package cn.lgw.learn.enums;

public enum SexEnum {
    MALE(0, "男"), FEMALE(1, "女");

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    SexEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SexEnum fromCode(int code) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.code == code) {
                return sex;
            }
        }
        return null;
    }
}
