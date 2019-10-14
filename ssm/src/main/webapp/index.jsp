<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>测试查询</h2>
    <a href="account/findAll">findAll</a>

    <h2>测试保存</h2>
    <form action="account/saveAccount" method="post">
        姓名：<input type="text" name="name"/><br/>
        金额：<input type="text" name="money"/><br/>
        <input type="submit" value="提交"/><br/>
    </form>
</body>
</html>
