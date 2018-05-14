package com.season.platform.web.service;

import com.season.platform.web.entity.Function;
import com.season.platform.web.entity.User;
import com.season.platform.web.pojo.TreeNode;
import com.season.platform.web.service.support.IBaseService;

import java.util.List;
import java.util.Set;

/**
 * Created by yingchun on 2017/9/1.
 */
public interface FunctionService extends IBaseService<Function, String> {
    List<TreeNode> getTreeData();

    Set<String> getFunctionCodeSet(Set<String> roleCodes, String userId);

    Set<String> getAllFunctionCode();

    List<Function> getFunctionList(Set<String> roleCodes,String userId);
}
