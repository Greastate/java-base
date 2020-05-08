package cn.lgw.learn.enums;

public enum UserStatusEnum {
    NORMAL(0, "正常"),
    CANT_BORROW(1, "无法借阅"),
    WRITTEN_OFF(2, "账户已注销");

    private int code;
    private String status;

    UserStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public static UserStatusEnum fromCode(int code) {
        for (UserStatusEnum status : UserStatusEnum.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}
