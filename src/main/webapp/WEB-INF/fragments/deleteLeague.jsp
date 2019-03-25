<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty sessionScope.status}">
    <c:choose>
        <c:when test="${sessionScope.status eq 'info.successful'}">
            <div class="alert alert-success">
                <strong><fmt:message bundle="${loc}" key="${sessionScope.status}"/> </strong>
            </div>
        </c:when>
        <c:when test="${sessionScope.status eq 'info.unsuccessful'}">
            <div class="alert alert-danger">
                <strong><fmt:message bundle="${loc}" key="${sessionScope.status}"/> </strong>
            </div>
        </c:when>
    </c:choose>
    <c:remove var="status" scope="session"/>
</c:if>
<c:if test="${requestScope.size == 0}">
    <h3><fmt:message bundle="${loc}" key="size.empty"/></h3>
</c:if>
<c:forEach items="${leagues}" var="league">
    <div class="media">
        <form method="post" action="<c:url value="totalizator"/>">
            <input type="hidden" name="redirect" value="<c:url value="/totalizator/deleteLeague" />">
            <input type="hidden" name="command" value="deleteLeagueAction"/>
            <input type="hidden" name="leagueId" value="${league.id}"/>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label><c:out value="${league.id}"/></label>
                    <label><c:out value="${league.leagueName}"/></label>
                </div>
                <div class="form-group col-md-3">
                    <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="<fmt:message bundle="${loc}" key="button.remove"/> ">
                </div>
            </div>
        </form>
    </div>
</c:forEach>
