<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۰۴/۰۳/۲۰۲۲
  Time: ۰۴:۵۲
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: green">${user.email} are registered Successfully</h1>
<h1 style="color: green">Wir schickten eine Email, die ein Token drinsteckt</h1>
<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>
