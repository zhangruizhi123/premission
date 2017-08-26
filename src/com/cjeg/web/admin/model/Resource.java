package com.cjeg.web.admin.model;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月24日 下午4:30:58
 *  资源表
 */
public class Resource {
	//id
	private String id;
	//url连接
	private String url;
	//描述
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", url=" + url + ", description="
				+ description + "]";
	}
}
