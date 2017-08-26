package com.cjeg.web.commen.message;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回的实体类
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年2月19日 上午10:21:48
 *
 */
public class MessageVO {
	/**
	 * 是否成功
	 */
	private boolean flag=false;
	
	/**
	 * 返回数据的实体
	 */
	private List data=new ArrayList();
	
	/**
	 * 返回的信息
	 */
	private String message;
	
	private Object obj;
	
	/**
	 * 返回的状态码，默认为零。便于前台查询错误的原因
	 */
	private int code=0;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "MessageVO [flag=" + flag + ", data=" + data + ", message="
				+ message + ", obj=" + obj + ", code=" + code + "]";
	}

	
}
