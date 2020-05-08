package cn.lgw.learn.enums;

public enum SchoolEnum {
    MATH_SCHOOL(0, "数学学院");

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    SchoolEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SchoolEnum fromCode(int code) {
        for (SchoolEnum school : SchoolEnum.values()) {
            if (school.code == code) {
                return school;
            }
        }
        return null;
    }
}
