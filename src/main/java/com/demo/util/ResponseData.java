package com.demo.util;

import com.demo.config.ExceptionEnum;
import org.springframework.stereotype.Component;


public class ResponseData<T> {

    private Integer code;
    private T data;
    private String Msg;

    public ResponseData() {

    }

    public ResponseData(Integer code,T data,String Msg) {
            this.code = code;
            this.data = data;
            this.Msg = Msg;
    }

    public ResponseData(Integer code,T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseData(Integer code,String Msg) {
        this.code = code;
        this.Msg = Msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String errorMsg) {
        this.Msg = errorMsg;
    }

    public static ResponseData ok(){
        ResponseData result = new ResponseData();
        result.setCode(200);
        result.setMsg("success");
        result.setData(null);
        return result;
    }

    public static ResponseData ok(Object data){
        ResponseData result = new ResponseData();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static ResponseData error(Integer code,String msg){
        ResponseData result = new ResponseData();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static ResponseData error(ExceptionEnum exceptionEnum){
        ResponseData result = new ResponseData();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
}
