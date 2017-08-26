package com.cjeg.web.admin.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月24日 下午4:18:36
 *  创建用户表
 */
public class User {
	//id
	private String id;
	//名字
	private String name;
	//密码
	private String password;
	//创建日期
	private Date createDate;
	//被创建用户
	private String createId;
	//用户具有的角色
	private List<Role> role;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", createDate=" + createDate + ", createId=" + createId
				+ ", role=" + role + "]";
	}
	
}
