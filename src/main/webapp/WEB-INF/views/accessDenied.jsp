<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"  crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/registration.css">
</head>
<body>
<div class="container1">
    <div class="btn-bar">
        <form action="<c:url value="/index"/>">
            <button type="submit" class="brn-2">Home</button>
        </form>
    </div>

    <div class="box">
        <div class="error-1">
            <span>${message}</span>
        </div>
    </div>
</div>

</body>
</html>
