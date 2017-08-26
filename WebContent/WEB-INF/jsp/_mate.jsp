<!-- 放入引入的库和 css文件-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<link rel="icon" href="${ctx}/img/logo.ico" type="img/x-ico" />
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<!-- 使用easy UI 库 -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/locale/easyui-lang-zh_CN.js"></script>

<style>
	.padding5{
		padding:5px;
	}
	.padding10{
		padding:10px;
	}
</style>
<script>
//带有前下划线
var ctx="<%=request.getContextPath()%>";
//弹出提示都用这个后期便于修改样式
function  alertMsg(msg){
	$.messager.alert('提示',msg);
	/*$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
	    if (r){
			alert('ok');
	    }
	});*/
}


function dateTimeFormat(date){
	var mydate=new Date(date);
	var myyear=mydate.getYear()+1900; 
	var mymonth=mydate.getMonth()+1;//注：月数从0~11为一月到十二月 
	var mydat=mydate.getDate(); 
	var myhours=mydate.getHours(); 
	var myminutes=mydate.getMinutes(); 
	var myseconds=mydate.getSeconds();
	return myyear+"-"+mymonth+"-"+mydat+" "+myhours+":"+myminutes+":"+myseconds;
}
function dateFormat(date){
	var mydate=new Date(date);
	var myyear=mydate.getYear()+1900; 
	var mymonth=mydate.getMonth()+1;//注：月数从0~11为一月到十二月 
	var mydat=mydate.getDate(); 
	return myyear+"-"+mymonth+"-"+mydat;
}

/*对图片进行base64编码*/
function preImg(sourceId,targetId) {  
    if (typeof FileReader === 'undefined') {  
        alert('Your browser does not support FileReader...');  
        return;  
    }  
    var reader = new FileReader();  
  
    reader.onload = function(e) {  
        var img = document.getElementById(targetId);  
        img.src = this.result;  
    }; 
    reader.readAsDataURL(document.getElementById(sourceId).files[0]);  
} 

/*弹出提示然后在2.5秒内消失*/
function toast(msg){
	var can="<span style='z-index:9999;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);-webkit-transform:translate(-50%,-50%);padding:10px;border-radius:5px;color:#fff;background-color:rgba(0,0,0,0.7);'>";
	can+=msg+"</span>";
	var $toast=$(can);
	$('body').append($toast);
	setTimeout(function(){$toast.remove()},2000);
	/*$toast.animate({opacity:'0'},2500,function(){
		$(this).remove();
	});*/
}
$(function(){
	//alertMsg("调用插件成功");
});
</script>