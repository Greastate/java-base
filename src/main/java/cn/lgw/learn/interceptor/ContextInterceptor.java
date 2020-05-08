package cn.lgw.learn.interceptor;

import cn.lgw.learn.util.LoginContext;
import cn.lgw.learn.util.WebContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ContextInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        WebContext.request.set(request);
        WebContext.response.set(response);

        HttpSession session = request.getSession();
        String isLoggedIn = (String) session.getAttribute("isLoggedIn");

        if (isLoggedIn != null) {
            LoginContext.name.set((String) session.getAttribute("name"));
            LoginContext.username.set((String) session.getAttribute("username"));
            LoginContext.userId.set((Long) session.getAttribute("userId"));
        }

        return true;
    }
}
