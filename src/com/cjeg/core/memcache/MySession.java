package com.cjeg.core.memcache;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * 创建一个自定义的分布式session对象，
 * @author admin
 *
 */
public class MySession
{
	//创建一个sessionName
	public static final String SESSIONNAME="MYSESSION";
	//获取sessionID
	private String sessionId;
	public String getSessionId()
	{
		return sessionId;
	}
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
	
	/**
	 * 添加数据
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key,Object value)
	{
		//存储session数据
		Map<String,Object>session=new HashMap<String,Object>();
		session.put(key, value);
		return MyMemcached.add(sessionId, session);
		//然后把数据直接存到memcached中
	}
	
	/**
	 * 获取数据
	 * @param key
	 * @return
	 */
	public Object get(String key)
	{
		Object obj=null;
		try
		{
			Map<String,Object>session =(Map<String, Object>) MyMemcached.get(sessionId);
			obj=session.get(key);
		}
		catch(Exception e)
		{
			obj=null;
		}
		return obj;
	}
	
	/**
	 * 获取唯一标示，可以作为sessionId使用
	 * @return
	 */
	public static String UUID()
	{
		return UUID.randomUUID().toString();
	}
	
}
