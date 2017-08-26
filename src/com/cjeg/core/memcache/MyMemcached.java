package com.cjeg.core.memcache;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MyMemcached
{
	private static MemCachedClient client=new MemCachedClient();
	static 
	{
		SockIOPool sockPool=SockIOPool.getInstance();
		String servers[]=new String[]{"127.0.0.1:11211"};
		Integer weights[]={3};
		// 设置服务器信息
		sockPool.setServers(servers);
		sockPool.setWeights(weights);
		// 设置初始连接数、最小连接数、最大连接数、最大处理时间
		sockPool.setInitConn(10);
		sockPool.setMinConn(10);
		sockPool.setMaxConn(100);
		sockPool.setMaxIdle(1000*60*60);
		//设置主线程睡眠时间，每3秒苏醒一次，维持连接池大小  
		//maintSleep 千万不要设置成30，访问量一大就出问题，单位是毫秒，推荐30000毫秒。
		sockPool.setMaintSleep(30000);
		//关闭套接字缓存  
		sockPool.setNagle(false);
		sockPool.setAliveCheck(true);
		sockPool.setFailover(true);
        //连接建立后的超时时间  
		sockPool.setSocketTO(3000);  
        //连接建立时的超时时间  
		sockPool.setSocketConnectTO(0); 
        // 初始化并启动连接池
		sockPool.initialize();
	}
	
	/**
	 * 添加数据,将在半个小时后失效
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static boolean add(String key,Object value)
	{
		//添加数据时候，如果存在，则直接覆盖，如果不存在则添加
		Object obj=client.get(key);
		if(obj==null)
		{
			return client.add(key, value,new Date(1000*60*30));
		}
		else
		{
			return client.replace(key, value,new Date(1000*60*30));
		}
	}
	
	/**
	 * 直接获取数据
	 * @param key
	 * @return
	 */
	public static Object get(String key)
	{
		return client.get(key);
	}
}
