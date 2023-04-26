<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<div class="pagination">--%>
<%--    Strona ${currentPage + 1} z ${totalPages}<br>--%>
<%--    <c:if test="${currentPage > 0}">--%>
<%--        <a href="<c:url value="${paginationUrl}">--%>
<%--            <c:param name="page" value="${currentPage - 1}" />--%>
<%--            <c:param name="size" value="${pageSize}" />--%>
<%--        </c:url>">&laquo; Poprzednia strona</a>--%>
<%--    </c:if>--%>
<%--    <c:if test="${currentPage < totalPages - 1}">--%>
<%--        <a href="<c:url value="${paginationUrl}">--%>
<%--            <c:param name="page" value="${currentPage + 1}" />--%>
<%--            <c:param name="size" value="${pageSize}" />--%>
<%--        </c:url>">Następna strona &raquo;</a>--%>
<%--    </c:if>--%>
<%--</div>--%>

<div class="pagination">
    Strona ${currentPage + 1} z ${totalPages}<br>
    <c:if test="${currentPage > 0}">
        <a href="#" onclick="previousPage('${paginationUrl}', ${currentPage}, ${pageSize})">&laquo; Poprzednia strona</a>
    </c:if>
    <c:if test="${currentPage < totalPages - 1}">
        <a href="#" onclick="nextPage('${paginationUrl}', ${currentPage}, ${pageSize})">Następna strona &raquo;</a>
    </c:if>
</div>

<script>
    function previousPage(paginationUrl, currentPage, pageSize) {
        let url = window.location.href.split('?')[0];
        let params = new URLSearchParams(window.location.search);
        params.set('page', currentPage - 1);
        params.set('size', pageSize);
        window.location.href = url + '?' + params.toString();
    }

    function nextPage(paginationUrl, currentPage, pageSize) {
        let url = window.location.href.split('?')[0];
        let params = new URLSearchParams(window.location.search);
        params.set('page', currentPage + 1);
        params.set('size', pageSize);
        window.location.href = url + '?' + params.toString();
    }
</script>
<br><br>