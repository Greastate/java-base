package cn.lgw.learn.util;


import com.alibaba.fastjson.JSON;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 参数相关工具
 */
public abstract class LogUtil {

    private LogUtil() {
    }

    public static String buildLogFromArgs(Object[] args) {
        if (args != null && args.length > 0) {
            StringBuilder argsStr = new StringBuilder(" ");
            for (Object arg : args) {
                if (arg instanceof ServletRequest ||
                        arg instanceof ServletResponse ||
                        arg instanceof HttpSession) {
                    continue;
                }
                argsStr.append(JSON.toJSONString(arg)).append(" ");
            }
            argsStr.deleteCharAt(argsStr.length() - 1);
            return argsStr.toString();
        }

        return "";
    }
}
