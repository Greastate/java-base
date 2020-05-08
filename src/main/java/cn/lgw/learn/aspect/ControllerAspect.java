package cn.lgw.learn.aspect;


import cn.lgw.learn.enums.ExceptionEnum;
import cn.lgw.learn.exception.CommonException;
import cn.lgw.learn.to.resp.RestResponse;
import cn.lgw.learn.util.LogUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * Controller切面逻辑
 * <p>
 * 1. 请求参数统一校验(待定）；<br>
 * 2. 输入出参数日志打印；<br>
 * 3. 异常响应结果格式化。
 * </p>
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut(value = "execution(public * cn.lgw.learn.controller..*.*(..))")
    public void doSomething() {
    }

    @Around("doSomething()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {

        String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();

        // 输入日志
        log.info("[Controller({})] {}, input=[{}]",
                controllerName, methodName, LogUtil.buildLogFromArgs((joinPoint.getArgs())));

        Object result;
        try {
            result = joinPoint.proceed();

            log.info("[Controller({})] {}, output=[{}]",
                    controllerName, methodName, JSON.toJSONString(result));

        } catch (CommonException e) {
            log.info("[Controller({})] {}, throw exception code={} message={}",
                    controllerName, methodName, e.getCode(), e.getMessage());
            return RestResponse.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.info("[Controller({})] {}, throw unmatchable exception message={}",
                    controllerName, methodName, e.getMessage());
            return RestResponse.serverError(ExceptionEnum.SERVER_ERROR.getMessage());
        }

        return result;
    }
}
