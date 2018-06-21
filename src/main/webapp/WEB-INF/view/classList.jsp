<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="js/util/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="js/util/easyui/themes/icon.css">
<script type="text/javascript" src="js/util/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/util/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
			#menu{
				width: 250px;
				height: 550px;
				float: left;
			}
			
			#yourright{
				float: left;
			}
			

	#title{
		text-align: center;
	}
</style>
	<title>蓝牙签到系统</title>

	<h1 id="title">蓝牙签到系统</h1>
</head>
<body>

<div id="dlg2" class="easyui-dialog" title="上传excel表格" style="width:400px;height:200px;padding:10px;"
		data-options="
			iconCls:'icon-add',
			closed:'true'
		">
		<form id="import" action="upfiles.html" method="post" enctype="multipart/form-data">
			<input class="easyui-filebox" name="file1" data-options="prompt:'选择名单'" style="width:100%">
    	</form>
    	<br>
    <div style="text-align:center;padding:5px">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importsubmit()">上传</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#import').form('clear');">清除</a>
    </div>
</div>


<div id="dlg3" class="easyui-dialog" title="删除名单" style="width:400px;height:200px;padding:10px;"
	 data-options="
			closed:'true'
		">
	<form id="delete" method="post">
		<c:forEach var="course" items="${courses}" varStatus="status">
			${ status['index']+1}、
			${course['cname']}${course['cnum']}班
			<input type="checkbox" name="selectedCourse" value="${course['cid']}${course['cnum']}" />
			<br><br>
		</c:forEach>
	</form>
	<br>
	<div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="deletesubmit()">删除</a>
	</div>
</div>







<div id="menu">
	<span>教师姓名：${tinfo}</span>
	<br>
	<br>
	<c:forEach var="course" items="${courses}" varStatus="status">
		${ status.index+1}、
		<a href="javascript:void(0)" onclick="changePage(${course.cid},${course.cnum},'${course.cname}')">
				${course.cname}${course.cnum}班
		</a>
		<br><br>
	</c:forEach>


		<a href="javascript:void(0)" class="easyui-linkbutton"
		   data-options="iconCls:'icon-add',plain:true" onclick="openimport()">导入考勤表</a>

		<a href="javascript:void(0)" class="easyui-linkbutton"
		   data-options="iconCls:'icon-cancel',plain:true" onclick="opendelete()">删除考勤表</a>
</div>


<iframe id="yourright" src="" width="920" height="500"></iframe>
</body>


<div id="record">
	<center><span>缺勤次数统计：</span></center>
	<br>
	<center><span id="list"></span></center>

</div>


<script type="text/javascript">
function changePage(cid,cnum,cname) {
	var theframe='student/tiaozhuan?cid='+cid+'&cnum='+cnum+'&cname='+cname+cnum;
	document.getElementById("yourright").src=""+theframe+"";
	$.ajax({
		url: 'student/total',
		dataType: "text",
		type: "post",
		data: {
			"cid":cid,
			"cnum":cnum
		},
		success: function (send) {
			console.log(send)
			document.getElementById("list").innerText=send;
		}
	});
}
function importsubmit(){
	$('#dlg2').dialog('close');
	$('#import').form('submit', {
	    url: 'student/importEXCEL',
	    success:function(data){
	    	window.location="./teacher/find/one";
	    }
	});
}


function deletesubmit(){
	$('#dlg3').dialog('close');
	$('#delete').form('submit', {
		url: 'student/deleteEXCEL',
		success:function(data){
			window.location="./teacher/find/one";
		}
	});
}
function openimport(){
	$('#dlg2').dialog('open');
}


function opendelete(){
	$('#dlg3').dialog('open');
}
</script> 
</html>