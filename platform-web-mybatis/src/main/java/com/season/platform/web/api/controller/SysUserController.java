package com.season.platform.web.api.controller;

import com.google.common.base.Preconditions;
import com.season.platform.web.api.model.SysUser;
import com.season.platform.web.api.service.SysUserService;
import com.season.platform.web.common.entity.JsonResponseEntity;
import com.season.platform.web.common.util.JsonKeyReader;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * Created by jiyc on 2017/2/26.
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SysUserController.class);
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonResponseEntity list(@RequestBody Map<String, Object> param) {
		JsonResponseEntity jsonResponseEntity = new JsonResponseEntity();
		try {
			//Preconditions.checkNotNull(param.getToken(), "token:" + PARAM_IS_NULL);
			logger.info("param start :" + MapUtils.getIntValue(param, "start", 1));
			logger.info("param size :" + MapUtils.getIntValue(param, "size", 2));
			logger.info("param username :" + MapUtils.getString(param, "username"));

			List<SysUser> userList = sysUserService.selectList(null);
			getSuccessResponse(jsonResponseEntity, userList);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			getErrorResponse(jsonResponseEntity, ERROR_CODE, null, e.getMessage());
		}
		return jsonResponseEntity;
	}


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonResponseEntity save(@RequestBody Map<String, Object> body) {
		JsonResponseEntity jsonResponseEntity = new JsonResponseEntity();
		JsonKeyReader reader = new JsonKeyReader(body);

		String username = reader.readString("username", false);
		try {


		} catch (Exception e) {
		}
		return jsonResponseEntity;
	}


}
