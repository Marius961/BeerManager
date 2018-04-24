<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/orders.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <a class="navbar-brand" href="<c:url value="/"/>">Product manager</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/>">Home<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main class="background">
    <div class="main-div">
        <div class="content-box register-size">
            <form:form modelAttribute="user" method="post" action="/register">
                <div class="form-group">
                    <form:label path="username" for="username_input">Full name</form:label>
                    <form:input path="username" type="text" class="form-control" id="username_input" placeholder="Your username"/>
                </div>
                <div class="form-group">
                    <form:label path="fullName" for="name_input">Full name</form:label>
                    <form:input path="fullName" type="text" class="form-control" id="name_input" placeholder="Your full name"/>
                </div>
                <div class="form-group">
                    <form:label path="companyName" for="company_name">Firm name</form:label>
                    <form:input path="companyName" type="text" class="form-control" id="company_name" placeholder="Your firm name"/>
                </div>
                <div class="form-group">
                    <form:label path="companyAddress" for="companyAddress">Company address</form:label>
                    <form:input path="companyAddress" type="text" class="form-control" id="companyAddress" placeholder="Your company address"/>
                </div>
                <div class="form-group">
                    <form:label path="email" for="email">Email</form:label>
                    <form:input path="email" type="email" class="form-control" id="email" placeholder="Your email address"/>
                </div>
                <div class="form-group">
                    <form:label path="email" for="tel_input">Tel. number</form:label>
                    <form:input path="telNumber" type="tel" class="form-control" id="tel_input" placeholder="Your tel number"/>
                </div>
                <div class="form-group">
                    <form:label path="password" for="password">Password</form:label>
                    <form:input path="password" type="password" class="form-control" id="password" placeholder="Your password"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
    </div>
</main>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
