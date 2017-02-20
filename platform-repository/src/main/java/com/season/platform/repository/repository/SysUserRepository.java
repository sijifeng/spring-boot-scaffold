package com.season.platform.repository.repository;

import com.season.platform.repository.dto.SysUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jiyc on 2017/2/17.
 */
public interface SysUserRepository extends JpaRepository<SysUserDTO, Long> {
}
