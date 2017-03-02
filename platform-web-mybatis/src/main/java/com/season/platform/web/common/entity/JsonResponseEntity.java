package com.season.platform.web.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by jiyc on 2017/2/26.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponseEntity<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public JsonResponseEntity() {
        this.code = 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

