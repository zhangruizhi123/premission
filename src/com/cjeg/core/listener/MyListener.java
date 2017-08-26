package com.cjeg.core.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cjeg.Util.StringUtils;
import com.cjeg.web.admin.model.Permission;
import com.cjeg.web.admin.model.Resource;
import com.cjeg.web.admin.model.Role;
import com.cjeg.web.admin.model.User;
import com.cjeg.web.consts.Consts;

public class MyListener implements ServletContextListener
{
	//定义全局的用户
	private static List<User>userList=new ArrayList<User>();
	//定义全局的角色
	private static List<Role>roleList=new ArrayList<Role>();
	//定义全局的权限
	private static List<Permission>permissionList=new ArrayList<Permission>();
	//定义全局的资源
	private static List<Resource>resourceList=new ArrayList<Resource>();
	@Override
	public void contextDestroyed(ServletContextEvent arg0){
		
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent){
		initResourceContext();
		initPermissionContext();
		initRoleContext();
		initUserContext();
		//将数据放在全局的上下文中便于在程序的任何地方都能访问到
		ServletContext servletContext=contextEvent.getServletContext();
		servletContext.setAttribute(Consts.USER_LIST, userList);
		servletContext.setAttribute(Consts.ROLE_LIST, roleList);
		servletContext.setAttribute(Consts.RESOURCE_LIST, resourceList);
		servletContext.setAttribute(Consts.PERMISSION_LIST, permissionList);
		System.out.println("开始");
	}
	
	//对用户列表中的数据进行初始化
	private void initUserContext(){
		User user =new User();
		//管理员的id为0，如果是由数据库可以使用UUID作为用户的id保证不重复
		user.setId("0");
		user.setName("root");
		user.setPassword(StringUtils.getMD5("root"));
		user.setCreateDate(new Date());
		//管理员没有创建者
		user.setCreateId("-1");
		user.setRole(roleList);
		userList.add(user);
		
		User user1 =new User();
		//管理员的id为0，如果是由数据库可以使用UUID作为用户的id保证不重复
		user1.setId("1");
		user1.setName("zhangsan");
		user1.setPassword(StringUtils.getMD5("zhangsan"));
		user1.setCreateDate(new Date());
		//管理员没有创建者
		user1.setCreateId("0");
		userList.add(user1);
		
		
		User user2 =new User();
		//管理员的id为0，如果是由数据库可以使用UUID作为用户的id保证不重复
		user2.setId("2");
		user2.setName("lisi");
		user2.setPassword(StringUtils.getMD5("lisi"));
		user2.setCreateDate(new Date());
		//管理员没有创建者
		user2.setCreateId("0");
		userList.add(user2);
		
		User user3 =new User();
		//管理员的id为0，如果是由数据库可以使用UUID作为用户的id保证不重复
		user3.setId("2");
		user3.setName("wangwu");
		user3.setPassword(StringUtils.getMD5("wangwu"));
		user3.setCreateDate(new Date());
		//管理员没有创建者
		user3.setCreateId("0");
		userList.add(user3);
	}
	
	private void initRoleContext(){
		Role role=new Role();
		role.setId("101");
		role.setName("超级管理员");
		//超级管理员添加所有权限
		role.setPermission(permissionList);
		roleList.add(role);

		
		
		Role role1=new Role();
		role1.setId("102");
		role1.setName("一般管理员");
		roleList.add(role1);
		
		Role role2=new Role();
		role2.setId("103");
		role2.setName("超级用户");
		roleList.add(role2);
		
		Role role3=new Role();
		role3.setId("104");
		role3.setName("普通用户");
		roleList.add(role3);
		
		//role.set
	}
	
	//权限添加
	private void initPermissionContext(){
		Permission permission=new Permission();
		permission.setId("201");
		permission.setName("resource");
		permission.setDescription("资源模块");
		permission.setResource(resourceList.subList(0, 4));
		permissionList.add(permission);
		
		Permission permission1=new Permission();
		permission1.setId("202");
		permission1.setName("role");
		permission1.setDescription("角色模块");
		permission1.setResource(resourceList.subList(4, 8));
		permissionList.add(permission1);
		
		Permission permission2=new Permission();
		permission2.setId("203");
		permission2.setName("user");
		permission2.setResource(resourceList.subList(12, 16));
		permission2.setDescription("用户模块");
		permissionList.add(permission2);
		
		Permission permission3=new Permission();
		permission3.setId("204");
		permission3.setName("permission");
		permission3.setResource(resourceList.subList(8, 12));
		permission3.setDescription("权限模块");
		permissionList.add(permission3);
		
		Permission permission4=new Permission();
		permission4.setId("205");
		permission4.setName("other");
		permission4.setResource(resourceList.subList(16, 20));
		permission4.setDescription("页面模块");
		permissionList.add(permission4);
	}
	
	//资源初始化
	private void initResourceContext(){
		Resource resource=new Resource();
		resource.setId("301");
		resource.setUrl("/admin/resource/list");
		resource.setDescription("分页列出资源");
		resourceList.add(resource);
		
		Resource resource1=new Resource();
		resource1.setId("302");
		resource1.setUrl("/admin/resource/add");
		resource1.setDescription("资源添加");
		resourceList.add(resource1);
		
		Resource resource2=new Resource();
		resource2.setId("303");
		resource2.setUrl("/admin/resource/update");
		resource2.setDescription("资源更新");
		resourceList.add(resource2);
		
		Resource resource3=new Resource();
		resource3.setId("304");
		resource3.setUrl("/admin/resource/delete");
		resource3.setDescription("资源删除");
		resourceList.add(resource3);
		
		//角色
		Resource role1=new Resource();
		role1.setId("311");
		role1.setUrl("/admin/role/list");
		role1.setDescription("分页列出角色");
		resourceList.add(role1);
		
		Resource role2=new Resource();
		role2.setId("312");
		role2.setUrl("/admin/role/add");
		role2.setDescription("角色添加");
		resourceList.add(role2);
		
		Resource role3=new Resource();
		role3.setId("313");
		role3.setUrl("/admin/role/update");
		role3.setDescription("角色更新");
		resourceList.add(role3);
		
		Resource role4=new Resource();
		role4.setId("314");
		role4.setUrl("/admin/role/delete");
		role4.setDescription("角色删除");
		resourceList.add(role4);
		
		
		//权限
		Resource perminss1=new Resource();
		perminss1.setId("321");
		perminss1.setUrl("/admin/permission/list");
		perminss1.setDescription("分页列出权限");
		resourceList.add(perminss1);
		
		Resource perminss2=new Resource();
		perminss2.setId("322");
		perminss2.setUrl("/admin/permission/add");
		perminss2.setDescription("权限添加");
		resourceList.add(perminss2);
		
		Resource perminss3=new Resource();
		perminss3.setId("323");
		perminss3.setUrl("/admin/permission/update");
		perminss3.setDescription("权限更新");
		resourceList.add(perminss3);
		
		Resource perminss4=new Resource();
		perminss4.setId("324");
		perminss4.setUrl("/admin/permission/delete");
		perminss4.setDescription("权限删除");
		resourceList.add(perminss4);
		
		
		//权限
		Resource user1=new Resource();
		user1.setId("331");
		user1.setUrl("/admin/user/list");
		user1.setDescription("分页列出用户");
		resourceList.add(user1);
		
		Resource user2=new Resource();
		user2.setId("332");
		user2.setUrl("/admin/user/add");
		user2.setDescription("用户添加");
		resourceList.add(user2);
		
		Resource user3=new Resource();
		user3.setId("333");
		user3.setUrl("/admin/user/update");
		user3.setDescription("用户更新");
		resourceList.add(user3);
				
		Resource user4=new Resource();
		user4.setId("334");
		user4.setUrl("/admin/user/delete");
		user4.setDescription("用户删除");
		resourceList.add(user4);
		
		
		//页面
		Resource page1=new Resource();
		page1.setId("341");
		page1.setUrl("/admin/user");
		page1.setDescription("用户信息页面");
		resourceList.add(page1);
		
		Resource page2=new Resource();
		page2.setId("342");
		page2.setUrl("/admin/role");
		page2.setDescription("角色管理页面");
		resourceList.add(page2);
		
		Resource page3=new Resource();
		page3.setId("343");
		page3.setUrl("/admin/permission");
		page3.setDescription("权限管理页面");
		resourceList.add(page3);
				
		Resource page4=new Resource();
		page4.setId("344");
		page4.setUrl("/admin/resource");
		page4.setDescription("资源管理页面");
		resourceList.add(page4);
	}
}
