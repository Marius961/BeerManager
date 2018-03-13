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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/index.css"/>">
</head>
<body>
<div class="container-1">
    <div class="btn-bar">
        <form action="<c:url value="/"/>">
            <button type="submit" class="brn-2">Sign in</button>
        </form>
    </div>
    <div class="content-2">
        <h1 class="header">Sing up and make your orded now!</h1>
        <form action="<c:url value="/registration"/>">
            <button type="submit" class="brn-1">Sign up</button>
        </form>
    </div>
</div>
<main class="container-2">
    <div class="content-3">
        <div class="info">
            <h1 class="info-header">HTML</h1>
            <p class="info-p">
                is the standard markup language for creating web pages
                and web applications. With Cascading Style Sheets (CSS)
                and JavaScript, it forms a triad of cornerstone technologies
                for the World Wide Web
            </p>
        </div>
        <div class="info">
            <h1 class="info-header">CSS</h1>
            <p class="info-p">
                is a style sheet language used for describing the presentation
                of a document written in a markup language. Although most often
                used to set the visual style of web pages and user interfaces
                written in HTML and XHTML, the language can be applied to any XML document
            </p>
        </div>
        <div class="info">
            <h1 class="info-header">JavaScript </h1>
            <p class="info-p">
                often abbreviated as JS, is a high-level, interpreted programming language.
                It is a language which is also characterized as dynamic, weakly typed,
                prototype-based and multi-paradigm. Alongside HTML and CSS, JavaScript
                is one of the three core technologies of World Wide Web content engineering.
            </p>
        </div>
    </div>
</main>
</body>
</html>
