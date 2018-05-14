package com.season.platform.web.api.mapper;

import com.season.platform.web.entity.User;
import com.season.platform.web.repostory.UserRepostory;
import com.season.platform.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by jiyc on 2017/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserTest {
	@Autowired
	private UserRepostory userRepostory;

	@Resource
	private UserService userService;
	@Test
	public void test(){
		//User user = userRepostory.findByName("test");
		User user = userService.getUserByLoginName("test");
		System.out.println(user);
	}
}
