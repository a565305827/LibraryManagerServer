<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/admin/css/common.css">
</head>
<style>
    .admin_top{
        height: 70px;
        background: #1cc09f;
        text-align: right;
        padding-right: 70px;
        color: white;
        position: relative;
    }

    .admin_top span{
        position: absolute;
        right: 20px;
        top: 30px;

    }
    .admin_top img{
        margin-top: 20px;
    }
    .admin_top .top_left{
        width: 250px;
        height: 70px;
        background:#283643;
        float: left;
        color: white;
        font-weight: bold;
        text-align: center;
        padding-right: 15px;
        padding-top: 20px;
        font-size: 20px;

    }
    .admin_top .top_left:hover{
        background: #000;
    }


    
</style>
<body style="background:blue">

<div class="admin_top">
    <div class="top_left">
        图书管理系统
    </div>
    <div class="h_top_right">
        <img src="${ctx }/admin/images/user_icon.png" alt="">
        <!-- 从session域 当中取出数据 -->
        <span>${admin.name }</span>
    </div>

</div>

</body>
</html>