package com.cjeg.core.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class Myinterceptor implements HandlerInterceptor
{
	private List<String>except=new ArrayList<String>();
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception
	{
		System.out.println(request.getRequestURL());
		System.out.println("session:"+request.getSession().getAttribute("name"));
		if(request.getSession().getAttribute("name")==null)
		{
			for(int i=0;i<except.size();i++)
			{
				String url=""+request.getRequestURL();
				if(url.contains(except.get(i)))
				{
					return true;
				}
				
			}
			//当时移动设备时
			if(isMobile(request))
			{
				request.getRequestDispatcher("/WEB-INF/jsp/mobile/login.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			}
			return false;
		}
		else
		{
			return true;
		}
	}
	 @Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{
	}
	public List<String> getExcept()
	{
		return except;
	}
	public void setExcept(List<String> except)
	{
		this.except = except;
	}
	
	private boolean isMobile(HttpServletRequest requst)
	{
		String type=requst.getHeader("User-Agent").toLowerCase();
		if(type.contains("mobile"))
		{
			return true;
		}
		return false;
	}
	
}
