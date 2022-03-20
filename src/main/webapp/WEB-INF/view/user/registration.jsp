<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <spring:url value="/resources/registration.css" var="registration_css"/>
    <link rel="stylesheet" href="${registration_css}">
</head>
<body>
<h3 style="color: red">${message}</h3>
<div class="container">
    <form id="regForm" action="registration" method="post" class="registration">
        <h1>Registration Form</h1>

        <label for="firstname">
            <span>Firstname</span>

            <input type="text" id="firstname" name="firstname" minlength="3" required>
            <span id="firstname_error" style="display:none"></span>

            <ul class="input-requirements">
                <li>At least 3 characters long</li>
                <li>Must only contain letters and numbers (no special characters)</li>
            </ul>
        </label>

        <label for="username">
            <span>Username</span>

            <input type="text" id="username" name="username" minlength="3" required>
            <span id="username_error" style="display:none"></span>

            <ul class="input-requirements">
                <li>At least 3 characters long</li>
                <li>Must only contain letters and numbers (no special characters)</li>
            </ul>
        </label>

        <label for="email">
            <span>Email</span>
            <input type="text" id="email" name="email" required>
            <span id="email_error" style="display:none"></span>
        </label>

        <label for="password">
            <span>Password</span>

            <input type="password" id="password" name="password" maxlength="100" minlength="3" required>
            <span id="password_error" style="display:none"></span>

            <ul class="input-requirements">
                <li>At least 8 characters long (and less than 100 characters)</li>
                <li>Contains at least 1 number</li>
                <li>Contains at least 1 lowercase letter</li>
                <li>Contains at least 1 uppercase letter</li>
                <li>Contains a special character (e.g. @ !)</li>
            </ul>
        </label>

        <label for="confirmPassword">
            <span>Repeat Password</span>
            <input type="password" id="confirmPassword" name="confirmPassword" maxlength="100" minlength="3" required>

            <span id="global_error" style="display:none"></span>
        </label>

        <br>

        <input type="submit">
<%--        <button onclick='registerNewUserWithApi()'>submit with Api</button>--%>
    </form>
</div>

<%--<script src="\webjars\jquery-3.6.0.min.js"></script>--%>
<%--<script type="text/javascript">--%>
<%--    const serverContext = "http://localhost:8081";--%>
<%--    function registerNewUserWithApi() {--%>
<%--        alert("???")--%>
<%--        alert(serverContext + "/api/user/registration")--%>
<%--        //$(".alert").html("").hide();--%>
<%--        var formData = $('regForm').serialize();--%>
<%--        $.post(serverContext + "/api/user/registration", formData, function (data) {--%>
<%--            if (data.message === "success")--%>
<%--                window.location.href = serverContext + "/registration_success.html";--%>
<%--        }).fail(function (data) {--%>
<%--            switch (true) {--%>
<%--                case data.responseJson.error.indexOf("MailError") > -1 :--%>
<%--                    alert("1")--%>
<%--                    window.location.href = serverContext + "/error/emailError";--%>
<%--                    break;--%>
<%--                case data.responseJson.error.indexOf("InternalError") > -1 :--%>
<%--                    alert("2")--%>
<%--                    window.location.href = serverContext + "/login.html?message=" + data.responseJson.message;--%>
<%--                    break;--%>
<%--                case data.responseJson.error === "UserAlreadyExist":--%>
<%--                    alert("3")--%>
<%--                    $("#email_error").show().html(data.responseJson.error);--%>
<%--                    break;--%>
<%--                default: {--%>
<%--                    alert("4")--%>
<%--                    var messages = $.parseJSON(data.responseJson.message);--%>
<%--                    $.each(messages, function (index, item) {--%>
<%--                        $("#" + item.field + "_error").show().html(item.defaultMessage);--%>
<%--                    });--%>
<%--                    var errors = $.parseJSON(data.responseJson.error);--%>
<%--                    $.each(errors, function (index, item) {--%>
<%--                        $("#global_error").show().append(item.defaultMessage + "<br>")--%>
<%--                    })--%>
<%--                }--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>