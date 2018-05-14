package com.season.platform.web.exception;

/**
 * Created by jiyc on 2017/2/26.
 */
public class BadRequestException extends BaseException {

    public BadRequestException() {
        super(2001, "请求格式錯誤", null);
    }
    public BadRequestException(String msg) {
        super(2000, msg, null);
    }
}
