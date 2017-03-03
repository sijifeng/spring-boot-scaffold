package com.season.platform.web.api.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.season.platform.web.api.model.SysUser;
import com.season.platform.web.api.service.SysUserService;
import com.season.platform.web.common.entity.JsonResponseEntity;
import com.season.platform.web.common.util.JsonKeyReader;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
			int start = MapUtils.getIntValue(param, "start", 1);
			int size = MapUtils.getIntValue(param, "size", 10);
			logger.info("param start :" + start);
			logger.info("param size :" + size);
			logger.info("param username :" + MapUtils.getString(param, "username"));


			Page<SysUser> page = new Page<SysUser>(start, size);

			Page<SysUser> list = sysUserService.selectPage(page);
			//List<SysUser> userList = sysUserService.selectList(null);
			getSuccessResponse(jsonResponseEntity, list);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			getErrorResponse(jsonResponseEntity, ERROR_CODE, null, e.getMessage());
		}
		return jsonResponseEntity;
	}

	@RequestMapping("/edit")
	public String edit(@RequestBody Map<String, Object> body, Model model) {
		try {
			String id = MapUtils.getString(body, "id");
			if (StringUtils.isNotBlank(id)) {
				SysUser user = sysUserService.selectById(id);
				model.addAttribute("user", user);
			} else {

			}

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}

		return "user/edit";
	}


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonResponseEntity save(SysUser sysUser) {
		JsonResponseEntity jsonResponseEntity = new JsonResponseEntity();

		try {

		} catch (Exception e) {
		}
		return jsonResponseEntity;
	}


}
