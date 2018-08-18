<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Створити замовлення</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/orders.css">
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
                    <a class="nav-link" href="<c:url value="/orders"/>">Мої замовлення<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Аккаунт
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="<c:url value="/my-profile"/>">Мій профіль</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="<c:url value="/logout"/>">Вийти з аккаунту</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main class="container">
    <div class="row container-background mt-3 p-2">
        <div class="col">
            <a class="h2">Замовлення:</a>
        </div>
        <div class="w-100"></div>
        <div class="col pt-2">
            <form:form action="/orders/process" method="post" modelAttribute="order" id="orderForm">
                <a class="h5">Товари:</a>
                <div class="row p-1 m-1">
                    <c:forEach items="${order.orderItems}" var="item" varStatus="status">
                        <div class="col-12 col-md-6 col-xl-4 item-2 no-gutters">
                            <div class="row">
                                <div class="col-12 col-lg-9 p-2">
                                    <h5>${item.product.name}</h5>
                                    <p class="text-muted">${item.product.description}</p>
                                </div>
                                <div class="col-12 col-lg-3 p-2" style="border-left: 0.3mm solid rgba(0, 0, 0, 0.07)">
                                    <span>Об'єм (л):</span>
                                    <form:input path="orderItems[${status.index}].volume" type="number" class="form-control" value="0"/>
                                    <form:hidden path="orderItems[${status.index}].productId" value="${item.productId}"/>
                                    <form:hidden path="userId" value="${currentUserId}"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="row">
                    <div class="col-6">
                        <form:label path="comment" for="commentTextArea">Коментар:</form:label>
                        <form:textarea path="comment" class="form-control" id="commentTextArea" rows="3"/>
                    </div>
                    <div class="col-4">
                        <form:label path="execDate" for="execDate">Дата виконання:</form:label>
                        <form:input path="execDate" class="form-control" id="execDate" type="date"/>
                    </div>
                    <div class="w-100"></div>
                    <div class="col-4 mt-3 ml-1">
                        <button type="button" class="btn btn-primary" onclick="validateOrderForm()">Замовити</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

    <%--<div class="main-div">--%>
        <%--<div class="content-box">--%>
            <%--<div class="content-div">--%>

            <%--</div>--%>
            <%--<form:form action="/orders/process" method="post" modelAttribute="order" id="orderForm">--%>
                <%--<c:forEach items="${order.orderItems}" var="item" varStatus="status">--%>
                    <%--<div class="prod-order inline background order-info">--%>
                    <%--<span class="order-info">${item.product.description}</span>--%>
                        <%--<table class="table-order">--%>
                            <%--<tr>--%>
                                <%--<td class="table-td"><h4 class="prod-name">${item.product.name}:</h4></td>--%>
                                <%--<td class="table-td-vol-input">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<form:input path="orderItems[${status.index}].volume" type="number" class="form-control left" id="volInput${status.index}" value="0"/>--%>
                                        <%--<form:hidden path="orderItems[${status.index}].productId" value="${item.productId}"/>--%>
                                        <%--<form:hidden path="userId" value="${currentUserId}"/>--%>
                                    <%--</div>--%>
                                <%--</td>--%>
                                <%--<td class="table-td-vol"><span class="barrels-vol">літрів</span></td>--%>
                            <%--</tr>--%>
                        <%--</table>--%>
                    <%--</div>--%>
                <%--</c:forEach>--%>
                <%--<br>--%>
                <%--<div class="form-group">--%>
                    <%--<form:label path="comment" for="commentTextArea">Коментар:</form:label>--%>
                    <%--<form:textarea path="comment" class="form-control" id="commentTextArea" rows="3"/>--%>
                <%--</div>--%>
                <%--<div class="form-group" style="width: 25%">--%>
                    <%--<form:label path="execDate" for="execDate">Дата виконання:</form:label>--%>
                    <%--<form:input path="execDate" class="form-control" id="execDate" type="date"/>--%>
                <%--</div>--%>
                <%--<br>--%>
                <%--<button type="button" class="btn btn-primary" onclick="validateOrderForm()">Замовити</button>--%>
            <%--</form:form>--%>
        <%--</div>--%>
    <%--</div>--%>
</main>

<script src="<%=request.getContextPath()%>/resources/js/registration.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
