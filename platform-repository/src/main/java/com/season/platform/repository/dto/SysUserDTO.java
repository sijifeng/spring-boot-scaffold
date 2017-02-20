package com.season.platform.repository.dto;

import java.util.Date;

/**
 * Created by jiyc on 2017/2/16.
 */
public class SysUserDTO {
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

	public String getKingnetId() {
		return kingnetId;
	}

	public void setKingnetId(String kingnetId) {
		this.kingnetId = kingnetId;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp == null ? null : lastIp.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data == null ? null : data.trim();
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
