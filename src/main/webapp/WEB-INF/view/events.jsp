<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container own-container">
        <noscript>
            <h2>Your browser does not support JavaScript!</h2>
            <style>
                .no-js{
                    display: block !important;
                }
            </style>
        </noscript>
        <c:if test="${not empty sessionScope.status}">
            <p><fmt:message bundle="${loc}" key="${sessionScope.status}" /></p>
            <c:remove var="status" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <p><fmt:message bundle="${loc}" key="${sessionScope.errorMessage}" /></p>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <c:set var="footballCounter" value="${1}" scope="request"/>
        <c:set var="hockeyCounter" value="${1}" scope="request"/>
        <c:set var="basketballCounter" value="${1}" scope="request"/>
        <c:forEach var="event" items="${requestScope.events}">
            <c:set var ="category" value="${event.competition.firstTeam.league.category.nameCategory}" scope="request"/>
            <c:set var="event" value="${event}" scope="request"/>
            <c:if test="${category eq 'football'}">
                <jsp:include page="../fragments/rates.jsp"/>
            </c:if>
            <c:if test="${category eq 'hockey'}">
                <jsp:include page="../fragments/rates.jsp"/>
            </c:if>
            <c:if test="${category eq'basketball'}">
                <jsp:include page="../fragments/rates.jsp"/>
            </c:if>
        </c:forEach>
    </div>
</body>
</html>