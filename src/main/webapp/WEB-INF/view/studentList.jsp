<!doctype html>
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>

<body>
<div id='main' align="center">

    <span>${cname}</span>

    <table id="dg" class="easyui-datagrid"
           style="width: 900px; height: 400px"
           data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url:'student/findbyclass?cid=${cid}&cnum=${cnum}',
				method: 'get',
				onClickRow: onClickRow
			">
        <thead>
        <tr>
            <th data-options="field:'stunum',width:80">学号</th>
            <th data-options="field:'stuname',width:80,align:'right',editor:'textbox'">姓名</th>
            <c:forEach var="i" begin="1" end="18" step="1">
                <th data-options="field:'class${i}',width:40,editor:{type:'checkbox',options:{on:'1',off:'0'}}">${i}</th>
            </c:forEach>
        </tr>
        </thead>
    </table>


    <div id="tb" style="height: auto">
        <a href="javascript:void(0)" class="easyui-linkbutton"
           data-options="iconCls:'icon-edit',plain:true" onclick="edit()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
           data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
           data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
           data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
        <a href="student/export" class="easyui-linkbutton"
           data-options="iconCls:'icon-redo',plain:true">导出</a>
        <br>
        <span>学号:</span> <input id="stunum"
                                style="border: 1px solid #ccc"> <a href="javascript:void(0)"
                                                                   class="easyui-linkbutton"
                                                                   data-options="iconCls:'icon-search',plain:true"
                                                                   onclick="doSearch()">查找</a>
    </div>
    <div id="dlg2" class="easyui-dialog" title="上传excel表格" style="width:400px;height:200px;padding:10px;"
         data-options="
				iconCls:'icon-add',
				closed:'true'
			">
        <form id="import" action="upfiles.html" method="post" enctype="multipart/form-data">
            <input class="easyui-filebox" name="file1" data-options="prompt:'Choose a file...'" style="width:100%">
        </form>
        <br>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="importsubmit()">Submit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#import').form('clear');">Clear</a>
        </div>
    </div>
    <div id="dlg" class="easyui-dialog" title="新增" style="width:400px;height:200px;padding:10px;"
         data-options="
				iconCls:'icon-add',
				closed:'true'
			">
        <form id="ff" method="post">
            <table>
                <tr>
                    <td>学号:</td>
                    <td><input class="easyui-textbox" type="text" name="stunum" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input class="easyui-textbox" type="text" name="stuname" data-options="required:true"></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()">Submit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear');">Clear</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    function importsubmit() {
        $('#dlg2').dialog('close');
        $('#import').form('submit', {
            url: 'student/importEXCEL',
            success: function (data) {
                $('#dg').datagrid('reload', {});
            }
        });
    }
    function submit() {
        $('#dlg').dialog('close');
        $('#ff').form('submit', {
            url: 'student/insert',
            success: function (data) {
                $('#dg').datagrid('reload', {});
                alert('添加成功');
                location.reload();
            }
        });
    }
    var selected = undefined;
    var editIndex = undefined;
    function onClickRow(index) {
        if (selected != index) {
            var flag = endEditing();
            if (flag) {
                $('#dg').datagrid('selectRow', index);
                selected = index;
            }
        }
    }
    function edit() {
        $('#dg').datagrid('beginEdit', selected);
        editIndex = selected;
    }
    function doSearch() {
        var rows = $("#dg").datagrid("getRows");
        for (var i = 0; i < rows.length; i++) {
            if ($("#stunum").val() == rows[i].stunum) {
                $('#dg').datagrid('selectRow', i);
                selected = i;
            }
        }
    }
    function accept() {
        if (endEditing()) {
            $('#dg').datagrid('acceptChanges');
        }
    }
    function endEditing() {
        if (selected == undefined) {
            return true
        }
        if ($('#dg').datagrid('validateRow', selected)) {
            if (editIndex != undefined) {
                $('#dg').datagrid('endEdit', editIndex);
                var row = $('#dg').datagrid('getRows')[editIndex];
                if (row.stunum == "" || row.stuname == "") {
                    alert("输入有误");
                    $('#dg').datagrid('rejectChanges');
                    $('#dg').datagrid('beginEdit', selected);
                    return false;
                } else {
                    $.ajax({
                        url: 'student/update',
                        dataType: "JSON",
                        type: "post",
                        data: {
                            "stunum": row.stunum,
                            "stuname": row.stuname,
                            "class1": row.class1,
                            "class2": row.class2,
                            "class3": row.class3,
                            "class4": row.class4,
                            "class5": row.class5,
                            "class6": row.class6,
                            "class7": row.class7,
                            "class8": row.class8,
                            "class9": row.class9,
                            "class10": row.class10,
                            "class11": row.class11,
                            "class12": row.class12,
                            "class13": row.class13,
                            "class14": row.class14,
                            "class15": row.class15,
                            "class16": row.class16,
                            "class17": row.class17,
                            "class18": row.class18
                        },
                        success: function (result) {
                            if (result == 1) {
                                alert("修改成功");
                                editIndex = undefined;
                            }
                        }
                    });
                    return true;
                }

            } else {
                return true;
            }
        }
    }

    function append() {
        $('#dlg').dialog('open');
    }

    function openimport() {
        $('#dlg2').dialog('open');
    }
    function removeit() {
        if (selected == undefined) {
            return
        }
        var row = $('#dg').datagrid('getRows')[selected];
        if (row.stunum == "" || row.stuname == "") {
            $('#dg').datagrid('deleteRow', selected);
            selected = undefined;
            editIndex = undefined;
        } else {
            $.ajax({
                url: 'student/delete',
                dataType: "JSON",
                type: "post",
                data: {
                    "stunum": row.stunum,
                },
                success: function (result) {
                    $('#dg').datagrid('deleteRow', selected);
                    selected = undefined;
                    editIndex = undefined;
                    alert("删除成功");
                }
            });
        }

    }


</script>
</body>
</html>