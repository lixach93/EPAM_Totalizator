<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<body>

<div class="navbar own-container">
    <c:choose>
        <c:when test="${not empty requestScope.permissionMessage}">
            <div class="alert alert-warning">
                <strong>Warning!</strong>
                <h2><fmt:message bundle="${loc}" key="${requestScope.permissionMessage}"/></h2>
            </div>
        </c:when>
        <c:otherwise>
            <c:set var="redirect" value="${pageContext.request.contextPath}/totalizator?command=showModeratorPage" scope="application"/>
            <div class="vertical-menu">
                <a  class="${requestScope.activeOne}" href="${pageContext.request.contextPath}/totalizator?command=chooseModeratorPage&page=event">
                    <fmt:message bundle="${loc}" key="link.event"/>
                </a>
            </div>
            <div class="right-container">
                <div class="top-block">
                    <c:choose>
                        <c:when test="${requestScope.block eq 'event'}">
                            <jsp:include page="../fragments/event.jsp"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="down-block">
                    <c:choose>
                        <c:when test="${requestScope.action eq 'activeEvent'}">
                            <jsp:include page="../fragments/activeEvent.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.action eq 'resultBetting'}">
                            <jsp:include page="../fragments/resultBetting.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.action eq 'createCompetition'}">
                            <jsp:include page="../fragments/createCompetition.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.action eq 'closeCompetition'}">
                            <jsp:include page="../fragments/closeCompetition.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.action eq 'closeCompRate'}">
                            <jsp:include page="../fragments/closeCompRate.jsp"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
