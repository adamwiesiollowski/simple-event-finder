<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <title>Edytuj/Usuń miejsce</title>
</head>
<body>

<c:if test="${not empty message}">
    <div class="alert alert-info">${message}<br></div>
</c:if>

<table>
    <thead>
    <tr>
        <th>Nazwa</th>
        <th>Miasto</th>
        <th>Edytuj miejsce</th>
        <th>Usuń miejsce</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${places}" var="place">
        <tr>
            <td>${place.name}</td>
            <td>${place.city}</td>
            <td><a href="<c:url value="/admin/editplace"><c:param name="id" value="${place.id}"/></c:url>">Edytuj</a></td>
            <td><a href="<c:url value="/admin/deleteplace"><c:param name="id" value="${place.id}"/></c:url>"
                   onclick="return confirm('Czy na pewno chcesz usunąć miejsce ${place.name}?')">Usuń</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/views/pagination.jsp"/>

<a href="<c:url value="/admin"/>">Panel administratora</a>

</body>
</html>