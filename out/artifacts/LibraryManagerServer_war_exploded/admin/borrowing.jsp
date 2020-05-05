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
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学生借阅管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加学生
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" class="borrow_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>学生Id</li>
        <li>性别</li>
        <li>学生姓名</li>
        <li>邮箱地址</li>
        <li>电话号码</li>
        <li>借书日期</li>
        <li>操作者</li>
        <li>查看学生借阅书籍</li>
        <li>去书库借阅</li>
        <li>删除分类</li>
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

    <form action="http://localhost:8080/StudentServlet"  name="f1">
        <input name="type" type="hidden" value="2" size="40">
        <input name="utype" type="hidden" value="3" size="40">
        <input name="action" type="hidden" value="addStudent" size="40">
        <TABLE class="stu_table" cellSpacing=1 cellPadding=3 width="100%" align=center bgColor=#6ab6b6>
            <TBODY>
            <tr>
                <TD height="30" align="center" class=forumrow>添加学生:</TD>
                <TD class=forumrowhighlight
                    height="30">
                    <div id="close"><img src="images/delete_icon.png" alt=""></div></TD>
            </tr>

            <TR>
                <TD class=forumrowhighlight
                    height="30"><label>
                    <span>姓名: </span>
                    <input name="name" type="text" size="40" class="am-form-field">
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight height="30"><label>
                    <span>手机号: </span>
                    <input name="name" type="text" size="40" class="am-form-field"/>
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight height="30"><label>
                    <span>密码: </span>
                    <input name="pwd" type="text" size="50" class="am-form-field"/>
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight  height="30"><label>
                    <span>邮箱: </span>
                    <input name="email" type="text" size="50" class="am-form-field"/>
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight height="30"><label>
                    <span>操作者: </span>
                    <input name="operator" type="text" size="50" class="am-form-field"/>
                </label></TD>
            </TR>
            <TR>
                <TD height="35" colspan="2" class=forumrow>
                    <label>
                        <input type="submit" name="Submit" value="提交信息" class="am-btn am-btn-default"/>
                    </label>
                    &nbsp;&nbsp;
                    <label>
                        <input type="reset" name="Reset" value="重新填写" class="am-btn am-btn-default"/>
                    </label>
                </TD>
                 </TR>
            </TBODY>
        </TABLE>
    </form>
</div>

<script>
    //获取学生列表
    function getStudents(page, pageSize, hasShowSize) {
        $.getJSON("http://localhost:8080/StudentServlet?action=getListStudent&page=" + page + "&pageSize=" + pageSize + "&type=1", function (result) {
            //此处返回的data已经是json对象
            //console.log(result);
            document.getElementById("list_books_ul").innerHTML = "";
            $.each(result.data, function (i, value) {
                //console.log(i + ":-----" + value.author);
                var html = '<ul class="list_goods_ul"><li>' + (++hasShowSize) + '</li>\n' +
                    '            <li>' + value.stuid + '</li>\n' +
                    '            <li>' + value.sex + '</li>\n' +
                    '            <li>' + value.name + '</li>\n' +
                    '            <li>' + value.email + '</li>\n' +
                    '            <li>' + value.tel + '</li>\n' +
                    '            <li>' + value.createDate + '</li>\n' +
                    '            <li>' + value.operator + '</li>\n' +
                    '            <li><a href="${ctx}/admin/borrowedlist.jsp?stuid=' + value.stuid + '"><button>查看借阅书籍</button></a></li>\n' +
                    '            <li><a href="${ctx}/admin/main.jsp?stuid=' + value.stuid + '"><button>借阅</button></a>\n' +
                    '            </li>\n' +
                    '            <li><a href="http://localhost:8080/StudentServlet?action=delStudent&type=2&stuid=' + value.stuid + '"' + '><img class="img_icon"\n' +
                    '                                                                                     src="${ctx }/admin/images/delete_icon.png"></a>\n' +
                    '            </li></ul>';
                document.getElementById("list_books_ul").innerHTML += html;
            });

            //分页
            var pageSize = 3;
            $("#page").paging({
                pageNo: result.page,
                totalPage: Math.ceil(result.totalSize / pageSize),
                totalSize: result.totalSize,
                callback: function (num) {
                    //在这个里去请求服务器，按页
                    var hasShowSize = (num - 1) * result.pageSize;
                    getStudents(num, pageSize, hasShowSize);
                }
            });
        });
    }

    window.getStudents(1, 3, 0);

    //添加学生
    // $('#add').click(function () {
    //     // $("#modal_view").fadeIn();
    //     // $("#modal_content").fadeIn();
    //
    //     $.post("http://localhost:8080/StudentServlet?action=addStudent", {
    //         name: "简言",
    //         sex: "男",
    //         birthday: "2019-10-1",
    //         tel: "13313364785",
    //         email: "yifeng@163.com",
    //         operator: "吴磊",
    //         type:""
    //     }, function (result, status) {
    //         alert("Data: " + result + "nStatus: " + status);
    //     });
    // });

    $('#add').click(function () {
        $("#modal_view").fadeIn();
        $("#modal_content").fadeIn();
    });


    $("#close").click(function () {
        $("#modal_view").fadeOut();
        $("#modal_content").fadeOut();
    });

    $("#del").click(function () {
        $.post("http://localhost:8080/StudentServlet?action=delStudent", {
            id: "3",
            type: "1"
        }, function (result, status) {
            alert("Data: " + result + "nStatus: " + status);
        });
    });
</script>
</body>
</html>