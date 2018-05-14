package com.season.platform.web.service.impl;

import com.season.platform.web.entity.User;
import com.season.platform.web.entity.UserRole;
import com.season.platform.web.service.UserRoleService;
import com.season.platform.web.service.UserService;
import com.season.platform.web.service.support.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yingchun on 2017/8/31.
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String> implements UserRoleService {
    @Override
    public void deleteAuthInRedis(String userId) {

    }

    @Override
    public void setRoleForRegisterUser(String userId) {

    }

    @Override
    public Page<UserRole> findAll(Specification<UserRole> spec, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Specification<UserRole> spec) {
        return 0;
    }

    @Override
    public List<UserRole> findList(Specification<UserRole> spec, Sort sort) {
        return null;
    }

    @Override
    public JpaRepository<UserRole, String> getBaseDao() {
        return null;
    }
}
