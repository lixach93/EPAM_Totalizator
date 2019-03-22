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
                <c:set var="redirect" value="${pageContext.request.contextPath}/totalizator/moderator" scope="application"/>
                <nav class="navbar navbar-inverse own-navbar">
                    <div class="container-fluid">
                        <div class="navbar-header">
                        </div>
                        <ul class="nav navbar-nav">
                            <li class="${requestScope.activeOne}">
                                <a  class="${requestScope.activeOne}" href="<c:url value="/totalizator/activeEvent"/>">
                                    <fmt:message bundle="${loc}" key="link.percent"/>
                                </a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav">
                            <li class="${requestScope.activeTwo}">
                                <a href="${pageContext.request.contextPath}/totalizator/charges">
                                    <fmt:message bundle="${loc}" key="link.charges"/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="well">
                    <c:choose>
                        <c:when test="${requestScope.action eq 'activeEvent'}">
                            <jsp:include page="../fragments/activeEvent.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.action eq 'charges'}">
                            <jsp:include page="../fragments/charges.jsp"/>
                        </c:when>
                    </c:choose>
                </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
