<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:forEach items="${events}" var="event">
    <div class="media">
        <div class="form-row">
            <div class="form-group col-md-3">
                <label><c:out value="${event.id}"/></label>
                <label><c:out value="${event.competition.firstTeam.nameTeam}"/></label>
                <label><c:out value="${event.competition.firstOpponentResult}"/></label> :
                <label><c:out value="${event.competition.secondOpponentResult}"/></label>
                <label><c:out value="${event.competition.secondTeam.nameTeam}"/></label>
            </div>
            <div class="form-group col-md-3">
                <c:if test="${event.rate.value eq 'total'}">
                    <label><fmt:message bundle="${loc}" key="label.betTeam"/></label>
                </c:if>
                <c:if test="${event.rate.value eq 'team'}">
                    <label><fmt:message bundle="${loc}" key="label.betScore"/></label>
                </c:if>
            </div>
            <div class="form-group col-md-3">
                <label><fmt:message bundle="${loc}" key="p.winMoney"/></label> <fmt:formatNumber value="${event.winPercent}" maxFractionDigits="2"/>
            </div>
        </div>
    </div>
</c:forEach>
<c:set var="command" value="charges" scope="request"/>
<jsp:include page="../fragments/pagination.jsp"/>
