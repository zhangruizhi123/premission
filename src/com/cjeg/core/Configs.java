package com.cjeg.core;

import java.util.ResourceBundle;


/**
 * 获取配置文件中的数据
 * @author 张瑞志
 *
 * 创建时间:2017年3月5日 下午7:55:35
 *
 */
public class Configs {
	private String files;
	
	private static  ResourceBundle  resourceBundle=null;
	/**
	 * 构造函数
	 */
	public Configs(){
	}
	
	/**
	 * 获取值
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		if (resourceBundle== null){
			resourceBundle=ResourceBundle.getBundle(files);
		}
		return resourceBundle.getString(key);
	}
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		if (resourceBundle== null){
			resourceBundle=ResourceBundle.getBundle(files);
		}
		this.files = files;
	}
	
}
