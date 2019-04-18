<%--
  Created by IntelliJ IDEA.
  User: bangyu
  Date: 2019/4/16
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书管理员系统</title>
    <link rel="stylesheet"  href="/static/css/menu-css.css">
    <link rel="stylesheet"  href="/static/css/style.css">
    <%--<script src="/static/js/jquery-3.2.1.js"></script>--%>
    <link href="/static/LigerUI/Source/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
    <script src="/static/LigerUI/Source/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="/static/LigerUI/Source/lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <head/>
<body>
    <script type="text/javascript">
        var CustomersData ;
        function upd() {
            $.ajax({
                type: "get",
                url: "http://localhost:8080/allbookjson",
                success: function(result) {
                    console.log(result);
                    data=result;
                }
            });
        }
        upd();
        var grid = null;

        var dataStr;
        $(function () {
            window['g'] =grid =  $("#maingrid4").ligerGrid({
                checkbox: true,
                columns: [
                    { display: 'ISBN', name: 'bookId', align: 'left', width: 140 },
                    { display: '书名', name: 'name', minWidth: 60, editor: { type: 'text' } },
                    { display: '作者', name: 'author', width: 140,align:'left',editor: { type: 'text' } },
                    { display: '出版信息', name: 'publish',minWidth: 100,editor: { type: 'text' }},
                    { display: '出版日期', name: 'pubdate',width:80, minWidth: 50 ,editor: { type: 'text' }},
                    { display: '价格', name: 'price',width:70, minWidth: 70 ,editor: { type: 'text' }},
                    { display: '类型', name: 'classnum', minWidth: 50 ,width:50,editor: { type: 'text' }}
                ],  pageSize:10,where : f_getWhere(),

                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                },
                dataAction:"local",
                dataType: "json",
                url:"http://localhost:8080/allbookjson",
                enabledEdit: true, clickToEdit: false, isScroll: false,
                isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
                width: '92%',height:'100%'
            });


            $("#pageloading").hide()
        });
        function f_onCheckAllRow(checked)
        {
            for (var rowid in this.records)
            {
                if(checked)
                    addCheckedCustomer(this.records[rowid]['bookId']);
                else
                    removeCheckedCustomer(this.records[rowid]['bookId']);
            }
        }
        function f_search()
        {
            grid.options.data = $.extend(true, {}, CustomersData);
            grid.loadData(f_getWhere());
        }
        function f_getWhere()
        {
            if (!grid) return null;
            var clause = function (rowdata, rowindex)
            {
                var key = $("#txtKey").val();
                return rowdata.isbn.indexOf(key) > -1;
            };
            return clause;
        }
        function beginEdit() {
            var row =grid.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            grid.beginEdit(row);
        }
        function cancelEdit() {
            var row = grid.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            grid.cancelEdit(row);
        }
        function cancelAllEdit()
        {
            grid.cancelEdit();
        }
        function endEdit()
        {
            var row = grid.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            grid.endEdit(row);
        }
        function endAllEdit()
        {
            grid.endEdit();
        }
        function deleteRow()
        {
            grid.deleteSelectedRow();
        }
        var newrowid = 100;
        function addNewRow()
        {
            grid.addEditRow();
        }

        function getSelected()
        {
            var row = grid.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
        function getData()
        {
            var data = grid.getData();
            alert(JSON.stringify(data));
        }
        var checkedCustomer = [];
        function findCheckedCustomer(bookId)
        {
            for(var i =0;i<checkedCustomer.length;i++)
            {
                if(checkedCustomer[i] == bookId) return i;
            }
            return -1;
        }
        function addCheckedCustomer(bookId)
        {
            if(findCheckedCustomer(isbn) == -1)
                checkedCustomer.push(bookId);
        }
        function removeCheckedCustomer(bookId)
        {
            var i = findCheckedCustomer(bookId);
            if(i==-1) return;
            checkedCustomer.splice(i,1);
        }
        function f_isChecked(rowdata)
        {
            if (findCheckedCustomer(rowdata.bookId) == -1)
                return false;
            return true;
        }
        function f_onCheckRow(checked, data)
        {
            if (checked) addCheckedCustomer(data.bookId);
            else removeCheckedCustomer(data.bookId);
        }
        function f_getChecked()
        {
            alert(checkedCustomer.join(','));
        }
    </script>
    <!-- 标题 -->
    <div class="myTitle">
        <img src="../IMG/title.png">
    </div>
    <!-- 导航 -->
    <div class="myNav"style="position:  relative;z-index: 1">
        <ul class="nav nav-pills nav-justified">
            <li class="dropdown myLi" >
                <a href="" class="dropdown-toggle"
                   data-toggle="dropdown">图书管理<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="./information.html">信息管理</a></li>
                    <li class="divider"></li>
                    <li><a href="./in.html">入库管理</a></li>
                    <li class="divider"></li>
                    <li><a href="/010300.htm">出库管理</a></li>
                    <li class="divider"></li>
                    <li class="divider"></li>
                    <li><a href="/010400.htm">采购管理</a></li>

                </ul>
            </li>
            <li class="dropdown myLi" >
                <a href="" class="dropdown-toggle"
                   data-toggle="dropdown">员工管理<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/board/1/0.htm">信息管理</a></li>
                    <li class="divider"></li>
                    <li><a href="/news/1/0.htm">新增员工</a></li>
                </ul>
            </li>
            <li class="dropdown myLi" >
                <a href="" class="dropdown-toggle"
                   data-toggle="dropdown">读者管理<span class="caret"></span></a>
            </li>
            <li class="dropdown myLi" >
                <a href="" class="dropdown-toggle"
                   data-toggle="dropdown">管理员中心<span class="caret"></span></a>
            </li>
        </ul>
    </div>
     <!--右侧-->
    <div class="SheetMain">
        <a class="pos">当前位置：图书管理 / 信息管理</a>
        <div id="searchbar">
              <p class="searchISBN">ISBN：</p>
            <input id="txtKey" type="text"placeholder="">
              <input id="btnOK" type="button" value="查询" onclick="f_search()">
        </div >

        <div class="btnList">
            <a class="l-button" style="width:80px;float:left; margin-left:6px;" onclick="beginEdit()">修改行</a>
            <a class="l-button" style="width:80px;float:left; margin-left:6px;" onclick="endEdit()">提交修改</a>
            <a class="l-button" style="width:80px;float:left; margin-left:6px;" onclick="endAllEdit()">提交全部</a>
            <a class="l-button" style="width:80px;float:left; margin-left:6px;" onclick="cancelEdit()">取消修改</a>
            <a class="l-button" style="width:80px;float:left; margin-left:6px;" onclick="cancelAllEdit()">取消全部</a>
            <a class="l-button" style="width:120px;float:left; margin-left:6px;" onclick="deleteRow()">删除选择的行</a>
            <a class="l-button" style="width:100px;float:left; margin-left:6px;" onclick="addNewRow()">添加行</a>

        </div>
        <div id="maingrid4" style="margin:20px; padding:10px"></div>
    </div>
    <!-- g data total ttt -->
    </body>
</html>