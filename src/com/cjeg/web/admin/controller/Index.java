package com.cjeg.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月23日 下午3:27:28
 * 登录主页的请求
 */
@Controller
@RequestMapping("")
public class Index {

	//将根目录重定向到index页面
	@RequestMapping("/")
	public String init(){
		return "redirect:/login";
	}
	
	@RequestMapping("/admin/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/admin/user")
	public String user(){
		return "user";
	}
	
	@RequestMapping("/admin/permission")
	public String permission(){
		return "permission";
	}
	
	@RequestMapping("/admin/resource")
	public String resource(){
		return "resource";
	}
	
	@RequestMapping("/admin/role")
	public String role(){
		return "role";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/noper")
	public String noper(){
		return "noper";
	}
	
	@RequestMapping("logout")
	public String logout(){
		return "logout";
	}
}
