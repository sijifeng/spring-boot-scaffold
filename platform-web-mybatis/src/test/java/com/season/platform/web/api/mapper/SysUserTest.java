package com.season.platform.web.api.mapper;

import com.season.platform.web.api.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jiyc on 2017/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserTest {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Test
	public void test(){
		SysUser user = sysUserMapper.selectByName("jiyc");
		System.out.println(user);
	}
}
