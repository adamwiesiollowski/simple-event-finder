<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Simple Event Finder</title>
</head>
<body>

<a href="<c:url value="/filmfest">
<c:param name="id" value="1"/>
</c:url>">Festiwale filmowe</a>

<a href="<c:url value="/musicfest">
<c:param name="id" value="2"/>
</c:url>">Festiwale muzyczne</a>

<a href="<c:url value="/concerts">
<c:param name="id" value="3"/>
</c:url>">Koncerty</a>

<a href="<c:url value="/weekendevents">
</c:url>">Wydarzenia w weekendy</a>

<h1><a href="/">Simple Event Finder</a></h1>

<jsp:include page="pastEventList.jsp"/>
<jsp:include page="/WEB-INF/views/pagination.jsp"/>

<a href="<c:url value="/pastevents">
</c:url>">Minione wydarzenia</a>

<a href="<c:url value="/admin"/>">Panel administratora</a>

</body>
</html>