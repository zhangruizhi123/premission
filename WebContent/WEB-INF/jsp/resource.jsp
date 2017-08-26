<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理</title>
<jsp:include page="_mate.jsp"></jsp:include>
<script type="text/javascript">
function urlOpen(url){
	return "<a href='"+url+"' target='_blank'>查看</a>";
}

function remove(){
	var row = $('#tt').datagrid('getSelected');
	if(!row){
		alertMsg("请选择要删除的行");
		return false;
	}
	var id=row.id;
	$.ajax({
		url:'${ctx}/admin/resource/delete?id='+id,
		type:'get',
		success:function(result){
			if(result.flag==1){
				$('#tt').datagrid('reload');  
				alertMsg("删除成功");
			}else{
				alertMsg("删除失败");
			}
		}
	});
}


var url="";
function add(){
	$('#winAdd').window('open');
	url='${ctx}/admin/resource/add';
	var row={url:'',description:''};
	$('#fmtt').form('load',row);
}

function update(){
	url='${ctx}/admin/resource/update';
	var row = $('#tt').datagrid('getSelected');
	if(!row){
		alertMsg("请选择要修改的行");
		return false;
	}
	$('#winAdd').window('open');
	$('#fmtt').form('load',row);
}
//提交表单
function submits(){
	$('#fmtt').form('submit',{
			url:url,
			onSubmit:function(){
				return $('#fmtt').form('validate');
			},
			dataType:'json',
			success:function(result){
				result=JSON.parse(result);
				if(result.flag){
					$('#tt').datagrid('reload');  
				}
				alertMsg(result.msg);
				$('#winAdd').window('close');
			}
	});
	
}
</script>
</head>
<body>
	<div class="table">
		<table id="tt" class="easyui-datagrid" style="width:100%;"
			url="${ctx}/admin/resource/list"
			title="资源管理" iconCls="icon-save"
			singleSelect="true"
			toolbar="#tb"
			rownumbers="true" pagination="true">
			<thead>
				<tr>
					<th field="id" hidden="true" width="300" align="center">id</th>
					<th field="url" width="200" align="center">资源的链接</th>
					<th field="description" width="200" align="center">资源描述</th>
				</tr>
			</thead>
		</table>
		<div id="tb">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="remove()">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
		</div>
		
	</div>
	
	
	<!-- 添加数据窗口 -->

	
	<div id="winAdd" closed="true" class="easyui-dialog" style="padding:20px;width:400px;height:300px;"
			title="资源添加" iconCls="icon-ok"
			buttons="#dlg-buttons" >
		<form id="fmtt" method="post" action="">
			<input  id="id" name="id" type="text" style="width:180px;display:none;" class="easyui-validatebox">
			<div class="padding5">
				<label>资源的url连接: </label><input id="url" name="url" type="text" style="width:180px;" required="true"  class="easyui-validatebox">
			</div>
			<div class="padding5">
				<div>资源描述</div>
				<textarea id="description" name="description" style="width:300px;height:100px;max-height:100px;" class="easyui-validatebox" required="true"></textarea>
			</div>
		
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="submits()">提交</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#winAdd').dialog('close')">取消</a>
	</div>

</body>
</html>