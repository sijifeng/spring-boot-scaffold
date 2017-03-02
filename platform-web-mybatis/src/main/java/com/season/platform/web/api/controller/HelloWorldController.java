package com.season.platform.web.api.controller;

import com.season.platform.web.exception.CommonException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by jiyc on 2017/2/23.
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloWorldController {

	@RequestMapping("/helloweb")
	public String hello(ModelMap modelMap) {
		//向模板中添加属性
		modelMap.put("hello", "helloweb");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}

	@RequestMapping("/json")
	public String json() throws CommonException {
		throw new CommonException("发生错误2");
	}
}
