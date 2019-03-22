<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty sessionScope.status}">
    <c:choose>
        <c:when test="${sessionScope.status eq 'info.successful'}">
            <div class="alert alert-success">
                <strong><fmt:message bundle="${loc}" key="status.successfulgame"/> </strong>
                <label><c:out value="${competition.firstTeam.nameTeam}"/></label>
                <label><c:out value="${competition.firstOpponentResult}"/></label>
                :
                <label><c:out value="${competition.secondOpponentResult}"/></label>
                <label><c:out value="${competition.secondTeam.nameTeam}"/></label>
            </div>
        </c:when>
        <c:when test="${sessionScope.status eq 'info.unsuccessful'}">
            <div class="alert alert-danger">
                <strong><fmt:message bundle="${loc}" key="status.unsuccessfulgame"/> </strong>
            </div>
        </c:when>
    </c:choose>
    <c:remove var="status" scope="session"/>
</c:if>
<c:forEach items="${requestScope.competitions}" var="competition">
    <div class="media">
        <form method="post" action="<c:url value="totalizator"/>">
            <input type="hidden" name="redirect" value="<c:url value="/totalizator/closeCompetition" />">
            <input type="hidden" name="command" value="closeCompetitionAction"/>
            <input type="hidden" name="competitionId" value="${competition.id}"/>
            <div class="form-row">
                    <div class="form-group col-md-3">
                    <label><c:out value="${competition.id}"/></label>
                    <label><c:out value="${competition.firstTeam.nameTeam}"/></label>
                    :
                    <label><c:out value="${competition.secondTeam.nameTeam}"/></label>
                </div>
                <div class="form-group col-md-2">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                        <fmt:message bundle="${loc}" key="link.closeCompetition"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</c:forEach>
