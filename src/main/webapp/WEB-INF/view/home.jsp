<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۰۴/۰۳/۲۰۲۲
  Time: ۰۴:۴۳
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<security:authorize access="hasRole('USER')">
    Hey User
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
    Hey Admin
</security:authorize>
<%--<h1 style="color: green">User ${user.firstname} ${user.lastname} Welcome To Home Page</h1>--%>
<a href="/perform_logout">log out</a>
</body>
</html>
