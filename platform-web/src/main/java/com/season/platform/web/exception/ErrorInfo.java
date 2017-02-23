package com.season.platform.web.exception;

import lombok.Data;

/**
 * Created by jiyc on 2017/2/20.
 */
@Data
public class ErrorInfo<T> {
	public static final Integer OK = 0;
	public static final Integer ERROR = 100;
	private Integer code;
	private String message;
	private String url;
	private T data;
}
