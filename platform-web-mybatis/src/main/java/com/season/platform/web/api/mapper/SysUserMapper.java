package com.season.platform.web.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.season.platform.web.api.model.SysUser;


/**
 * Created by jiyc on 2017/2/28.
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	SysUser selectByName(String userName);
}
