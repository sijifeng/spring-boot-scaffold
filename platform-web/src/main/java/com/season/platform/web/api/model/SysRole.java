package com.season.platform.web.api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by jiyc on 2017/2/28.
 */
@Entity
@Data
public class SysRole {
	@Id
	@GeneratedValue
	private Integer roleId;//角色id 自增 主键
	private Integer systemId;//系统编号 从系统表中获取
	private String roleCode;//角色编码
	private String name;//角色名称
	private String info;//描述
	private String status;//状态，Y正常，N禁用
	private Integer type;
	private Date createTime;
	private String createBy;
	private Date updateTime;
	private String updateBy;
	private String remarks;
}
