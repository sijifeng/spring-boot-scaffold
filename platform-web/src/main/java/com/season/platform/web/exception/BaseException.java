package com.season.platform.web.exception;

/**
 * Created by jiyc on 2017/2/26.
 */
public abstract class BaseException extends RuntimeException {

    protected int code;
    protected String msg;
    protected String log;

    public BaseException() {

    }

    public BaseException(int code, String msg) {
        this(code, msg, null);
    }

    public BaseException(int code, String msg, String log) {
        super();
        this.code = code;
        this.msg = msg;
        this.log = log;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public String log() {
        return this.log;
    }
}
