<%@ page contentType="text/html;charset=UTF-8"%>
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
        </div>
    </nav>
</header>
<main class="container-fluid">
    <div class="row">
        <div class="col">
            <div class="row jumbotron">
                <div class="row">
                    <div class="col-12">
                        <h1 class="display-4">Вітаємо у Product manager!</h1>
                    </div>
                    <div class="col">
                        <p class="lead">Це простий інструмент для менеджменту замовлень!</p>
                        <hr class="my-4">
                    </div>
                    <div class="w-100"></div>
                    <div class="col">
                        <p>Ми використовуєм просту та зручну систему контролю та створення замовлень</p>
                    </div>
                    <div class="w-100"></div>
                    <div class="col-12 col-sm-auto p-1 text-center">
                        <button class="btn btn-dark" onclick="location.href='/registration'">Зареєструватись</button>
                    </div>
                    <div class="col-12 col-sm-auto text-center">
                        <small class="small-1">або</small>
                    </div>
                    <div class="col-12 col-sm-auto p-1 text-center">
                        <button class="btn btn-dark" onclick="location.href='login'">Увійти</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>
