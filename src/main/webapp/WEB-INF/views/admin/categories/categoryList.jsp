<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <title>Edytuj/Usuń kategorię</title>
</head>
<body>

<c:if test="${not empty message}">
    <div class="alert alert-info">${message}<br></div>
</c:if>

<table>
    <thead>
    <tr>
        <th>Nazwa</th>
        <th>Edytuj kategorię</th>
        <th>Usuń kategorię</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.name}</td>
            <td><a href="<c:url value="/admin/editcategory"><c:param name="id" value="${category.id}"/></c:url>">Edytuj</a></td>
            <td><a href="<c:url value="/admin/deletecategory"><c:param name="id" value="${category.id}"/></c:url>"
                   onclick="return confirm('Czy na pewno chcesz usunąć kategorię ${category.name}?')">Usuń</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/views/pagination.jsp"/>

<a href="<c:url value="/admin"/>">Panel administratora</a>

</body>
</html>