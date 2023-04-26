<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty message}">
    <div class="alert alert-info">${message}<br></div>
</c:if>

<form:form method="post" modelAttribute="category">
    Nazwa kategorii: <form:input path="name"/>
    <br><form:errors path="name"/><br><br>
    <input type="submit" value="Zatwierdź">
</form:form>

<a href="<c:url value="/admin"/>">Panel administratora</a>