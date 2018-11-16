package com.ucomponent.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:方法注释名
 * Descp:
**/
@Target(ElementType.METHOD)         //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented                         //生成文档
public @interface ActionName {
  String value() default "";
}
