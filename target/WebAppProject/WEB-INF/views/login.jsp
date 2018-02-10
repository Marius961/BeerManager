<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>

<body>
    <form:form method="POST" modelAttribute="user" action="check-user" class="box login">

        <fieldset class="boxBody">

            <form:label path="name">Name:</form:label>
            <form:input path="name" />

            <form:label path="password">Password:</form:label>
            <form:password path="password"/>

        </fieldset>

        <footer>
            <label><form:checkbox path="admin" tabindex="3"/>Admin</label>
            <input type="submit" class="btnLogin" value="Login" tabindex="4">
        </footer>

    </form:form>


</body>
</html>