package com.season.platform.web.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.season.platform.web.api.mapper.SysUserMapper;
import com.season.platform.web.api.model.SysUser;
import com.season.platform.web.api.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by jiyc on 2017/3/2.
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser selectByName(String userName) {
		return sysUserMapper.selectByName(userName);
	}


}
