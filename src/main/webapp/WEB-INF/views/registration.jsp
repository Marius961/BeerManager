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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
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
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/"/>">Головна<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main class="background">
    <div class="main-div">
        <div class="content-box register-size">
            <form>
                <div class="form-group input-group-1">
                    <label for="username">Логін</label>
                    <input type="text" class="form-control" id="username" placeholder="Ваш логін латиницею">
                    <small id="emailHelp" class="form-text text-muted">Використовується для входу на сайт.</small>
                </div>
                <div class="form-group input-group-1">
                    <label for="fullName">П.І.Б.</label>
                    <input type="text" class="form-control" id="fullName" placeholder="введіть ваше П.І.Б.">
                </div>
                <div class="form-group input-group-1">
                    <label for="companyName">Назва компанії</label>
                    <input type="text" class="form-control" id="companyName" placeholder="Введіть назву вашої компанії">
                </div>
                <div class="form-group input-group-1">
                    <label for="companyAddress">Адреса вашої компанії</label>
                    <input type="text" class="form-control" id="companyAddress" placeholder="Введіть адресу вашої компанії або філіалу">
                    <small class="form-text text-muted">УВАГА - замовлені товари будуть доставлені на дану адресу.</small>
                </div>
                <div class="form-group input-group-1">
                    <label for="email">Ел. адреса</label>
                    <input type="email" class="form-control" id="email" placeholder="Введіть Вашу електронну адресу">
                </div>
                <div class="form-group input-group-1">
                    <label for="telNumber">Номер мобільного телефону</label>
                    <input type="tel" class="form-control" id="telNumber" placeholder="Введіть номер Вашого мобільного телефону">
                </div>
                <div class="form-group input-group-1">
                    <label for="password">Пароль</label>
                    <input type="password" class="form-control" id="password" placeholder="мінімум 8 латинських букв та цифр">
                </div>
                <div class="form-group input-group-1">
                    <label for="passwordConfirm">Підтвердіть пароль</label>
                    <input type="password" class="form-control" id="passwordConfirm" placeholder="Будь ласска введіть пароль щераз">
                </div>
                <button type="button" class="btn btn-primary" onclick="validateAndSend(this.form)" style="margin: 0.5%">Зареєструватись</button>
            </form>
        </div>
    </div>
</main>
<script src="<%=request.getContextPath()%>/resources/js/registration.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>
</html>
