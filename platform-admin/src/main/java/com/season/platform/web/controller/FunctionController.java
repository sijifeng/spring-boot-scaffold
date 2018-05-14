package com.season.platform.web.controller;

import com.season.platform.web.entity.Function;
import com.season.platform.web.pojo.Result;
import com.season.platform.web.pojo.TreeNode;
import com.season.platform.web.service.FunctionService;
import com.season.platform.web.util.StrUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by yingchun on 2017/9/1.
 */
@Controller
@RequestMapping(value = "/function")
public class FunctionController {
    @Resource
    private FunctionService functionService;

    /**
     * 用户列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tree")
    private String list() {

        return "base/auth/function_tree";
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public List<Function> getAll() {
        System.out.println(functionService);
        Sort sort = new Sort(Sort.Direction.ASC,"levelCode");
        List<Function> list = functionService.findAll(sort);
        System.out.println(list);
        return list;

    }


    /**
     * getTreeData 构造bootstrap-treeview格式数据
     *
     * @return
     */
    @RequestMapping(value = "/treeData", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> getTreeData() {

        return functionService.getTreeData();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Function get(@PathVariable("id") String id) {

        Function function = functionService.find(id);
        if (!StrUtil.isEmpty(function.getParentId())) {
            function.setParentName(functionService.find(function.getParentId()).getName());
        } else {
            function.setParentName("系统菜单");
        }
        return function;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Function function) {
        function.setUpdateDateTime(new Date());
        functionService.save(function);
        return new Result(true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") String id) {

        try {
            Function function = functionService.find(id);
            functionService.delete(function);
            return new Result(true);
        } catch (Exception e) {
            return new Result(false, "该菜单/功能已经被其他数据引用，不可删除");
        }
    }


    //TODO 功能集合将从session中获取
    @RequestMapping(value="/navigation")
    @ResponseBody
    public List<Function> navigation(String pageUrl){
        return  functionService.findAll();
    }
}
