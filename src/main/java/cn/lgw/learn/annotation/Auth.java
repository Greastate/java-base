package cn.lgw.learn.annotation;

import cn.lgw.learn.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 鉴权注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    /**
     * 哪些角色CODE可以访问
     */
    RoleEnum[] roles();
}