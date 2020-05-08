package cn.lgw.learn.enums;

public enum RoleEnum {
    VISITOR(0, "游客"),
    USER(1, "普通用户"),
    ADMIN(2, "管理员");

    private int code;
    private String role;

    RoleEnum(int code, String role) {
        this.code = code;
        this.role = role;
    }

    public int getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    public static RoleEnum fromCode(int code) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.code == code) {
                return role;
            }
        }
        return null;
    }
}
