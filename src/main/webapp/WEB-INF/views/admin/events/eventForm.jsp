<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty message}">
    <div class="alert alert-info">${message}<br></div>
</c:if>

<form:form method="post" modelAttribute="event">
    Kategoria: <form:select path="category" items="${categories}" itemLabel="name" itemValue="id"/><br><br><br>
    Nazwa wydarzenia: <form:input path="name"/>
    <br><form:errors path="name"/><br>
    Link do wydarzenia: <form:input path="link"/>
    <br><form:errors path="link"/><br><br><br>
    Wybierz miejsce wydarzenia: <form:select path="place.id">
    <br><form:options items="${places}" itemValue="id" itemLabel="nameAndCity"/></form:select><br>
    Jeśli miejsca wydarzenia nie ma na liście: <a href="<c:url value="/admin/addplace"/>">Kliknij, aby dodać nowe
    miejsce</a><br><br><br><br><br>
    Data początkowa wydarzenia: <form:input path="dateBeginning" type="date"/>
    <br><form:errors path="dateBeginning"/>
    <form:errors path="isDateBeginningBeforeDateEnd"/><br>
    Data końcowa wydarzenia:
    <form:input path="dateEnd" type="date"/>
    <br><span class="italic">wypełnij w przypadku wydarzeń trwających dłużej niż jeden dzień</span>
    <br><form:errors path="dateEnd"/><br><br>
    <input type="submit" value="Zatwierdź">
</form:form>

<a href="<c:url value="/admin"/>">Panel administratora</a>