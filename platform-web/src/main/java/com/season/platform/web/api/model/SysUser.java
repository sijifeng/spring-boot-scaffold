package com.season.platform.web.api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by jiyc on 2017/2/28.
 */
@Entity
@Data
public class SysUser {
	@Id
	private String kingnetId;
	private Integer userId;  // userId 为帐号来源地的用户id, 如果是OA登录。 userId 与OA一致
	private String userName; //登录用户名  简写 英文
	private String password;
	private String realName;// 姓名
	private String email;//邮箱
	private Integer department; //部门id
	private Integer type;// 登录类型  1 为OA
	private String lastIp;//最后登录IP
	private String status;//状态，Y正常，N禁用
	private Date lastUpdate;//最后更新时间
	private String data;//扩展字段
}
