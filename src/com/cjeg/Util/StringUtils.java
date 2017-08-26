package com.cjeg.Util;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 字符串的工具类
 * @author 张瑞志
 *
 * 创建时间:2017年2月19日 上午10:44:02
 *
 */
public class StringUtils {
	
	/**
	 * 获取32位的UUID
	 * @return
	 */
	public static String getUUID32(){
		return getUUID36().replaceAll("-", "");
	}
	/**
	 * 获取36位的UUID
	 * @return
	 */
	public static String getUUID36(){
		return UUID.randomUUID().toString();
	}
	
	public static boolean isEmpty(String str){
		if(str==null||str.length()<1){
			return true;
		}
		return false;
	}
	
	public static String getMD5(String str){
		return DigestUtils.md5Hex(str);
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("hello").length());
	}
}
