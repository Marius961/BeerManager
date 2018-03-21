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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/registration.css"/>">
</head>
<body>
<div class="container1">
    <div class="btn-bar">
        <form action="<c:url value="/index"/>">
            <button type="submit" class="brn-2">Home</button>
        </form>
    </div>
    <div class="box">
        <form:form method="post" modelAttribute="user" action="/register-user">
            <h1 class="form">Sing up</h1>
            <div class="form-group">
                <form:label path="fullName" for="name_input">Full name</form:label>
                <form:input path="fullName" type="text" class="form-control" id="name_input" placeholder="Vasia Pupkin" />
                <form:errors path="fullName" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="companyName" for="company_name_input">Company name</form:label>
                <form:input path="companyName" type="text" class="form-control" id="company_name_input" placeholder="Pupkin inc." />
                <form:errors path="companyName" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="companyAddress" for="company_addr_input">Company address</form:label>
                <form:input path="companyAddress" type="text" class="form-control" id="company_addr_input" placeholder="Redmond, WA 98052-7329 USA" />
                <form:errors path="companyAddress" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="email" for="email_input">Email</form:label>
                <form:input path="email" type="email" class="form-control" id="email_input" placeholder="vasia@gmail.com" />
                <form:errors path="email" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="telNumber" for="tel_input">Tel. number</form:label>
                <form:input path="telNumber" type="text" class="form-control" id="tel_input" placeholder="+380000000000" />
                <form:errors path="telNumber" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="password" for="pass_input">Password</form:label>
                <form:input path="password" type="password" class="form-control" id="pass_input" placeholder="HardPass123" />
                <form:errors path="password" cssClass="error-1"/>
            </div>
            <button type="submit" class="btn btn-primary">Sign in</button>
        </form:form>
        <p class="p-alt">Already registered?</p>
        <a class="link-1" href="<c:url value=""/> ">Sign in</a>
    </div>
</div>
</body>
</html>
