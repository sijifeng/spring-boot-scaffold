package com.season.platform.web.service;

import com.season.platform.web.entity.User;
import com.season.platform.web.entity.UserAvatar;
import com.season.platform.web.service.support.IBaseService;

/**
 * Created by yingchun on 2017/8/31.
 */
public interface UserService extends IBaseService<User, String> {
    /**
     * 根据登录名获取用户
     *
     * @param loginName 登录名
     * @return
     */
    User getUserByLoginName(String loginName);

    /**
     * 根据用户Id 获取头像
     *
     * @param userId
     * @return
     */
    UserAvatar getAvatarByUserId(String userId);

    /**
     * 关联头像和用户 user.getAvatarId()
     *
     * @param user
     * @param dirPath 项目目录
     */
    void updateUserAvatar(User user, String dirPath);

    User getUserById(String userId);

    /**
     * 清除redis中的相关权限设置，主要在更新权限时使用
     *
     * @param userId
     */
    void deleteAuthInRedis(String userId);

    String getUserNamesByUserIds(String userIds);
}
