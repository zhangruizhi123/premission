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
@RequestMapping("/admin/resource")
public class ResourceController {
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object>list(HttpServletRequest request,int page,int rows){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Resource> resourceList=(List<Resource>) servletContext.getAttribute(Consts.RESOURCE_LIST);
		if(resourceList==null||resourceList.size()<=0){
			result.put("total", 0);
			result.put("rows", new ArrayList<Resource>());
		}
		else{
			int total=resourceList.size();
			result.put("total", total);
			
			int fromIndex=(page-1)*rows;
			int toIndex=fromIndex+rows;
			if(fromIndex>total){
				result.put("rows", new ArrayList<Resource>());
			}else if(toIndex>total){
				List<Resource>list=resourceList.subList(fromIndex, total);
				result.put("rows", list);
			}else{
				List<Resource>list=resourceList.subList(fromIndex, toIndex);
				result.put("rows", list);
			}
			
		}
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request,String url,String description){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		try{
			List<Resource> permissionList=(List<Resource>) servletContext.getAttribute(Consts.RESOURCE_LIST);
			Resource resource=new Resource();
			resource.setId(StringUtils.getUUID32());
			resource.setUrl(url);
			resource.setDescription(description);
			permissionList.add(resource);
			result.put("flag", "1");
			result.put("msg", "添加资源成功");
		}catch(Exception e){
			result.put("flag", "-1");
			result.put("msg", "添加资源失败");
		}
		
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request,String id,String url,String description){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		try{
			List<Resource> resourceList=(List<Resource>) servletContext.getAttribute(Consts.RESOURCE_LIST);
			for(Resource resource:resourceList){
				if(resource.getId().equals(id)){
					resource.setDescription(description);
					resource.setUrl(url);
					result.put("flag", "1");
					result.put("msg", "更新资源成功");
					return result;
				}
			}
			
			result.put("flag", "-1");
			result.put("msg", "更新资源失败");
		}catch(Exception e){
			result.put("flag", "-1");
			result.put("msg", "更新资源失败");
		}
		
		return result;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request,String id){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<Resource> resourceList=(List<Resource>) servletContext.getAttribute(Consts.RESOURCE_LIST);
		for(Resource resource:resourceList){
			if(resource.getId().equals(id)){
				resourceList.remove(resource);
				result.put("flag", "1");
				result.put("msg", "删除资源成功");
				return result;
			}
		}
		result.put("flag", "-1");
		result.put("msg", "删除资源失败");
		return result;
	}
}
