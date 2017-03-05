package com.season.platform.web.api.controller;

import com.season.platform.web.common.entity.JsonResponseEntity;

import java.util.ArrayList;

/**
 * Created by jiyc on 2017/3/2.
 */
public class BaseController {

	public static final Integer SUCCESS_CODE = 200;
	public static final String SUCCESS_MSG = "成功。";
	public static final Integer ERROR_CODE = 400;
	public static final String ERROR_MSG = "失败。";
	public static final String PARAM_IS_NULL = "参数为空";

	void getSuccessResponse(JsonResponseEntity responseModel, Object data) {
		if (null == data) {
			data = new ArrayList();
		}

		responseModel.setCode(SUCCESS_CODE);
		responseModel.setData(data);
		responseModel.setMsg(SUCCESS_MSG);

	}

	//错误
	void getErrorResponse(JsonResponseEntity responseModel, Integer code, Object data, String error) {
		if (null == data) {
			data = new ArrayList();
		}

		switch (code.intValue()) {
			case 400:
				responseModel.setCode(ERROR_CODE);
				responseModel.setData(data);
				responseModel.setMsg(ERROR_MSG);
			default:
				responseModel.setCode(code.intValue());
				responseModel.setData(data);
				responseModel.setMsg(error);
		}

	}
}
