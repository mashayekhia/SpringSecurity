<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form method="post" action="registration" enctype="application/x-www-form-urlencoded">
    <div>
        <label for="firstname">Firstname</label>
        <input id="firstname" name="firstname" type="text" value="">
    </div>
    <div>
        <label for="lastname">Lastname</label>
        <input id="lastname" name="lastname" type="text">
    </div>
    <div>
        <label for="username">Username</label>
        <input id="username" name="username" type="text">
    </div>
    <div>
        <label for="email">Email</label>
        <input id="email" name="email" type="text">
    </div>
    <div>
        <label for="password">Password</label>
        <input id="password" name="password" type="password">
    </div>
    <div>
        <label for="confirmPassword">Confirm Password</label>
        <input id="confirmPassword" name="matchingPassword" type="password">
    </div>
    <input type="submit" value="register">
</form>
<h3 style="color: red">${message}</h3>
</body>
</html>