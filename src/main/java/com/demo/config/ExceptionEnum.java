package com.demo.config;
/**
 * @Descrption: TODO
 * @author: THIRLY
 * @date: 2018/10/19 16:37
 */
public enum ExceptionEnum {
    /**
     *
     */
    UNKNOW_ERROR(-1,"未知错误"),
    ;

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
