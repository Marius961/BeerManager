<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Профіль користувача</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/orders.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/animations.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
                    <a class="nav-link" href="<c:url value="/users"/>">Користувачі<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/all-orders"/>">Замовлення<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
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
<main class="container container-background">
    <div class="row mt-3">
        <div class="col p-4">
            <h4>Інформація про користувача</h4>
            <h5 class="mt-3">Основна інформація</h5>
            <hr>
            <div class="row">
                <div class="col-12 col-sm-6 col-lg-4 p-2">
                    <div class="row">
                        <div class="col-12">
                            <h6>Логін</h6>
                        </div>
                        <div class="col-12">
                            <span>${user.username}</span>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-4 p-2">
                    <div class="row">
                        <div class="col-12">
                            <h6>П.І.Б.</h6>
                        </div>
                        <div class="col-12">
                            <span>${user.fullName}</span>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-4 p-2">
                    <div class="row">
                        <div class="col-12">
                            <h6>Ел. адреса</h6>
                        </div>
                        <div class="col-12">
                            <span>${user.email}</span>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-4 p-2">
                    <div class="row">
                        <div class="col-12">
                            <h6>Номер телефону</h6>
                        </div>
                        <div class="col-12">
                            <span>${user.telNumber}</span>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-4 p-2">
                    <div class="row">
                        <div class="col-12">
                            <h6>ID профілю</h6>
                        </div>
                        <div class="col-12">
                            <span>${user.id}</span>
                        </div>
                    </div>
                </div>
            </div>
            <h5 class="mt-3">Інформація про компанію</h5>
            <hr>
            <div class="row">
                <div class="col-12 col-sm-6 col-lg-4 p-2">
                    <div class="row">
                        <div class="col-12">
                            <h6>Назва компанії</h6>
                        </div>
                        <div class="col-12">
                            <span>${user.companyName}</span>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-4 p-2">
                    <div class="row">
                        <div class="col-12">
                            <h6>Адреса компанії</h6>
                        </div>
                        <div class="col-12">
                            <span>${user.companyAddress}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
