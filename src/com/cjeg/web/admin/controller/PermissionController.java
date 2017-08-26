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
@RequestMapping("/admin/permission")
public class PermissionController {
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object>list(HttpServletRequest request,int page,int rows){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Permission> permissionList=(List<Permission>) servletContext.getAttribute(Consts.PERMISSION_LIST);
		if(permissionList==null||permissionList.size()<=0){
			result.put("total", 0);
			result.put("rows", new ArrayList<Permission>());
		}
		else{
			int total=permissionList.size();
			result.put("total", total);
			
			int fromIndex=(page-1)*rows;
			int toIndex=fromIndex+rows-1;
			if(fromIndex>total){
				result.put("rows", new ArrayList<Permission>());
			}else if(toIndex>total){
				List<Permission>list=permissionList.subList(fromIndex, total);
				result.put("rows", list);
			}else{
				List<Permission>list=permissionList.subList(fromIndex, toIndex);
				result.put("rows", list);
			}
			
		}
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request,String name,String description,String ids){
		String strs[]=ids.split(",");
		List<String>resourceId=new ArrayList<String>();
		for(String s:strs){
			resourceId.add(s);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Permission> permissionList=(List<Permission>) servletContext.getAttribute(Consts.PERMISSION_LIST);
		List<Resource> resourceList=(List<Resource>) servletContext.getAttribute(Consts.RESOURCE_LIST);
		//这里应该对资源的id进行校验,但是由于时间限制，暂时先放着
		
		Permission permission=new Permission();
		permission.setId(StringUtils.getUUID32());
		permission.setName(name);
		permission.setDescription(description);
		List<Resource> resource =new ArrayList<Resource>();
		List<String>returnId=new ArrayList<String>();
		for(String id:resourceId){
			boolean isFind=false;
			for(Resource resource1:resourceList){
				if(resource1.getId().equals(id)){
					resource.add(resource1);
					isFind=true;
					break;
				}
			}
			if(!isFind){
				returnId.add(id);
			}
			
		}
		if(resource.size()==resourceId.size()){
			permission.setResource(resource);
			result.put("flag", 1);
			result.put("msg", "添加成功");
			
		}else{
			//当添加失败后返回添加失败的id
			permission.setResource(resource);
			result.put("flag", -1);
			result.put("msg", "添加失败");
			result.put("obj", returnId);
		}
		permissionList.add(permission);
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request,String id,String name,String description,String ids){
		String strs[]=ids.split(",");
		List<String>resourceId=new ArrayList<String>();
		for(String s:strs){
			resourceId.add(s);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Permission> permissionList=(List<Permission>) servletContext.getAttribute(Consts.PERMISSION_LIST);
		List<Resource> resourceList=(List<Resource>) servletContext.getAttribute(Consts.RESOURCE_LIST);
		//这里应该对资源的id进行校验,但是由于时间限制，暂时先放着
		Permission permission=null;
		for(Permission p:permissionList){
			if(p.getId().equals(id)){
				permission=p;
				break;
			}
		}
		if(permission==null){
			result.put("flag", -1);
			result.put("msg", "修改失败");
			return result;
		}
		permission.setName(name);
		permission.setDescription(description);
		List<Resource> resource =new ArrayList<Resource>();
		List<String>returnId=new ArrayList<String>();
		for(String idss:resourceId){
			boolean isFind=false;
			for(Resource resource1:resourceList){
				if(resource1.getId().equals(idss)){
					resource.add(resource1);
					isFind=true;
					break;
				}
			}
			if(!isFind){
				returnId.add(idss);
			}
			
		}
		if(resource.size()==resourceId.size()){
			permission.setResource(resource);
			result.put("flag", 1);
			result.put("msg", "修改成功");
		}else{
			//当添加失败后返回添加失败的id
			permission.setResource(resource);
			result.put("flag", -1);
			result.put("msg", "修改失败");
			result.put("obj", returnId);
		}
		return result;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request,String id){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Permission> permissionList=(List<Permission>) servletContext.getAttribute(Consts.PERMISSION_LIST);
		for(Permission p:permissionList){
			if(p.getId().equals(id)){
				permissionList.remove(p);
				result.put("flag", 1);
				result.put("msg", "删除成功");
				return result;
			}
		}
		result.put("flag", -1);
		result.put("msg", "删除失败");
		return result;
	}
}
