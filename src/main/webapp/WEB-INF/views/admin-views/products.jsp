<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Товари</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/products.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/animations.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
</head>
<body class="background">
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <a class="navbar-brand" href="#">Product manager</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/users"/>">Користувачі<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/all-orders"/>">Замовлення<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/products"/>">Товари<span class="sr-only">(current)</span></a>
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
    <div class="row m-2 sticky-top" style="background: #f1f1f1">
        <div class="col-12 col-sm-2 col-md-1 col-lg-1">
            <div class="tab" id="tab1" onclick="showProductsList(this)"><span class="label-text-1">Всі</span></div>
        </div>
        <div class="col-12 col-sm-3 col-md-2 col-lg-2 col-xl-2">
            <div class="tab" onclick="showUnblockedProducts(this)"><span class="label-text-1">Активні</span></div>
        </div>
        <div class="col-12 col-sm-3 col-md-3 col-lg-2">
            <div class="tab" onclick="showBlockedProductList(this)"><span class="label-text-1">Не активні</span></div>
        </div>
        <div class="col-12 col-sm-6 col-md-2 col-lg-2 pt-1 order-sm-11">
            <button type="button" class="col-12 btn btn-light p-1 font-2" onclick="refreshData()">Оновити</button>
        </div>
        <div class="col-12 col-sm-6 col-md-4 col-lg-2 pt-1 order-sm-12">
            <button type="button" class="col-12 btn btn-light p-1 font-2" onclick="addForm()">Додати товар</button>
        </div>
        <div class="col-md-12 col-lg-3 col-xl-3 order-md-last mt-1" id="search" style="transition: 100ms;">
            <input type="text" oninput="search(this.value)" class="form-control font-2" placeholder="Пошук по продукції" aria-label="Search by products" aria-describedby="button-addon4">
        </div>
    </div>
    <div class="main-div">
    </div>
</main>
<script src="<%=request.getContextPath()%>/resources/js/products.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
