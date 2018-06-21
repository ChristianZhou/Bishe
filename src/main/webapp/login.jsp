<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<meta charset="utf-8">

	<style type="text/css">
	body{
		background-size: cover;
	}
	.main{
		position: absolute;
		top: 50%;
		left: 50%;
		margin-top: -250px;
		margin-left: -150px;
		height: 500px;
		width: 300px;
		background-color: white;
	}
	td{
		margin-top: 15px;
	}
	input{
		border-radius: 3px;
		border: 1px solid ;
		margin-top: 15px;
		padding:7px;
	}
	input:focus{
        border-color: #66afe9;
	}
		
	.btn{
		position: relative;
		width: 200px;
		cursor: pointer;
	}
	

	</style>
	
</head>




<link rel="stylesheet" type="text/css"
	  href="js/util/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	  href="js/util/easyui/themes/icon.css">
<script type="text/javascript" src="js/util/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/util/easyui/jquery.easyui.min.js"></script>
<body onload="">
<div style="text-align: center;">
<div class='main'>
<img src="img/xz.jpg">

<form action="teacher/find/one" onsubmit="return check()" method="post">
<table>
<tr><td>用户名：<input placeholder="请输入用户名" type="text" class="notnull" name="tname" ></td></tr>
<tr><td>密　码：<input placeholder="请输入密码" type="text" class="notnull" name="tpwd" ></td></tr>
<tr>
	<td align="center">${tinfo}</td>
</tr>
<tr><td  align="center" valign="top"><input class="btn" type="submit"  value="登陆" ></td></tr>
	<tr><td  align="center" valign="top"><input class="btn" type="button" onclick="register()"  value="注册" ></td></tr>
</table>




</form>





	<div id="dlg" class="easyui-dialog" title="注册" style="width:400px;height:200px;padding:10px;"
		 data-options="
				iconCls:'icon-add',
				closed:'true'
			">
		<form id="ff" method="post">
			<table>
				<tr>
					<td>姓名:</td>
					<td><input class="easyui-textbox" type="text" name="tname" data-options="required:true"></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-textbox" type="text" name="tpwd" data-options="required:true"></td>
				</tr>
			</table>
		</form>
		<div style="text-align:center;padding:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()">Submit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear');">Clear</a>
		</div>
	</div>
</div>
</div>


<script type="text/javascript">

function	check(){
	var notnull=document.getElementsByClassName("notnull");
	for (var i = notnull.length - 1; i >= 0; i--) {
		if(!notnull[i].value){
			alert("输入未完成！");
			return false;
		}
	}
	return true;
}

function register() {
	$('#dlg').dialog('open');
}

function submit() {
	$('#dlg').dialog('close');
	$('#ff').form('submit', {
		url: 'http://localhost:8080/zgx/teacher/insert',
		success: function (data) {
			if (data == '1'){
				alert('注册成功');
				location.reload();
			}else{
				alert('注册失败');
			}
		}
	});
}

</script>

</body>
</html>
