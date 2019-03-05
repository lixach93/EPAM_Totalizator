<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="locale" var="loc" scope="application" />
<head>
    <title>EpamTotalizator</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link href="../../resources/styles/style.css" type="text/css" rel="stylesheet" />
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
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator?command=showLoginPage"><fmt:message bundle="${loc}" key="link.login"/> </a>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator?command=showRegistrationPage"><fmt:message bundle="${loc}" key="link.registration"/></a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${sessionScope.userRole eq 'user'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator?command=showPersonalPage">
                                <fmt:message bundle="${loc}" key="link.personal"/>
                            </a>
                        </c:when>
                        <c:when test="${sessionScope.userRole eq 'admin'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator?command=showAdminPage">
                                <fmt:message bundle="${loc}" key="link.admin"/>
                            </a>
                        </c:when>
                    </c:choose>
                    <h2 class="navbar-brand user_name">${sessionScope.userLogin}</h2>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/totalizator?command=logout">
                        <fmt:message bundle="${loc}" key="link.exit"/>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>