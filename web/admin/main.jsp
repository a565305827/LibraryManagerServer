<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
    String code = (String) request.getParameter("code");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="${ctx }/admin/css/style.css"
          type="text/css"/>
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx }/admin/css/pageStyle.css">

</head>
<body style="background: #f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">书库</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 新增
                    </button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3"></div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search">
                <span class="am-input-group-btn">
						<button class="am-btn am-btn-default" type="button"
                                id="input_search_btn">搜索</button>
					</span>
            </div>
        </div>
    </div>

</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>书籍Id</li>
        <li>书名</li>
        <li>作者</li>
        <li>入馆时间</li>
        <li>价格</li>
        <li>出版社</li>
        <li>操作员</li>
        <li>编辑</li>
        <li class="borrowclass">借阅</li>
        <li>删除</li>
    </ul>

    <div id="list_books_ul"></div>
    <%--<c:forEach items="${allBooks }" var="bookInfo" varStatus="status">--%>
    <%--<ul>--%>
    <%--<li>${status.index + 1}</li>--%>
    <%--<li>${bookInfo.bookName}</li>--%>
    <%--<li>${bookInfo.author }</li>--%>
    <%--<li>${bookInfo.inTime }</li>--%>
    <%--<li>${bookInfo.price }</li>--%>
    <%--<li>${bookInfo.press }</li>--%>
    <%--<li>${bookInfo.operator }</li>--%>
    <%--<li><a href="${ctx }/BooksLibrary?action=editUI&id=${bookInfo.id}"><img class="img_icon"--%>
    <%--src="${ctx }/admin/images/edit_icon.png"></a>--%>
    <%--</li>--%>
    <%--<li><a href="${ctx }/BooksLibrary?action=delBook&id=${bookInfo.id}"><img class="img_icon"--%>
    <%--src="${ctx }/admin/images/delete_icon.png"></a>--%>
    <%--</li>--%>
    </ul>
    <%--</c:forEach>--%>

    <!--分页-->
    <div id="page" class="page_div">aaa</div>
</div>

<div id="modal_view">


</div>

<div id="image_icon">

    <table>
        <TR>
            <TD class=forumrowhighlight height="30">
                <%--http://localhost:8080/UploadServlet?action=uploadImage--%>
                <form action="${pageContext.request.contextPath}/UploadServlet" enctype="multipart/form-data" name="f2" method="post">

                    <input type="submit" name="提交" class="am-btn am-btn-default">
                </form>
            </TD>
        </TR>
    </table>
</div>

<div id="modal_content">
    <form action="${pageContext.request.contextPath}/UploadServlet" enctype="multipart/form-data" name="f1" method="post">
        <input name="type" type="hidden" value="2" size="40">
        <input name="action" type="hidden" value="addBook" >
        <TABLE class="stu_table" cellSpacing=1 cellPadding=3 width="100%" align=center bgColor=#6ab6b6>
            <tr>
                <TD height="30" align="left" class="forumrow">添加书籍:</TD>
                <TD class="forumrowhighlight" height="30" align="right">
                    <div id="close"><img src="images/delete_icon.png" alt=""></div></TD>
            </tr>
            <TBODY>
            <TR>
                <TD class=forumrowhighlight><label>
                    <span>书名: </span>
                    <input name="bookname" type="text" class="am-form-field">
                </label>
                </TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight  height="30"><label>
                    <span>价格: </span>
                    <input height="30" name="price" type="number" class="am-form-field"></input>
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight  height="30"><label>
                    <span>作者: </span>
                    <input height="30" name="author" type="text" class="am-form-field"></input>
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight  height="30"><label>
                    <span>出版社: </span>
                    <input height="30" name="press" type="text" class="am-form-field"></input>
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight height="30"><label>
                    <span>操作者: </span>
                    <input height="30" name="operator" type="text" class="am-form-field"></input>
                </label></TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight height="30">
                    <label>
                        <span>数量: </span>
                        <input height="30" name="bookcase" type="number" class="am-form-field"></input>
                    </label>
                </TD>
            </TR>
            <TR>
                <TD class=forumrowhighlight height="30">
                    <label>
                        <span>书籍类型: </span>
                        <input height="30" name="booknum" type="text" class="am-form-field"></input>
                    </label>
                </TD>
            </TR>
            <tr>
                <td class=forumrowhighlight height="30">
                    <label>
                        <span>书籍介绍: </span>
                        <%--<input height="100"  name="content" type="text" class="am-form-field">--%>
                        <textarea cols="45" rows="10" name="content"></textarea>
                    </label>
                </td>
            </tr>
            <tr>
                <td class=forumrowhighlight height="30">
                    <label>
                        <span>书籍封面: </span>
                        <input height="30" name="imageurl" type="file">
                    </label>
                </td>
            </tr>
            <TR>
                <TD style="padding-top: 15px" height="35" colspan="2" class=forumrow>
                    <label>
                    <input type="submit" name="Submit" value="提交信息" size="50" class="am-btn am-btn-default" onclick="addBook()">
                    </label>
                    &nbsp;&nbsp;
                    <label>
                    <input type="reset" name="Submit" value="重新填写" class="am-btn am-btn-default">
                    </label>
                </TD>
            </TR>
            </TBODY>
        </TABLE>
    </form>
</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/admin/js/paging.js"></script>
<script>

    var loc = location.href;//获取整个跳转地址内容，其实就是你传过来的整个地址字符串
    console.log(loc);
    var stuid = loc.substring(loc.indexOf("=") + 1);
    console.log(stuid);

    function getBooks(page, pageSize, hasShowSize) {
        //返回json
        $.getJSON("http://localhost:8080/BooksLibrary?action=getListBooks&page=" + page + "&pageSize=" + pageSize + "&type=1", function (result) {
            //此处返回的data已经是json对象
            document.getElementById("list_books_ul").innerHTML = "";
            $.each(result.data, function (i, value) {
                console.log(i + ":-----" + value.author);

                var html = '<ul class="list_goods_ul"><li>' + (++hasShowSize) + '</li>\n' +
                    '            <li>' + value.bookid + '</li>\n' +
                    '            <li>' + value.bookname + '</li>\n' +
                    '            <li>' + value.author + '</li>\n' +
                    '            <li>' + value.inTime + '</li>\n' +
                    '            <li>' + value.price + '</li>\n' +
                    '            <li>' + value.press + '</li>\n' +
                    '            <li>' + value.operator + '</li>\n' +
                    '            <li><img class="img_icon" src="${ctx }/admin/images/edit_icon.png" onclick="editBook(' + value.bookid + ')">\n' +
                    '            </li>\n' +
                    '            <li id="' + value.bookid + '"' + '" class="borrowclass"><button onclick="borrow(' + value.bookid + ')">借阅</button> </li>\n' +
                    '            <li><a href="http://localhost:8080/BooksLibrary?action=delBook&type=2&barcode=' + value.barcode + '"' + '><img class="img_icon"\n' +
                    '                                                                                     src="${ctx }/admin/images/delete_icon.png"></a>\n' +
                    '            </li></ul>';
                document.getElementById("list_books_ul").innerHTML += html;
            });

            var byClass = getByClass(document, "borrowclass");
            if (loc.indexOf("=") !== -1) {
                for (var i=0; i<byClass.length; i++) {
                    byClass[i].removeAttribute("hidden");
                }
            } else {
                for (var i=0; i<byClass.length; i++) {
                    byClass[i].hidden = "hidden";
                }
            }

            console.log(result);
            //分页
            var pageSize = 3;
            $("#page").paging({
                pageNo: result.page,
                totalPage: Math.ceil(result.totalSize / pageSize),
                totalSize: result.totalSize,
                callback: function (num) {
                    //在这个里去请求服务器，按页
                    //location.href = "${pageContext.request.contextPath }/BooksLibrary?action=getListBooks&page=" + num + "&pageSize=" + pageSize;
                    var hasShowSize = (num - 1) * result.pageSize;
                    getBooks(num, pageSize, hasShowSize);
                }
            });
        });
    }

    window.getBooks(1, 3, 0);

    // $("#add").click(function () {
    //     $.post("http://localhost:8080/BooksLibrary?action=addBook", {
    //         bookName: "庆余年",
    //         price: "23",
    //         author: "猫腻",
    //         press: "人民出版社",
    //         operator: "刘一峰",
    //         bookcase: "文学著作"
    //     }, function (result, status) {
    //         alert("Data: " + data + "nStatus: " + status);
    //     });
    // });

    //借阅  参数传入,可以从学生页跳过来, stuid, 期待还书时间默认为当前时间的30天以后
    function borrow(bookid) {
        console.log(bookid);
        console.log(getExpectDateReturn());
        $.post("http://localhost:8080/BorrowServlet?action=borrowBook", {
            stuid: stuid,
            bookid: bookid,
            expectreturndate: getExpectDateReturn(),
            operator: "${admin.name}"
        }, function (result, status) {
            var parse = JSON.parse(result);
            console.log(parse);
            if (parse.code === "1") {
                alert("借阅成功");
            } else {
                alert("借阅失败");
            }
        });
    }

    function getByClass(parent, cls){
        var res = [];
        var reg = new RegExp('\\b' + cls + '\\b', 'i');  //匹配cls是一个独立的单词
        var ele = parent.getElementsByTagName('*');
        for(var i = 0; i < ele.length; i++){
            if(reg.test(ele[i].className)){
                res.push(ele[i]);
            }
        }
        return res;
    }

    function getExpectDateReturn() {
        var date1 = new Date();
        var date2 = new Date(date1);
        date2.setDate(date1.getDate() + 30);
        return date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate();
    }

    $('#add').click(function () {
        $("#modal_view").fadeIn();
        $("#modal_content").fadeIn();
    });


    $("#close").click(function () {
        $("#modal_view").fadeOut();
        $("#modal_content").fadeOut();
    });


    function editBook(bookid) {
        $("#modal_view").fadeIn();
        $("#image_icon").fadeIn();
    }


    $("#close_image").click(function () {
        $("#modal_view").fadeOut();
        $("#image_icon").fadeOut();
    });
</script>

</body>
</html>