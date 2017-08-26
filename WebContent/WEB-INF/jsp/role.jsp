<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍类型管理</title>
<jsp:include page="_mate.jsp"></jsp:include>
<style type="text/css">
 .mli{
  list-style: none;
  float: left;
  width: 200px;
 }
</style>
<script type="text/javascript">
function opens(){
	url='';
	var row = $('#tt').datagrid('getSelected');
	if(!row){
		alertMsg("请选择要修改的行");
		return false;
	}
	$('#winAdd').dialog('open');
	$('#fmtt').form('load',row);
	loadResource();
	var rowss=row.permission;
	if(rowss){
		for(var i=0;i<rowss.length;i++){
			var id="resource"+rowss[i].id;
			$("#"+id).attr("checked","true"); 
		}
	}
}
function remove(){
	var row = $('#tt').datagrid('getSelected');
	if(!row){
		alertMsg("请选择要删除的行");
		return false;
	}
	var id=row.id;
	$.ajax({
		url:'${ctx}/admin/role/delete?id='+id,
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
	url='${ctx}/admin/role/add';
	var row={name:'',description:''};
	$('#fmtt').form('load',row);
	loadResource();
	
}
//加载资源
function loadResource(){
	var $select=$("#select");
	$select.html("hello");
	$.ajax({
		url:"${ctx}/admin/permission/list",
		data:{page:'1',rows:'100'},
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			$select.empty();
			var rows=data.rows;
			var result="<ul>";
			
			for(var i=0;i<rows.length;i++){
				var row=rows[i];
				result+="<li class='mli'>";
				result+='<input type="checkbox" id="resource'+row.id+'" value="'+row.id+'">';
				result+=row.name;
				result+="</li>";
			}
			
			result+="</ul>";
			$select.html(result);
			}
	});
}
function update(){
	url='${ctx}/admin/role/update';
	var row = $('#tt').datagrid('getSelected');
	if(!row){
		alertMsg("请选择要修改的行");
		return false;
	}
	$('#winAdd').window('open');
	$('#fmtt').form('load',row);
	loadResource();
	var rowss=row.permission;
	if(rowss){
		for(var i=0;i<rowss.length;i++){
			var id="resource"+rowss[i].id;
			$("#"+id).attr("checked","true"); 
		}
	}
}
//提交表单
function submits(){
	$('#fmtt').form('submit',{
			url:url,
			
			onSubmit:function(param){
				var $select=$("#select input[type='checkbox']:checked");
				if($select.length==0){
					alertMsg("请选择权限对应的连接");
					return false;
				}
				var ids=[];
				$select.each(function(){
					var id=$(this).val();
					ids.push(id);
				});
				param.ids=ids;
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
			url="${ctx}/admin/role/list"
			title="用户管理" iconCls="icon-save"
			singleSelect="true"
			toolbar="#tb"
			rownumbers="true" pagination="true">
			<thead>
				<tr>
					<th field="id" hidden="true" width="300" align="center">id</th>
					<th field="name" width="200" align="center">角色名称</th>
				</tr>
			</thead>
		</table>
		<div id="tb">
			<a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="opens()">查看</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="remove()">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
		</div>
		
	</div>
	
	<!-- 添加数据窗口 -->

	
	<div id="winAdd" closed="true" class="easyui-dialog" style="padding:10px;width:600px;height:400px;"
			title="资源添加" iconCls="icon-ok"
			minimizable="true"
            maximizable="true"
			buttons="#dlg-buttons" >
		<form id="fmtt" method="post" action="">
			<input  id="id" name="id" type="text" style="width:180px;display:none;" class="easyui-validatebox">
			<div class="padding5">
				<label>角色名称: </label><input id="name" name="name" type="text" style="width:180px;" required="true"  class="easyui-validatebox">
			</div>
		
		</form>
		<div>具有权限:</div>
		<div id="select">
			<!-- 添加所有的资源 -->
		</div>
	
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="submits()">提交</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#winAdd').dialog('close')">取消</a>
	</div>

</body>
</html>