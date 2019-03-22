<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${countPage > 1}">
    <div class="pagination-div">
        <ul class="pagination">
            <c:choose>
                <c:when test="${not empty requestScope.category}">
                    <c:forEach var="page" begin="1" end="${countPage}" >
                        <li><a class="current-page" href="<c:url value="/totalizator/${requestScope.command}/${requestScope.category}?page=${page}"/>">${page}</a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="page" begin="1" end="${countPage}" >
                        <li><a class="current-page" href="<c:url value="/totalizator/${requestScope.command}?page=${page}"/>">${page}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</c:if>
