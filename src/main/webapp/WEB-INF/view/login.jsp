<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۰۴/۰۳/۲۰۲۲
  Time: ۰۴:۲۱
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<spring:url value="/resources/login.css" var="login_css"/>
<link rel="stylesheet" href="${login_css}">
<body>
<div class="login">
    <h1>Login</h1>
    <form action="perform_login" method='POST'>
        <label for="username">
            <input type='text' name='username' required="required">
        </label>
        <label for="password">
            <input type='password' name='password' required="required"/>
        </label>
        <button name="submit" type="submit">login in</button>
    </form>
    <a href="/forget_password.html">Forget Password</a>
    <a href="/home.html">Home Page</a>
</div>
</body>
</html>