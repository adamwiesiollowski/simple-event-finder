<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Dostęp zabroniony</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    <nav>Witaj, <span><sec:authentication property="principal.username"/></span></nav>
</sec:authorize>

<h1><a href="/">Simple Event Finder</a></h1>

Dostęp zabroniony!

<sec:authorize access="isAuthenticated()">
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Wyloguj">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>

</body>
</html>