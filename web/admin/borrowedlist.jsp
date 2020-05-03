<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx }/admin/css/pageStyle.css">
    <script src="${ctx }/admin/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx }/admin/js/paging.js"></script>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学生已借阅书籍列表</strong>
            <small></small>
        </div>
    </div>
    <%--<hr>--%>
    <%--<div class="am-g">--%>
    <%--<div class="am-u-sm-12 am-u-md-6">--%>
    <%--<div class="am-btn-toolbar">--%>
    <%--<div class="am-btn-group am-btn-group-xs">--%>
    <%--<button id="add" class="am-btn am-btn-default">--%>
    <%--<span class="am-icon-plus"></span> 添加学生--%>
    <%--</button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
</div>

<div class="goods_list" class="borrow_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>学生Id</li>
        <li>书籍Id</li>
        <li>学生姓名</li>
        <li>书籍名称</li>
        <li>借书日期</li>
        <li>预计归还日期</li>
        <li>还书</li>
    </ul>

    <%--<ul class="list_goods_ul">--%>
    <%--<li>01</li>--%>
    <%--<li>2011</li>--%>
    <%--<li>男</li>--%>
    <%--<li>刘一峰</li>--%>
    <%--<li>yifeng@qq.com</li>--%>
    <%--<li>13313364159</li>--%>
    <%--<li>2019.01.20</li>--%>
    <%--<li>吴磊</li>--%>
    <%--<li><img class="img_icon" src="${ctx }/admin/images/edit_icon.png" alt=""></li>--%>
    <%--<li id="del"><img class="img_icon" src="${ctx }/admin/images/delete_icon.png" alt=""></li>--%>
    <%--</ul>--%>

    <div id="list_books_ul"></div>
    <!--分页-->
    <div id="page" class="page_div">aaa</div>
</div>

<div id="modal_view">


</div>

<div id="modal_content">
    <div id="close"><img src="images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加学生：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>学生名称：</span>
                <input type="text" class="am-form-field">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button">添加</button>
        </div>

    </div>
</div>

<script>

    var loc = location.href;//获取整个跳转地址内容，其实就是你传过来的整个地址字符串
    var stuid = loc.substring(loc.indexOf("=")+1);
    function getStudentBorrowed(page, pageSize, hasShowSize) {
        $.post("http://localhost:8080/StuBorrowServlet?action=getStuBorrowRecord", {
            stuid: stuid,
            page: page,
            pageSize: pageSize,
            type:1
        }, function (result) {
            //此处返回的data已经是json对象

            document.getElementById("list_books_ul").innerHTML = "";
            var parse = JSON.parse(result);
            $.each(parse.data, function (i, value) {
                var html = '<ul class="list_goods_ul"><li>' + (++hasShowSize) + '</li>\n' +
                    '            <li>' + value.stuid + '</li>\n' +
                    '            <li>' + value.bookid + '</li>\n' +
                    '            <li>' + value.name + '</li>\n' +
                    '            <li>' + value.bookname + '</li>\n' +
                    '            <li>' + value.borrowdate + '</li>\n' +
                    '            <li>' + value.expect_return_date + '</li>\n' +
                    '            <li id="'+ value.bookid +'"'+'><button onclick="returnbook(' + value.bookid + ')">还书</button></li>\n' +
                    '            </ul>';

                document.getElementById("list_books_ul").innerHTML += html;
            });


            //分页
            var pageSize = 3;
            $("#page").paging({
                pageNo: parse.page,
                totalPage: Math.ceil(parse.totalSize / pageSize),
                totalSize: parse.totalSize,
                callback: function (num) {
                    //在这个里去请求服务器，按页
                    var hasShowSize = (num - 1) * parse.pageSize;
                    getStudentBorrowed(num, pageSize, hasShowSize);
                }
            });
        });
    }

    window.getStudentBorrowed(1, 3, 0);

    function returnbook(bookid) {
        //还书
        $.post("http://localhost:8080/ReturnTableServlet?action=returnBook", {
            stuid: stuid,
            bookid: bookid,
            type:2
        }, function (result) {
            var parse = JSON.parse(result);
            if (parse.code === "1") {
                alert("还书成功");
                getStudentBorrowed(1,3,0);
            } else {
                alert("还书失败");
            }
        });
    }

    $('#add').click(function () {
        $("#modal_view").fadeIn();
        $("#modal_content").fadeIn();
    });

    $("#close").click(function () {
        $("#modal_view").fadeOut();
        $("#modal_content").fadeOut();
    });
</script>
</body>
</html>