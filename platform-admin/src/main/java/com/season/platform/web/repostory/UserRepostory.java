package com.season.platform.web.repostory;

import com.season.platform.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yingchun on 2017/8/29.
 */
public interface UserRepostory extends JpaRepository<User, String> {
    User findByName(String name);
}
