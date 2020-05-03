<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
    System.out.println(ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx }/admin/js/pageStyle.css">
    <script src="${ctx }/admin/js/jquery.min.js"></script>
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加管理员
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="account_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>用户</li>
        <li>修改密码</li>
        <li>移除管理员</li>
    </ul>

    <c:forEach items="${allManagers }" var="manager" varStatus="status">

        <ul class="list_goods_ul">
            <li id="${manager.id} " name="index">${status.index + 1}</li>
            <li>${manager.name }</li>
            <li class="edit_img"><img id="${manager.pwd}" class="img_icon" src="${ctx }/admin/images/edit_icon.png"
                                      alt=""></li>
            <li><a href="${ctx}/AdminManager?action=delManager&type=2&id=${manager.id}"><img class="img_icon"
                                                                                          src="${ctx }/admin/images/delete_icon.png"alt=""></a></li>
        </ul>
    </c:forEach>

    <%
        if (request.getAttribute("adderror") != null) {
    %>
    <div class="type1" id="myPopup">
        <font face="幼圆"><b>输入的用户或密码已经存在</b>
        </font>
    </div>
    <%
        }
    %>
</div>

<div id="modal_view">


</div>

<div id="modal_content_account">
    <div id="close"><img src="${ctx }/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span id="edit_add_label"></span>
            </div>
        </div>
        <div id="username_div" class="item1">
            <div>
                <span>用户名：</span>
                <input id="username" type="text" class="am-form-field">&nbsp;&nbsp;
            </div>

        </div>
        <div class="item1">
            <div>
                <span>密码：</span>
                <input id="pwd" type="text" class="am-form-field">&nbsp;&nbsp;
            </div>

        </div>
        <div class="item1" id="edit_add_btn">
            <button id="add-manager" class="am-btn am-btn-default" type="button">添加</button>
        </div>

    </div>
</div>

<script>
    var fag = false;
    var pwd;
    $(function () {
        $('#add').click(function () {
            fag = false;
            $("#modal_view").fadeIn();
            $("#modal_content_account").fadeIn();
            document.getElementById("edit_add_label").textContent = "添加管理员:";
            document.getElementById("add-manager").textContent = "添加";
            document.getElementById("username_div").removeAttribute("hidden");
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account").fadeOut();
        });

        $(".edit_img").click(function () {
            fag = true;
            pwd = this.firstChild.id;
            $("#modal_view").fadeIn();
            $("#modal_content_account").fadeIn();
            document.getElementById("username_div").hidden = "hidden";
            document.getElementById("edit_add_label").textContent = "修改密码:";
            document.getElementById("add-manager").textContent = "修改";
        });

        $("#add-manager").click(function () {
            if (fag) {   //修改密码
                location.href = "${ctx}/AdminManager?action=updateManager&type=2&id=" +document.getElementsByName('index').id+  "&oldPwd=" + pwd + "&newPwd=" + document.getElementById('pwd').value;
            } else {  //添加管理员
                //location.href = "${ctx}/AdminManager?action=add&name=刘亦菲&pwd=lll";
                alert(document.getElementsByName('index').id);
                location.href = "${ctx}/AdminManager?action=add&&type=2&id=" +document.getElementsByName('index').id+ "&name=" + document.getElementById('username').value + "&pwd=" + document.getElementById('pwd').value;
            }
        });
    });
</script>
</body>
</html>