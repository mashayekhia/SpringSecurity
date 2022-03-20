<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۰۴/۰۳/۲۰۲۲
  Time: ۰۴:۴۳
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Home Page</title>
    <spring:url value="/resources/home.css" var="home_css"/>
    <link rel="stylesheet" href="${home_css}">
</head>
<body>
<div class="infobox">
    <security:authorize access="hasRole('USER')">
        Hey User
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
        Hey Admin
    </security:authorize>
    <%--<h1 style="color: green">User ${user.firstname} ${user.lastname} Welcome To Home Page</h1>--%>
    <h2>Fullscreen Background</h2>

    This will apply the resized image to the background of every page that referances the stylesheet. if for example you
    want it to only be on one page kinda like twitter has it only on their landing page and not all the others. you can
    easly accomplish this by changing the css rule to:
    <br>
    <br>
    "body.landing" instead of just "body" ("landing" can be anything you want)
    <br>
    <br>
    then on the desired page you body tag should look like this:
    <br><br>
    "< body class="landing" > " instead of just " < body > "
    <br><br>
</div>
<a href="/perform_logout">log out</a>
</body>
</html>
