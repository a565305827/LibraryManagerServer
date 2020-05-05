<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String ctx = request.getContextPath();
    System.out.println("ctx=" + ctx);
    pageContext.setAttribute("ctx", ctx);
%>
<head>
    <title>练习</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/UploadServlet" enctype="multipart/form-data" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="file" name="pic">
    <input type="submit">
</form>

</body>
</html>