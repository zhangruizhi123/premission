package com.cjeg.web.admin.model;

import java.util.List;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月24日 下午4:23:33
 *  创建角色表
 */
public class Role {
	//id
	private String id;
	//角色名称
	private String name;
	//角色具有的权限
	private List<Permission> permission;
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
	public List<Permission> getPermission() {
		return permission;
	}
	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", permission="
				+ permission + "]";
	}
}
