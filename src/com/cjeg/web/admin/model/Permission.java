package com.cjeg.web.admin.model;

import java.util.List;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月24日 下午4:30:28
 *  权限表
 */
public class Permission {
	//id
	private String id;
	//名称
	private String name;
	//权限描述
	private String description;
	//权限能控制哪些连接
	private List<Resource> resource;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<Resource> getResource() {
		return resource;
	}
	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", description="
				+ description + ", resource=" + resource + "]";
	}
	
}
