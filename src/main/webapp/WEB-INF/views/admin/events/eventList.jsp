<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <title>Edytuj/Usuń wydarzenie</title>
</head>
<body>

<c:if test="${not empty message}">
    <div class="alert alert-info">${message}<br></div>
</c:if>

<table>
    <thead>
    <tr>
        <th>Data</th>
        <th>Kategoria</th>
        <th>Nazwa wydarzenia</th>
        <th>Miejsce</th>
        <th>Miejscowość</th>
        <th>Edytuj wydarzenie</th>
        <th>Usuń wydarzenie</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="event" items="${events}">
        <tr>
            <td>${event.dateRange}</td>
            <td>${event.category.name}</td>
            <td>${event.name}</td>
            <td>${event.place.name}</td>
            <td>${event.place.city}</td>
            <td><a href="<c:url value="/admin/editevent"><c:param name="id" value="${event.id}"/></c:url>">Edytuj</a></td>
            <td><a href="<c:url value="/admin/deleteevent"><c:param name="id" value="${event.id}"/></c:url>" onclick="return confirm('Czy na pewno chcesz usunąć wydarzenie ${event.name}?')">Usuń</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/views/pagination.jsp"/>

<a href="<c:url value="/admin"/>">Panel administratora</a>

</body>
</html>