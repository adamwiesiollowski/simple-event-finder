<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Panel administratora</title>
</head>
<body>

<c:if test="${not empty message}">
    <div class="alert alert-info">${message}<br></div>
</c:if>

<sec:authorize access="isAuthenticated()">
    <nav>Witaj, <span><sec:authentication property="principal.username"/></span></nav>
</sec:authorize>

<h1><a href="/">Simple Event Finder</a></h1>

<a href="<c:url value="/admin/addevent"/>">Dodaj wydarzenie</a><br>
<a href="<c:url value="/admin/eventlist"/>">Edytuj/Usuń wydarzenie</a><br><br>
<a href="<c:url value="/admin/addplace"/>">Dodaj miejsce</a><br>
<a href="<c:url value="/admin/placelist"/>">Edytuj/Usuń miejsce</a><br><br>
<a href="<c:url value="/admin/addcategory"/>">Dodaj kategorię</a><br>
<a href="<c:url value="/admin/categorylist"/>">Edytuj/Usuń kategorię</a>

<sec:authorize access="hasRole('ADMIN')">
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Wyloguj">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>

</body>
</html>