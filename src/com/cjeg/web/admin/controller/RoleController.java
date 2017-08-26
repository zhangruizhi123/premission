package com.cjeg.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjeg.Util.StringUtils;
import com.cjeg.web.admin.model.Permission;
import com.cjeg.web.admin.model.Resource;
import com.cjeg.web.admin.model.Role;
import com.cjeg.web.admin.model.User;
import com.cjeg.web.consts.Consts;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月24日 下午5:54:59
 *
 */

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object>list(HttpServletRequest request,int page,int rows){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Role> roleList=(List<Role>) servletContext.getAttribute(Consts.ROLE_LIST);
		if(roleList==null||roleList.size()<=0){
			result.put("total", 0);
			result.put("rows", new ArrayList<Role>());
		}
		else{
			int total=roleList.size();
			result.put("total", total);
			
			int fromIndex=(page-1)*rows;
			int toIndex=fromIndex+rows-1;
			if(fromIndex>total){
				result.put("rows", new ArrayList<Role>());
			}else if(toIndex>total){
				List<Role>list=roleList.subList(fromIndex, total);
				result.put("rows", list);
			}else{
				List<Role>list=roleList.subList(fromIndex, toIndex);
				result.put("rows", list);
			}
			
		}
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request,String name,String ids){
		List<String>permissionId=new ArrayList<String>();
		String str[]=ids.split(",");
		for(String s:str){
			permissionId.add(s);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Role> roleList=(List<Role>) servletContext.getAttribute(Consts.ROLE_LIST);
		List<Permission> permissionList=(List<Permission>) servletContext.getAttribute(Consts.PERMISSION_LIST);
		
		//没有添加上得权限
		List<String>returnId=new ArrayList<String>();
		List<Permission> pList=new ArrayList<Permission>();
		Role role=new Role();
		role.setId(StringUtils.getUUID32());
		role.setName(name);
		for(String pId:permissionId){
			boolean isFind=false;
			for(Permission permission:permissionList){
				if(permission.getId().equals(pId)){
					pList.add(permission);
					isFind=true;
					break;
				}
			}
			if(!isFind){
				returnId.add(pId);
			}
		}
		role.setPermission(pList);
		//当全部添加上时
		if(pList.size()==permissionId.size()){
			roleList.add(role);
			result.put("flag", 1);
			result.put("msg", "添加角色成功");
			return result;
		}else{
			result.put("flag", -1);
			result.put("msg", "添加角色失败");
			result.put("obj", returnId);
		}
		
		
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request,String id,String name,String ids){
		List<String>permissionId=new ArrayList<String>();
		String str[]=ids.split(",");
		for(String s:str){
			permissionId.add(s);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Role> roleList=(List<Role>) servletContext.getAttribute(Consts.ROLE_LIST);
		List<Permission> permissionList=(List<Permission>) servletContext.getAttribute(Consts.PERMISSION_LIST);
		Role role=null;
		for(Role r:roleList){
			if(r.getId().equals(id)){
				role=r;
			}
		}
		if(role==null){
			result.put("flag", -1);
			result.put("msg", "添加角色失败");
			return result;
		}
		//没有添加上得权限
		List<String>returnId=new ArrayList<String>();
		List<Permission> pList=new ArrayList<Permission>();
		role.setName(name);
		for(String pId:permissionId){
			boolean isFind=false;
			for(Permission permission:permissionList){
				if(permission.getId().equals(pId)){
					pList.add(permission);
					isFind=true;
					break;
				}
			}
			if(!isFind){
				returnId.add(pId);
			}
		}
		role.setPermission(pList);
		
		//当全部添加上时
		if(pList.size()==permissionId.size()){
			result.put("flag", 1);
			result.put("msg", "角色修改成功");
			return result;
		}else{
			result.put("flag", -1);
			result.put("msg", "角色修改失败");
			result.put("obj", returnId);
		}
		
		
		return result;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request,String id){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Role> roleList=(List<Role>) servletContext.getAttribute(Consts.ROLE_LIST);
		for(Role role:roleList){
			if(role.getId().equals(id)){
				roleList.remove(role);
				result.put("flag",1);
				result.put("msg","删除成功");
				return result;
			}
		}
		
		result.put("flag",-1);
		result.put("msg","删除失败");
		return result;
	}
}
