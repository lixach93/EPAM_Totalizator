<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <c:set var="redirect" value="${pageContext.request.contextPath}/totalizator?command=showAdminPage" scope="application"/>
                <div class="vertical-menu">
                    <a  class="${requestScope.activeOne}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=league">
                        <fmt:message bundle="${loc}" key="span.league" />
                    </a>
                    <a class="${requestScope.activeTwo}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=team">
                        <fmt:message bundle="${loc}" key="span.team" />
                    </a>
                    <a class="${requestScope.activeThree}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=competition">Competition</a>
                    <a class="${requestScope.activeFour}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=rate">Rate</a>

                </div>
                <div class="right-container">
                    <div class="top-block">
                        <c:choose>
                            <c:when test="${requestScope.block eq 'league'}">
                                <jsp:include page="../fragments/league.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.block eq 'team'}">
                                <jsp:include page="../fragments/team.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.block eq 'competition'}">
                            <jsp:include page="../fragments/competition.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.block eq 'rate'}">
                                <jsp:include page="../fragments/rate.jsp"/>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="down-block">
                        <c:choose>
                            <c:when test="${requestScope.action eq 'createLeague'}">
                                <jsp:include page="../fragments/createLeague.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.action eq 'createTeam'}">
                                <jsp:include page="../fragments/createTeam.jsp"/>
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
