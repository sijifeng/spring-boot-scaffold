package com.season.platform.web.repostory;

import com.season.platform.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yingchun on 2017/9/3.
 */
public interface TestRepository extends JpaRepository<User, String> {

}
