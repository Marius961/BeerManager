<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"  crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/registration.css">
</head>
<body>
<div class="container1">
    <div class="btn-bar">
        <form action="<c:url value="/index"/>">
            <button type="submit" class="brn-2">Home</button>
        </form>
    </div>

    <div class="box">
        <form name="form_login" action="<c:url value='/j_spring_security_check'/>" method="POST">
            <h1 class="form">Sing in</h1>
            <c:if test="${not empty error}">
                <div class="login-failed-message">
                        ${error}
                </div>
            </c:if>
            <div class="form-group">
                <label for="user_tel">Tel. number</label>
                <input type="text" class="form-control" id="user_tel" name="user_tel" value="" placeholder="+380000000000" />
            </div>
            <div class="form-group">
                <label for="user_pass">Password</label>
                <input type="password" class="form-control" id="user_pass" name="user_password" value="" placeholder="HardPass123" />
            </div>
            <label for="rememberMe">Remember me</label>
            <input type="checkbox" name="rememberMe" id="rememberMe">
            <br>
            <button name="submit" type="submit" class="btn btn-primary">Sign in</button>
        </form>
        <p class="p-alt">OR</p>
        <a class="link-1" href="">Sign up</a>
    </div>
</div>

</body>
</html>
