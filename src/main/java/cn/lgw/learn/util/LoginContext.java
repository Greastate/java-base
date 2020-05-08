package cn.lgw.learn.util;

public class LoginContext {
    public static ThreadLocal<String> username = new ThreadLocal<>();

    public static ThreadLocal<Long> userId = new ThreadLocal<>();

    public static ThreadLocal<String> name = new ThreadLocal<>();
}
