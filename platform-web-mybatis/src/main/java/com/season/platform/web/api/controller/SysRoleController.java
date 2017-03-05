package com.season.platform.web.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.season.platform.web.api.model.SysMenu;
import com.season.platform.web.api.model.SysRole;
import com.season.platform.web.api.service.SysMenuService;
import com.season.platform.web.api.service.SysRoleService;
import com.season.platform.web.common.entity.JsonResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jiyc on 2017/3/4.
 */
public class SysRoleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping("/init")
    public String index(Model model) {
        return "system/role/list";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length, String system, String roleHandler) {
        //return sysRoleService.pageList(start, length, system, roleHandler);
        return null;
    }

    @RequestMapping("/role")
    @ResponseBody
    public JsonResponseEntity role(String roleId) {
        JsonResponseEntity jsonResponseEntity = new JsonResponseEntity();
        Map<String, Object> attr = new HashMap<String, Object>();
        try {

            SysRole sysRole = null;
            List<Integer> menuIds = null;

            if (StringUtils.isNotEmpty(roleId)) {
                sysRole = sysRoleService.selectById(roleId);
                attr.put("sysRole", sysRole);
                //menuIds = sysRoleService.findMenuByRoleId(roleId);
            }

            // 菜单信息
            JSONArray json = new JSONArray();
            List<SysMenu> menuList = sysMenuService.selectList(null);
            if (menuList != null && menuList.size() > 0) {
                for (SysMenu menu : menuList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    /*map.put("id", menu.getMenuId());
                    map.put("name", menu.getMenuName());
                    map.put("pId", menu.getParentId());

                    // 选中角色拥有的菜单权限
                    if (menuIds != null && menuIds.contains(menu.getMenuId())) {
                        map.put("checked", true);
                    } else {
                        map.put("checked", false);
                    }*/

                    json.add(map);
                }
            }
            attr.put("menuList", json.toString());
            getSuccessResponse(jsonResponseEntity, attr);
        } catch (Throwable e) {
            getErrorResponse(jsonResponseEntity, ERROR_CODE, null, e.getMessage());
        }
        return jsonResponseEntity;
    }

}
