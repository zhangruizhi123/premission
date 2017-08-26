package com.cjeg.web.admin.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjeg.Util.StringUtils;
import com.cjeg.web.admin.model.User;
import com.cjeg.web.consts.Consts;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月26日 上午10:24:23
 *
 */
@Controller
@RequestMapping("log")
public class LoginController {
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,String name,String password){
		HttpSession session=request.getSession();
		ServletContext servletContext=request.getServletContext();
		List<User> userList=(List<User>) servletContext.getAttribute(Consts.USER_LIST);
		String pass=StringUtils.getMD5(password);
		for(User u:userList){
			//当用户存在时
			if(u.getName().equals(name)){
				if(u.getPassword().equals(pass)){
					//将用户添加到session中便于跟踪是否登录成功
					session.setAttribute("sys_u", u);
					return "index";
				}
				return "login";
			}
		}
		return "login";
	}

	//退出界面
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		Object obj=request.getSession().getAttribute("sys_u");
		if(obj!=null){
			obj=null;
		}
		return "login";
	}
}
