<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${requestScope.size  == 0}">
    <h3><fmt:message bundle="${loc}" key="size.empty"/></h3>
</c:if>

<c:forEach items="${bets}" var="bet">
    <div class="media">
    <p><label>ID:</label> <c:out value="${bet.id}"/></p>
    <div class="row">
        <div class="form-group col-md-3">
            <label> <c:out value="${bet.event.competition.firstTeam.teamName}"/></label>
            <c:out value="${bet.event.competition.firstTeamResult}"/> :
            <c:out value="${bet.event.competition.secondTeamResult}"/>
            <label><c:out value="${bet.event.competition.secondTeam.teamName}"/></label>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-3">
    <label><fmt:message bundle="${loc}" key="p.moneyRate"/></label> <c:out value="${bet.money}"/>
        </div>
        <div class="form-group col-md-3">
            <label><fmt:message bundle="${loc}" key="p.bet"/></label>
    <c:choose>
        <c:when test="${bet.event.rate.value eq 'total'}">
            <fmt:message bundle="${loc}" key="p.score"/>
            <c:out value="${bet.teamFirstScore}"/> :
            <c:out value="${bet.teamSecondScore}"/>
        </c:when>
        <c:when test="${bet.event.rate.value eq 'team'}">
            <c:choose>
                <c:when test="${bet.winner eq '0'}">
                    <fmt:message bundle="${loc}" key="p.draw"/>
                </c:when>
                <c:otherwise>
                    <fmt:message bundle="${loc}" key="p.winner"/>:
                     <c:out value="${bet.winner}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
        </div>
        <div class="form-group col-md-3">
            <label><fmt:message bundle="${loc}" key="p.winMoney"/></label>
            <fmt:formatNumber value="${bet.winMoney}" maxFractionDigits="2"/>
        </div>
    </div>
    </div>
</c:forEach>
<c:set var="command" value="result" scope="request"/>
<jsp:include page="../fragments/pagination.jsp"/>