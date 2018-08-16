<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Мої замовлення</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/orders.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/animations.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
    <div class="row">
        <div class="col-12 col-sm-6 col-md-3 col-lg-2 col-xl-2">
            <div class="tab" id="tab1" onclick="loadUserCurrentDateOrders(this)"><span class="btn-label p-sm-3 p-3">Сьогодні</span></div>
        </div>
        <div class="col-12 col-sm-6 col-md-3 col-lg-2 col-xl-2">
            <div class="tab" onclick="loadUserOtherOrders(this)"><span class="btn-label p-sm-3 p-3">Інші дні</span></div>
        </div>
        <div class="col-12 col-sm-12 col-md-3 col-lg-2 col-xl-2 offset-md-3 offset-lg-6 offset-xl-6 pt-1">
            <button type="button" class="col-12 btn btn-light p-1" onclick="location.href='/order/form'">Створити замовлення</button>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col" id="orders-container">
            <div id="orders">
                <%--<div class="row" id="2018-08-25">--%>
                    <%--<div class="col-12 p-0 mb-2">--%>
                        <%--<h2 class="date-header p-2">25 Серпня 2018</h2>--%>
                    <%--</div>--%>
                    <%--<div class="col" id="list-2018-08-25">--%>

                    <%--</div>--%>
                <%--</div>--%>

            </div>
            <%--<div class="row mb-3 order-box" id="elem49">--%>
                <%--<div class="col-12">--%>
                    <%--<div class="row order-header p-1">--%>
                        <%--<div class="col-6 text-left">Дата створення: <span class="font-weight-bold">2018-08-14</span> </div>--%>
                        <%--<div class="col-6 text-right">Замовлення №<span class="font-weight-bold">49</span></div>--%>
                        <%--<div class="col text-left">Час замовлення:<span class="font-weight-bold">14:16:00</span></div>--%>
                    <%--</div>--%>
                    <%--<div class="row order-header p-1 mt-1 text-center font-weight-bold">--%>
                        <%--<div class="col-9">Назва товару</div>--%>
                        <%--<div class="col-3">Об'єм</div>--%>
                    <%--</div>--%>
                    <%--<div class="row" id="items-12">--%>
                        <%--<div class="col">--%>
                            <%--<div class="row p-1 mt-1 item-1">--%>
                                <%--<div class="col-9">Чернігівське</div>--%>
                                <%--<div class="col-3 text-center">50 літрів</div>--%>
                            <%--</div>--%>
                            <%--<div class="row p-1 mt-1 item-1">--%>
                                <%--<div class="col-9">Garage</div>--%>
                                <%--<div class="col-3 text-center">60 літрів</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col p-1 pl-2 comment-col">--%>
                    <%--<h5>Коментар:</h5>--%>
                    <%--<p>Ваш коментар до замовлення буде міститись тут</p>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>
</main>
<script src="<%=request.getContextPath()%>/resources/js/orders.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
