<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/orders.css">
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
                    <a class="nav-link" href="<c:url value="/${currentUser.username}/orders"/>">My orders<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/${currentUser.username}/my-profile"/>">My account</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main class="background">
    <div class="main-div">
        <div class="content-box">
            <div class="content-div">
                <h2 class="header-1" id="order-header">Order:</h2>
            </div>
            <form:form action="/orders/process" method="post" modelAttribute="order" id="orderForm">
                <c:forEach items="${order.orderItems}" var="item" varStatus="status">
                    <div class="prod-order inline background order-info">
                    <span class="order-info">${item.product.description}</span>
                        <table class="table-order">
                            <tr>
                                <td class="table-td"><h4 class="prod-name">${item.product.name}:</h4></td>
                                <td class="table-td-vol-input">
                                    <div class="form-group">
                                        <form:input path="orderItems[${status.index}].volume" type="number" class="form-control left" id="volInput${status.index}" value="0" step="1"/>
                                        <form:hidden path="orderItems[${status.index}].productId" value="${item.productId}"/>
                                        <form:hidden path="userId" value="${currentUser.id}"/>
                                    </div>
                                </td>
                                <td class="table-td-vol"><span class="barrels-vol">liters</span></td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
                <br>
                <div class="form-group">
                    <form:label path="comment" for="commentTextArea">Comment:</form:label>
                    <form:textarea path="comment" class="form-control" id="commentTextArea" rows="3"/>
                </div>
                <div class="form-group" style="width: 25%">
                    <form:label path="execDate" for="execDate">Execution date:</form:label>
                    <form:input path="execDate" class="form-control" id="execDate" type="date"/>
                </div>
                <br>
                <button type="button" class="btn btn-primary" onclick="validateOrderForm()">Submit</button>
            </form:form>
        </div>
    </div>
</main>

<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>
