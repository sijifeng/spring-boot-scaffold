package com.season.platform.web.controller;


import com.season.platform.web.entity.User;
import com.season.platform.web.service.TestService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/test")
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Resource
    private TestService testService;

    /**
     * 用户列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    private String list() {
        return "base/test/user_list";
    }


    @RequestMapping(value = "/ajaxlist", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxList(@RequestBody Map<String, Object> param) {//
        Map<String, Object> maps = new HashMap<>();
        try {
            logger.info("param = " + param);

            int start = MapUtils.getIntValue(param, "start", 1);
            int size = MapUtils.getIntValue(param, "length", 10);
            logger.info("param start :" + start);
            logger.info("param size :" + size);
            int page = start / size;
            Pageable pageable = new PageRequest(page, size);
            Page<User> list = testService.findAll(pageable);// 分页列表
            long count = testService.count();

            maps.put("recordsTotal", count);        // 总记录数
            maps.put("recordsFiltered", count);    // 过滤后的总记录数
            maps.put("draw", param.get("draw"));
            maps.put("data", list.getContent());

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return maps;
    }


    /**
     * 用户编辑
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    private String edit(String id, HttpServletRequest request) {

        request.setAttribute("id", id);
        return "base/test/user_edit";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/get")
    @ResponseBody
    private User getUser(String id) {
        return testService.find(id);
    }

}
