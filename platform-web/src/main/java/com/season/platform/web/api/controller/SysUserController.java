package com.season.platform.web.api.controller;

import com.season.platform.web.common.entity.JsonResponseEntity;
import com.season.platform.web.common.util.JsonKeyReader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by jiyc on 2017/2/26.
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {




    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonResponseEntity save(@RequestBody Map<String, Object> body){
        JsonResponseEntity jsonResponseEntity = new JsonResponseEntity();
        JsonKeyReader reader = new JsonKeyReader(body);

        String username = reader.readString("username", false);
        try {


        } catch (Exception e) {
        }
        return jsonResponseEntity;
    }


    @RequestMapping(value = "/save1", method = RequestMethod.POST)
    public JsonResponseEntity save1(@RequestBody Map<String, Object> body){
        JsonResponseEntity jsonResponseEntity = new JsonResponseEntity();
        JsonKeyReader reader = new JsonKeyReader(body);

        String username = reader.readString("username", false);
        try {


        } catch (Exception e) {
        }
        return jsonResponseEntity;
    }
}
