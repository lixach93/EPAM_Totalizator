<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:forEach items="${bets}" var="bet">
    <p>ID: <c:out value="${betting.id}"/></p>
    <c:out value="${bet.event.competition.firstTeam.nameTeam}"/>
    <c:out value="${bet.event.competition.firstOpponentResult}"/> :
    <c:out value="${bet.event.competition.secondOpponentResult}"/>
    <c:out value="${bet.event.competition.secondTeam.nameTeam}"/>
    <br/>
    <fmt:message bundle="${loc}" key="href.betting"/>
    <br/>
    <c:choose>
        <c:when test="${bet.event.rate.value eq 'total'}">
            <fmt:message bundle="${loc}" key="p.score"/>
            <c:out value="${bet.opponentFirstScore}"/> :
            <c:out value="${bet.opponentSecondScore}"/>
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
    <fmt:message bundle="${loc}" key="p.moneyRate"/> <c:out value="${bet.money}"/>
    <fmt:message bundle="${loc}" key="p.winMoney"/> <c:out value="${bet.winMoney}"/>
    <p/>
</c:forEach>