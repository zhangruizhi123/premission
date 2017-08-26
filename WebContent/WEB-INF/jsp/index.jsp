<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="_mate.jsp"></jsp:include>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<title>测试题</title>
<style type="text/css">

body{
	padding-top:0px;
	margin-top:0px;
}
.head{
	height:20px;
	padding:10px;
	background-color:#ECF3FF;
}
.head .login{
	float:right;
	color:#00f;
}

.head .login .times{
	font-size:12px;
	display:block;
	float:left;
	width:65px;
	color:#95B8E7;
}
.head .login .days{
	color:#95B8E7;
	display:block;
	float:left;
	font-size:12px;
	width:140px;
}
.head .login a{
	text-decoration:none;
	color:#00f;
}
.head .login a:hover{
	color:#f00;
}

.nav{
	padding:0px;
	margin:0px;
}
.nav li{
	padding:5px;
	list-style:none;
	padding-left:20px;
	cursor:pointer;
}
.nav li:hover{
	background-color:#00f;
	color:#fff;
}
</style>
<script type="text/javascript">


//使插件自适应屏幕
$(document).ready(function(){  
    var height1 = $(window).height()-50;  
    $("#layout").attr("style","width:100%;height:"+height1+"px");  
    $("#layout").layout("resize",{  
        width:"100%",  
        height:height1+"px"  
    });  
});  
  
  
$(window).resize(function(){  
    var height1 = $(window).height()-50;  
    $("#layout").attr("style","width:100%;height:"+height1+"px");  
    $("#layout").layout("resize",{  
        width:"100%",  
        height:height1+"px"  
    });  
});

//自动更新时间
function times(){
	var mydate=new Date();
	var myhours=mydate.getHours(); 
	var myminutes=mydate.getMinutes(); 
	var myseconds=mydate.getSeconds();
	$("#times").html(myhours+":"+myminutes+":"+myseconds);
}

//设置日期
function days(){
	var mydate=new Date();
	var myyear=mydate.getYear()+1900; 
	var mymonth=mydate.getMonth()+1;//注：月数从0~11为一月到十二月 
	var mydat=mydate.getDate(); 
	var myday=mydate.getDay();//注:0-6对应为星期日到星期六
	var xingqi;
	switch(myday) 
	{ 
	case 0:xingqi="星期日";break; 
	case 1:xingqi="星期一";break; 
	case 2:xingqi="星期二";break; 
	case 3:xingqi="星期三";break; 
	case 4:xingqi="星期四";break; 
	case 5:xingqi="星期五";break; 
	case 6:xingqi="星期六";break; 
	default:xingqi="系统错误！" ;
	} 
	var days=myyear+"年"+mymonth+"月"+mydat+"日  "+xingqi;
	$("#days").html(days);
}
function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
}
/*单击导航栏时*/
function selected(){
	$(".nav li").click(function(){
		$(this).css({'background-color':'#00f','color':'#fff'}).siblings().css({'color':'#000','background-color':'#fff'});
		var title=$(this).html();
		var url=$(this).attr("url");
		if(url){
			addTab(title,url);
		}
	});
}
$(function(){
	
	var name="${admin_session.name}";
	$(".name").html("["+name+"]");
	days();
	times();
	window.setInterval(times,1000);
	selected();
});
</script>
</head>
<body>
<!-- 退出界面的 -->
<div class="head">
	<div class="login">
		<span class="days" id="days"></span>
		<span class="times" id="times"></span>
		<!-- 你好,<span class="name">张三   </span> -->
		<a href="${ctx}/log/logout">[退出]</a>
		
	</div>
</div>
<div class="easyui-layout" id="layout" style="width:1000px;;height:500px;">
		<div region="west" split="true" title="导航" style="width:200px;">
			<ul class="nav">
				<li url="${ctx}/admin/user">用户管理</li>
				<li url="${ctx}/admin/role">角色管理</li>
				<li url="${ctx}/admin/permission">权限管理</li>
				<li url="${ctx}/admin/resource">资源管理</li>
			</ul>
		</div>
		<div id="content" region="center" title="操作" style="padding:5px;">
			<div id="tabs" class="easyui-tabs" style="width:100%;height:100%;">
				<div title="Home" url="${ctx}/people">
				 	欢迎登陆系统
				</div>
			</div>
		
		
		</div>
	</div>
</body>
</html>