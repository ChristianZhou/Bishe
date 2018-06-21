package edu.njxz.common.annotation;

import java.lang.annotation.*;


/**
 * @author 周桂星
 * @time 2018/1/15 15:05
 * @description Dao 注解
**/
@Target({ElementType.TYPE})//指定可标注类型范围
@Retention(RetentionPolicy.RUNTIME)//指定保留时常
@Documented//其它类型的annotation应该被作为被标注的程序成员的公共API
public @interface Dao {
    String value()default "";
}
