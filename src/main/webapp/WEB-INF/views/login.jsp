<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
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

            <form:label path="tel_number">Name:</form:label>
            <form:input path="tel_number" />

            <form:label path="password">Password:</form:label>
            <form:password path="password"/>

        </fieldset>

        <footer>
            <input type="submit" class="btnLogin" value="Login" tabindex="4">
        </footer>

    </form:form>


</body>
</html>