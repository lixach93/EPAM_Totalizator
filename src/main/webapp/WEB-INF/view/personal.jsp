<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="container own-container ">
            <c:set var="redirect" value="${pageContext.request.contextPath}/totalizator/personal" scope="application"/>
            <nav class="navbar navbar-inverse own-navbar">
                <div class="container-fluid">
                    <div class="navbar-header">
                    </div>
                    <ul class="nav navbar-nav">
                        <li class="${requestScope.activeOne}">
                            <a href="${pageContext.request.contextPath}/totalizator/personal">
                                <fmt:message bundle="${loc}" key="href.personal"/>
                            </a>
                        </li>
                        <li class="dropdown ${requestScope.activeTwo}">
                            <a class="dropdown-toggle" data-toggle="dropdown"  href="#">
                                <fmt:message bundle="${loc}" key="href.betting"/>
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="<c:url value="/totalizator/activeBet"/>">
                                        <fmt:message bundle="${loc}" key="link.activeBet"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="<c:url value="/totalizator/result"/>">
                                        <fmt:message bundle="${loc}" key="href.result"/>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown ${requestScope.activeThree}">
                            <a class="dropdown-toggle" data-toggle="dropdown"  href="#">
                                <fmt:message bundle="${loc}" key="href.operation"/>
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="<c:url value="/totalizator/updateBalance"/>">
                                        <fmt:message bundle="${loc}" key="link.updateBalance"/>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="well">
                <c:choose>
                    <c:when test="${requestScope.action eq 'activeBet'}">
                        <jsp:include page="../fragments/activeBet.jsp"/>
                    </c:when>
                    <c:when test="${requestScope.action eq 'result'}">
                        <jsp:include page="../fragments/result.jsp"/>
                    </c:when>
                    <c:when test="${requestScope.action eq 'userInfo'}">
                        <jsp:include page="../fragments/userInfo.jsp"/>
                    </c:when>
                    <c:when test="${requestScope.action eq 'fillBalance'}">
                        <jsp:include page="../fragments/fillBalance.jsp"/>
                    </c:when>
                </c:choose>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
