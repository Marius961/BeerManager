<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Реєстрація</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/orders.css">
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
                    <a class="nav-link" href="<c:url value="/"/>">Головна<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main class="container">
    <div class="row">
        <div class="col-10 col-sm-7 col-md-6 col-lg-5 col-xl-4 mt-5 mb-2 mx-auto container-background">
           <div class="row">
               <div class="col-12 p-3" id="loginForm">
                   <c:if test="${not empty error}">
                       <div class="row no-gutters">
                           <div class="col">
                               <div class="alert alert-danger" role="alert">
                                       ${error}
                               </div>
                           </div>
                       </div>
                   </c:if>
                   <h4>Вхід</h4>
                   <hr>
                   <form action="<c:url value="/security_check"/>" name="form_login" method="post" onsubmit="return validateForm(this)">
                       <div class="form-group">
                           <label for="username">Логін</label>
                           <input type="text" name="username" class="form-control" id="username">
                       </div>
                       <div class="form-group">
                           <label for="password">Пароль</label>
                           <input type="password" name="password" class="form-control" id="password">
                       </div>
                       <div class="form-group form-check">
                           <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
                           <label class="form-check-label" for="rememberMe">Запам'ятати мене</label>
                       </div>
                       <div class="row">
                           <div class="col-3 mt-2">
                               <button type="submit" class="btn btn-primary">Увійти</button>
                           </div>
                       </div>
                   </form>
               </div>
               <div class="col p-3 form-link">
                   <span>Немає аккаунту? <a href="<c:url value="/registration"/>">Зареєструватись</a> </span>
               </div>
           </div>
        </div>
    </div>
</main>



<script src="<%=request.getContextPath()%>/resources/js/loginForm.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
