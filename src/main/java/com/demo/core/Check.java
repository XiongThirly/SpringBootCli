package com.demo.core;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
    /**
     * 是否非空
     */
    public boolean notNull() default false;

    /**
     * 是否为数值
     */
    public boolean numeric() default false;

    /**
     * 最大长度
     */
    public int maxLen() default 99999;

    /**
     * 最小长度
     */
    public int minLen() default -1;

    /**
     * 最小数值
     */
    public long minNum() default -999999;

}

