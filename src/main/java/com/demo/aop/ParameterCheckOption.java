package com.demo.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;


import com.demo.core.Check;
import com.demo.core.Valid;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



@Component
public class ParameterCheckOption {

    public void checkValid(ProceedingJoinPoint joinPoint) throws Exception{
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        String str = "";
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = getMethodByClassAndName(target.getClass(), methodName);
            Annotation[][] annotations = method.getParameterAnnotations();
            args = joinPoint.getArgs();
            if (annotations != null) {
                for (int i = 0; i < annotations.length; i++) {
                    Annotation[] anno = annotations[i];
                    for (int j = 0; j < anno.length; j++) {
                        if (annotations[i][j].annotationType().equals(
                                Valid.class)) {
                            str = checkParam(args[i]);
                            if (StringUtils.hasText(str)) {
                                throw new Exception();
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {

            throw new Exception();
        }
    }


    private String checkParam(Object args) throws Exception {
        String retStr = "";
        Field[] field = args.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {
            Check check = field[j].getAnnotation(Check.class);
            if (check != null) {
                String str = validateFiled(check, field[j], args);
                if (StringUtils.hasText(str)) {
                    retStr = str;
                    return retStr;
                }
            }
        }
        return retStr;
    }


    public String validateFiled(Check check, Field field, Object args)
            throws Exception {
        field.setAccessible(true);
        int length = 0;
        if (field.get(args) != null) {
            length = (String.valueOf(field.get(args))).length();
        }
        if (check.notNull()) {
            if (field.get(args) == null
                    || "".equals(String.valueOf(field.get(args)))) {
                return field.getName() + "不能为空";
            }
        }
        if (check.maxLen() > 0 && (length > check.maxLen())) {
            return field.getName() + "长度不能大于" + check.maxLen();
        }

        if (check.minLen() > 0 && (length < check.minLen())) {
            return field.getName() + "长度不能小于" + check.minLen();
        }

        if (check.numeric() && field.get(args) != null) {
            try {
                new BigDecimal(String.valueOf(field.get(args)));
            } catch (Exception e) {
                return field.getName() + "必须为数值型";
            }
        }
        if (check.minNum() != -999999) {
            try {
                long fieldValue = Long
                        .parseLong(String.valueOf(field.get(args)));
                if (fieldValue < check.minNum()) {
                    return field.getName() + "必须不小于" + check.minNum();
                }
            } catch (Exception e) {
                return field.getName() + "必须为数值型，且不小于" + check.minNum();
            }
        }
        return "";
    }



    public Method getMethodByClassAndName(Class c, String methodName)
            throws Exception {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

}
