package com.cjeg.web.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjeg.Util.StringUtils;
import com.cjeg.web.admin.model.Permission;
import com.cjeg.web.admin.model.Role;
import com.cjeg.web.admin.model.User;
import com.cjeg.web.consts.Consts;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月24日 下午5:16:30
 * 用户相关的控制器
 *  */

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	/**
	 * 根据分页查询数据
	 */
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,int page,int rows){
		
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<User> userList=(List<User>) servletContext.getAttribute(Consts.USER_LIST);
		if(userList==null||userList.size()<=0){
			result.put("total", 0);
			result.put("rows", userList);
		}
		else{
			int total=userList.size();
			result.put("total", total);
			
			int fromIndex=(page-1)*rows;
			int toIndex=fromIndex+rows-1;
			if(fromIndex>total){
				result.put("rows", new ArrayList<User>());
			}else if(toIndex>total){
				List<User>list=userList.subList(fromIndex, total);
				result.put("rows", list);
			}else{
				List<User>list=userList.subList(fromIndex, toIndex);
				result.put("rows", list);
			}
			
		}
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request,String name,String password,String ids){
		String str[]=ids.split(",");
		List<String>idList=new ArrayList<String>();
		for(String s:str){
			idList.add(s);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<User> userList=(List<User>) servletContext.getAttribute(Consts.USER_LIST);
		List<Role> roleList=(List<Role>) servletContext.getAttribute(Consts.ROLE_LIST);
		User user=new User();
		user.setId(StringUtils.getUUID32());
		user.setName(name);
		user.setPassword(StringUtils.getMD5(password));
		user.setCreateDate(new Date());
		List<Role>userRole=new ArrayList<Role>();
		List<String>returnId=new ArrayList<String>();
		for(String id:idList){
			boolean isFind=false;
			for(Role role:roleList){
				if(role.getId().equals(id)){
					userRole.add(role);
					isFind=true;
					break;
				}
			}
			if(!isFind){
				returnId.add(id);
			}
		}
		user.setRole(userRole);
		userList.add(user);
		if(userRole.size()==str.length){
			result.put("flag", 1);
			result.put("msg", "添加用户成功");
		}else{
			result.put("flag", -1);
			result.put("msg", "添加用户失败");
			//将失败用户id返回
			result.put("obj", returnId);
		}
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request,String id,String name,String password,String ids){
		String str[]=ids.split(",");
		List<String>idList=new ArrayList<String>();
		for(String s:str){
			idList.add(s);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<User> userList=(List<User>) servletContext.getAttribute(Consts.USER_LIST);
		List<Role> roleList=(List<Role>) servletContext.getAttribute(Consts.ROLE_LIST);
		User user=null;
		for(User u:userList){
			if(u.getId().equals(id)){
				user=u;
				break;
			}
		}
		if(user==null){
			result.put("flag", -1);
			result.put("msg", "无法获取到该用户信息");
			return result;
		}
		user.setName(name);
		user.setPassword(StringUtils.getMD5(password));
		user.setCreateDate(new Date());
		List<Role>userRole=new ArrayList<Role>();
		List<String>returnId=new ArrayList<String>();
		for(String idL:idList){
			boolean isFind=false;
			for(Role role:roleList){
				if(role.getId().equals(idL)){
					userRole.add(role);
					isFind=true;
					break;
				}
			}
			if(!isFind){
				returnId.add(idL);
			}
		}
		user.setRole(userRole);
		if(userRole.size()==str.length){
			result.put("flag", 1);
			result.put("msg", "用户修改成功");
		}else{
			result.put("flag", -1);
			result.put("msg", "用户修改失败");
			//将失败用户id返回
			result.put("obj", returnId);
		}
		return result;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request,String id){
		Map<String,Object> result = new HashMap<String,Object>();
		ServletContext servletContext=request.getServletContext();
		List<User> userList=(List<User>) servletContext.getAttribute(Consts.USER_LIST);
		boolean isDelete=false;
		for(User user:userList){
			if(user.getId().equals(id)){
				isDelete=true;
				//删除数据
				synchronized (UserController.class) {
					userList.remove(user);
				}
			}
		}
		
		if(isDelete){
			result.put("flag", 1);
			result.put("msg", "删除成功");
		}else{
			result.put("flag", -1);
			result.put("msg", "删除失败");
		}
		return result;
	}
}
