<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <thead>
    <tr>
        <th>Data</th>
        <th>Dzień tygodnia</th>
        <th>Nazwa wydarzenia</th>
        <th>Miejsce</th>
        <th>Miejscowość</th>
        <th>Link do wydarzenia</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="event" items="${events}">
        <tr>
            <td>${event.dateRange}</td>
            <td>${event.dayOfWeek}</td>
            <td>
                <a href="<c:url value="/pasteventsbyname">
                    <c:param name="name" value="${event.name}"/>
                </c:url>">${event.name}</a>
            </td>
            <td>
                <a href="<c:url value="/pasteventsbyplace">
                    <c:param name="name" value="${event.place.name}"/>
                    <c:param name="city" value="${event.place.city}"/>
                </c:url>">${event.place.name}</a>
            </td>
            <td>
                <a href="<c:url value="/pasteventsbycity">
                    <c:param name="city" value="${event.place.city}"/>
                </c:url>">${event.place.city}</a>
            </td>
            <td><a href="${event.link}" target="_blank">Więcej informacji</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>