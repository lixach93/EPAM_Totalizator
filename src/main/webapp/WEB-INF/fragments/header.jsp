<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="locale" var="loc" scope="application" />
<head>
    <title>EpamTotalizator</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/styles/bootstrap.min.css"/>" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/styles/style.css"/>" type="text/css" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator">EpamTotalizator</a>
        </div>

        <a href="<c:url value="/totalizator?command=changeLocale&language=ru_RU"/> ">RU</a>
        <a href="<c:url value="/totalizator?command=changeLocale&language=en_US"/> ">ENG</a>
        <a href="<c:url value="/totalizator?command=changeLocale&language=be_BY"/> ">BY</a>
        <div class="navbar-right">
            <c:choose>
                <c:when test="${empty sessionScope.userLogin}">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator/login"><fmt:message bundle="${loc}" key="link.login"/> </a>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator/registration"><fmt:message bundle="${loc}" key="link.registration"/></a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${sessionScope.userRole eq 'user'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator/personal">
                                <fmt:message bundle="${loc}" key="link.personal"/>
                            </a>
                        </c:when>
                        <c:when test="${sessionScope.userRole eq 'administrator'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator/admin">
                                <fmt:message bundle="${loc}" key="link.admin"/>
                            </a>
                        </c:when>
                        <c:when test="${sessionScope.userRole eq 'moderator'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator/moderator">
                                <fmt:message bundle="${loc}" key="link.moderator"/>
                            </a>
                        </c:when>
                    </c:choose>
                    <h2 class="navbar-brand user_name">${sessionScope.userLogin}</h2>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator/logout">
                        <fmt:message bundle="${loc}" key="link.exit"/>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>