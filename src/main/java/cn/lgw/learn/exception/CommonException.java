package cn.lgw.learn.exception;

public class CommonException extends Exception{
    private int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public static CommonException create(int code, String message) {
        return new CommonException(code, message);
    }

    public int getCode() {
        return code;
    }
}