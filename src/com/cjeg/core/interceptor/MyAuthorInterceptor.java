package com.cjeg.core.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cjeg.web.admin.model.Permission;
import com.cjeg.web.admin.model.Resource;
import com.cjeg.web.admin.model.Role;
import com.cjeg.web.admin.model.User;
import com.cjeg.web.consts.Consts;

/**
 * 控制后台登录权限问题
 * @author 张瑞志
 *
 * 创建时间:2017年2月23日 下午9:52:43
 *
 */
public class MyAuthorInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url=request.getRequestURI();
		url=url.replaceAll(request.getContextPath(), "");
		//除去登录的链接
		if(url.equals("/login")||url.equals("/log/login")||url.equals("/log/logout")){
			return true;
		}else{
			//当session中存在数据时直接登录，否者就从定向到登录页面
			Object obj=request.getSession().getAttribute("sys_u");
			if(obj==null){
				//重定向到登录页面
				response.sendRedirect(request.getContextPath() + "/login"); 
				return true;
			}
			else{
				//当用户登录成功后，获取用户具有的权限
				User user=(User)obj;
				//当不能访问时，转入无法访问网页
				
				if(!isAccesss(user,url)){
					response.sendRedirect(request.getContextPath() + "/noper"); 
					return true;
				}
			}
		}
		//response.sendRedirect(request.getContextPath() + "/index"); 
		return true;
	}
	
	//判断该用户是否有访问该资源的权限
	private boolean isAccesss(User user,String url){
		//获取角色
		List<Role>roleList=user.getRole();
		if(roleList!=null&&roleList.size()>0){
			for(Role role:roleList){
				List<Permission> perList=role.getPermission();
				if(perList!=null&&perList.size()>0){
					for(Permission per:perList){
						List<Resource> resourceList=per.getResource();
						if(resourceList!=null&&resourceList.size()>0){
							for(Resource res:resourceList){
								if(res.getUrl().equals(url)){
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
}
