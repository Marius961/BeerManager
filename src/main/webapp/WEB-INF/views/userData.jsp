<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/${currentUserName}/users/list"/>">Users<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main class="background">
    <div class="main-div">
        <div class="content-box">
            <h2 class="header-1">Full name: ${user.fullName}</h2>
            <h6 class="details-1">Email: ${user.email}</h6>
            <h6 class="details-1">Phone number: ${user.telNumber}</h6>
            <h6 class="details-1">Company name: ${user.companyName}</h6>
            <h6 class="details-1">Company address: ${user.companyAddress}</h6>
            <c:if test="${currentUserName == user.username}">
                <button class="btn btn-info" onclick="location.href='/logout'">Logout</button>
            </c:if>
        </div>
        <c:if test="${empty orders}">
            <h1>You have not any orders :(</h1>
        </c:if>
        <c:if test="${not empty orders}">
            <c:forEach var="order" items="${orders}">
                <div class="content-box" id="orderBox${order.id}">
                    <div class="product-block">
                        <p class="details-1 order-info">
                            Order №: <span class="info">${order.id}</span>
                        </p>
                        <p class="details-1 inline order-info background">
                            Creation date: <span class="info">${order.creationDate}</span>
                        </p>
                        <p class="details-1 inline order-info background">
                            Creation time: <span class="info">${order.creationTime}</span>
                        </p>
                        <p class="details-1 inline order-info background">
                            Execution date: <span class="info">${order.execDate}</span>
                        </p>
                        <p class="details-1 inline order-info background">
                            Price: <span class="info">${order.price}</span>
                        </p>

                        <div class="prod-order inline background order-info">
                            <table class="table-order">
                                <c:forEach var="orderItem" items="${order.orderItems}">
                                    <tr>
                                        <td class="table-td"><h4 class="prod-name">${orderItem.product.name}:</h4></td>
                                        <td class="table-td-vol"><span class="barrel-qt">${orderItem.volume}</span></td>
                                        <td class="table-td-vol-name"><span class="barrels-vol">liters</span></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="btn-bar">
                            <button type="button" class="btn btn-danger btn-right" onclick="removeOrder(${order.id})">Cancel</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</main>
<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
