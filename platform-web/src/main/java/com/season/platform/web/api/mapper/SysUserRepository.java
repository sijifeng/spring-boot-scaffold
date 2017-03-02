package com.season.platform.web.api.mapper;

import com.season.platform.web.api.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by jiyc on 2017/2/28.
 */
public interface SysUserRepository extends JpaRepository<SysUser, String> {

	SysUser findByUsername(String username);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update sys_user set password = :password where username = :username")
	int modifyPwd(@Param("username") String username, @Param("password") String password);
}
