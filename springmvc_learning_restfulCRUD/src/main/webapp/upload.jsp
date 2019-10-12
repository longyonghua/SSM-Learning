<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>传统方式上传</h2>
<form action="springmvc/testUpload" enctype="multipart/form-data" method="post">
    选择文件：<input type="file" name="upload"/><br/>
    <input type="submit" value="上传"/>
</form>

<h2>springmvc方式上传</h2>
<form action="springmvc/testUpload2" enctype="multipart/form-data" method="post">
    选择文件：<input type="file" name="upload"/><br/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
