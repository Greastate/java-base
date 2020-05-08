package cn.lgw.learn.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContext {
    public static ThreadLocal<HttpServletResponse> response = new ThreadLocal<>();

    public static ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();
}