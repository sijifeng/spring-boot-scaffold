package com.season.platform.web.exception;

/**
 * Created by jiyc on 2017/2/26.
 */
public class RequestMissingKeyException extends BaseException {

    public RequestMissingKeyException() {
        super();
        this.code = 3000;
    }

    public RequestMissingKeyException(String keyName) {
        super();
        this.code = 3000;
        this.msg = String.format("[%s]字段不能为空", keyName);
    }
}
