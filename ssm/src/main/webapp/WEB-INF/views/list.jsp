<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>成功</h2>
    <c:forEach items="${accounts}" var="account">
        ${account.name}--${account.money}
    </c:forEach>
</body>
</html>
