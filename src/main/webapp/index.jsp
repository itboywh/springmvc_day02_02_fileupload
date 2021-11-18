<%--
  Created by IntelliJ IDEA.
  User: 儒雅
  Date: 2021/11/15
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>传统方式文件上传</h3>
<form action="user/fileupload01" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/></br>
    <input type="submit" value="上传文件"/>
</form>
<h3>springmvc文件上传</h3>
<form action="user/fileupload02" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/></br>
    <input type="submit" value="上传文件"/>
</form>
<h3>跨服务器文件上传</h3>
<form action="user/fileupload03" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/></br>
    <input type="submit" value="上传文件"/>
</form>

</body>
</html>
