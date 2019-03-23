<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <c:if test="${requestScope.size == 0}">
        <h3><fmt:message bundle="${loc}" key="size.empty"/></h3>
    </c:if>

    <c:forEach items="${bets}" var="bet">
        <div class="media">
        <p>ID :<c:out value="${bet.id}"/></p>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label>
                    <c:out value="${bet.event.competition.firstTeam.nameTeam}"/> -
                    <c:out value="${bet.event.competition.secondTeam.nameTeam}"/>
                </label>
            </div>
            <div class="form-group col-md-2">
        <c:if test="${bet.event.rate.value eq 'total'}">
            <label><fmt:message bundle="${loc}" key="p.score"/></label>
            <c:out value="${bet.opponentFirstScore}"/> :
            <c:out value="${bet.opponentSecondScore}"/>
        </c:if>
        <c:if test="${bet.event.rate.value eq 'team'}">
            <c:choose>
                <c:when test="${bet.winner eq '0'}">
                    <label><fmt:message bundle="${loc}" key="p.draw"/></label>
                </c:when>
                <c:otherwise>
                    <label><fmt:message bundle="${loc}" key="p.winner"/>:</label>
                    <c:out value="${bet.winner}"/>
                </c:otherwise>
            </c:choose>
        </c:if>
            </div>
            <div class="form-group col-md-3">
                <label>
                    <fmt:message bundle="${loc}" key="p.moneyRate"/>
                </label> : <c:out value="${bet.money}"/>
            </div>
        </div>
        </div>
    </c:forEach>
    <c:set var="command" value="activeBet" scope="request"/>
    <jsp:include page="../fragments/pagination.jsp"/>



