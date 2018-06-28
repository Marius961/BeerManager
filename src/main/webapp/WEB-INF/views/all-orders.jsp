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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/animations.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <a class="navbar-brand" href="#">Product manager</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/orders"/>">My orders<span class="sr-only">(current)</span></a>
                </li>
                <%--<li class="nav-item">--%>
                    <%--<a class="nav-link" href="<c:url value="/${currentUserName}/my-profile"/>">My account</a>--%>
                <%--</li>--%>
            </ul>
        </div>
    </nav>
</header>
<main class="background">
    <div class="main-div">
        <button type="button" class="btn btn-primary btn-sm right" onclick="location.href='/order/form'">New order</button>
        <H4>My orders</H4>
        <div class="container-div">
            <div class="group-box" onclick="displayHideOrderGroup('#currentDayOrders')">
                <h5 class="group-header">Current day</h5>
                <div id="currentDayOrders">
                </div>
            </div>
            <div class="group-box" onclick="displayHideOrderGroup('#otherOrders')">
                <h5 class="group-header">Other orders</h5>
                <div id="otherOrders" style="display: none">
                </div>
            </div>
        </div>
    </div>
</main>
<script src="<%=request.getContextPath()%>/resources/js/orders.js"></script>
</body>
</html>
