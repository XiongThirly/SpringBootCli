package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParameterCheckAspect {
    @Autowired
    private ParameterCheckOption parameterCheckOption;


    /**
     * 定义切点
     */

    @Pointcut("within(com.demo.controller..*)")
    public void check() {
    }

    /**
     * 切面方法，使用统一异常处理
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "check()", argNames = "Valid")
    public Object checkIsValid(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null;
        // 参数校验，未抛出异常表示验证OK
        parameterCheckOption.checkValid(joinPoint);
        object = ((ProceedingJoinPoint) joinPoint).proceed();
        return object;
    }

}
