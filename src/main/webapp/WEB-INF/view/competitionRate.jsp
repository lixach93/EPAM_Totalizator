<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container">
        <noscript>
            <h2>Your browser does not support JavaScript!</h2>
            <style>
                .no-js{
                    display: block !important;
                }
            </style>
        </noscript>
        <c:set var="footballCounter" value="${1}" scope="request"/>
        <c:set var="hockeyCounter" value="${1}" scope="request"/>
        <c:set var="basketballCounter" value="${1}" scope="request"/>
        <c:forEach var="competitionRate" items="${rates}">
            <c:set var ="category" value="${competitionRate.competition.firstOpponent.league.category.nameCategory}" scope="request"/>
            <c:set var="rate" value="${competitionRate}" scope="request"/>
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