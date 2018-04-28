<%--
  Created by IntelliJ IDEA.
  User: mapiy
  Date: 28.04.2018
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <span class="navbar-brand mb-0 h1">Product manager</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">About us<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main class="background">
    <div class="main-div">

        <div class="content-box">
            <h1 class="welcome-h1">Welcome to product manager</h1>
            <div class="table-div">
                <div class="table-cell-div">
                    <button class="btn btn-dark table-cell-btn" onclick="location.href='/registration'">Register</button>
                </div>
                <div class="table-cell-div">
                    <button class="btn btn-dark table-cell-btn" onclick="location.href='login'">Login</button>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
