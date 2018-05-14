package com.season.platform.web.service.impl;

import com.season.platform.web.entity.Function;
import com.season.platform.web.entity.UserRole;
import com.season.platform.web.pojo.TreeNode;
import com.season.platform.web.repostory.FunctionRepository;
import com.season.platform.web.service.FunctionService;
import com.season.platform.web.service.UserRoleService;
import com.season.platform.web.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by yingchun on 2017/9/1.
 */
@Service
public class FunctionServiceImpl extends BaseServiceImpl<Function, String> implements FunctionService {
    @Autowired
    private FunctionRepository functionRepository;

    @Override
    public List<TreeNode> getTreeData() {
        return null;
    }

    @Override
    public Set<String> getFunctionCodeSet(Set<String> roleCodes, String userId) {
        return null;
    }

    @Override
    public Set<String> getAllFunctionCode() {
        return null;
    }

    @Override
    public List<Function> getFunctionList(Set<String> roleCodes, String userId) {
        return null;
    }

    @Override
    public Page<Function> findAll(Specification<Function> spec, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Specification<Function> spec) {
        return 0;
    }

    @Override
    public List<Function> findList(Specification<Function> spec, Sort sort) {
        return null;
    }

    @Override
    public JpaRepository<Function, String> getBaseDao() {
        return functionRepository;
    }
}
