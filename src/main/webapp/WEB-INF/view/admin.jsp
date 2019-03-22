<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    <c:choose>
        <c:when test="${not empty requestScope.permissionMessage}">
            <div class="container own-container">
                <div class="alert alert-warning">
                    <strong>Warning!</strong>
                    <h2><fmt:message bundle="${loc}" key="${requestScope.permissionMessage}"/></h2>
                </div>
            </div>
        </c:when>
    <c:otherwise>
    <div class="container own-container">
        <c:set var="redirect" value="${pageContext.request.contextPath}/totalizator/admin" scope="application"/>
        <nav class="navbar navbar-inverse own-navbar">
            <div class="container-fluid">
                <div class="navbar-header">
                </div>
                <ul class="nav navbar-nav">
                    <li class="dropdown ${requestScope.activeOne}">
                        <a class="dropdown-toggle" data-toggle="dropdown"  href="#">
                            <fmt:message bundle="${loc}" key="link.league"/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="<c:url value="/totalizator/createLeague"/> ">
                                    <fmt:message bundle="${loc}" key="link.createLeague"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown ${requestScope.activeTwo}">
                        <a class="dropdown-toggle" data-toggle="dropdown"  href="#">
                            <fmt:message bundle="${loc}" key="link.team"/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="<c:url value="/totalizator/createTeam"/>">
                                    <fmt:message bundle="${loc}" key="link.createTeam"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown ${requestScope.activeThree}">
                        <a class="dropdown-toggle" data-toggle="dropdown"  href="#">
                            <fmt:message bundle="${loc}" key="link.competition"/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="<c:url value="/totalizator/createCompetition"/>">
                                    <fmt:message bundle="${loc}" key="link.createCompetition"/>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="/totalizator/closeCompetition"/>">
                                    <fmt:message bundle="${loc}" key="link.closeCompetition"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="${requestScope.activeFour}">
                        <a href="<c:url value="/totalizator/payouts"/>">
                            <fmt:message bundle="${loc}" key="link.payouts"/>
                        </a>
                    </li>
                    <li class="${requestScope.activeFive}">
                        <a href="<c:url value="/totalizator/users"/>">
                            <fmt:message bundle="${loc}" key="link.users"/>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="well">
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
                            <c:when test="${requestScope.action eq 'payouts'}">
                                <jsp:include page="../fragments/payouts.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.action eq 'users'}">
                                <jsp:include page="../fragments/users.jsp"/>
                            </c:when>
                        </c:choose>
                    </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
