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
            <c:set var="redirect" value="${pageContext.request.contextPath}/totalizator?command=showPersonalPage" scope="application"/>
            <div class="vertical-menu">
                <a  class="${requestScope.activeOne}" href="${pageContext.request.contextPath}/totalizator?command=choosePersonalPage&page=personal">
                    <fmt:message bundle="${loc}" key="href.personal"/>
                </a>
                <a class="${requestScope.activeTwo}" href="${pageContext.request.contextPath}/totalizator?command=choosePersonalPage&page=betting">
                    <fmt:message bundle="${loc}" key="href.betting"/>
                </a>
            </div>
            <div class="right-container">
                <div class="top-block">
                    <c:choose>
                        <c:when test="${requestScope.block eq 'personal'}">
                            <jsp:include page="../fragments/personal.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.block eq 'betting'}">
                            <jsp:include page="../fragments/betting.jsp"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="down-block">
                    <c:choose>
                        <c:when test="${requestScope.action eq 'activeBetting'}">
                            <jsp:include page="../fragments/activeBetting.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.action eq 'resultBetting'}">
                            <jsp:include page="../fragments/resultBetting.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.action eq 'userInfo'}">
                            <jsp:include page="../fragments/userInfo.jsp"/>
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
