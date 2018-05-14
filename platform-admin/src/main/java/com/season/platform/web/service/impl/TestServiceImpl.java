package com.season.platform.web.service.impl;

import com.season.platform.web.entity.User;
import com.season.platform.web.entity.UserAvatar;
import com.season.platform.web.repostory.UserRepostory;
import com.season.platform.web.service.TestService;
import com.season.platform.web.service.UserService;
import com.season.platform.web.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestServiceImpl extends BaseServiceImpl<User, String> implements TestService {
    @Autowired
    private UserRepostory userRepostory;

    @Override
    public JpaRepository<User, String> getBaseDao() {
        return userRepostory;
    }

    @Override
    public Page<User> findAll(Specification<User> spec, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Specification<User> spec) {
        return 0;
    }

    @Override
    public List<User> findList(Specification<User> spec, Sort sort) {
        return null;
    }
}
