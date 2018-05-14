package com.season.platform.web.service;

import com.season.platform.web.entity.UserRole;
import com.season.platform.web.pojo.Result;
import com.season.platform.web.service.support.IBaseService;

/**
 * Created by yingchun on 2017/8/31.
 */
public interface UserRoleService extends IBaseService<UserRole, String> {
    //Result delete(String ids);

    void deleteAuthInRedis(String userId);

    void setRoleForRegisterUser(String userId);
}
