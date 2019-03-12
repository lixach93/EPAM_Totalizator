<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <c:forEach items="${bettings}" var="betting">
  <p>ID :<c:out value="${betting.id}"/></p>
        <c:out value="${betting.event.competition.firstTeam.nameTeam}"/> -
        <c:out value="${betting.event.competition.secondTeam.nameTeam}"/>
        <c:if test="${betting.event.rate.value eq 'total'}">
            <fmt:message bundle="${loc}" key="p.score"/>
            <c:out value="${betting.opponentFirstScore}"/> :
            <c:out value="${betting.opponentSecondScore}"/>
        </c:if>
        <c:if test="${betting.event.rate.value eq 'team'}">
            <c:choose>
                <c:when test="${betting.winner eq '0'}">
                    <fmt:message bundle="${loc}" key="p.draw"/>
                </c:when>
                <c:otherwise>
                    <fmt:message bundle="${loc}" key="p.winner"/>:
                     <c:out value="${betting.winner}"/>
                </c:otherwise>
            </c:choose>
        </c:if>
        <br/>
        <fmt:message bundle="${loc}" key="p.moneyRate"/> : <c:out value="${betting.money}"/>
        <br/>
        <p/>
 </c:forEach>
