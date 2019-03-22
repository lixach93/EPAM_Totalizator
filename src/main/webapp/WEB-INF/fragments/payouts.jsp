<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:forEach items="${events}" var="event">
    <div class="media">
        <form method="post" action="<c:url value="totalizator"/>">
            <input type="hidden" name="redirect" value="<c:url value="/totalizator/payouts"/>">
            <input type="hidden" name="command" value="closeEvent"/>
            <input type="hidden" name="eventId" value="${event.id}"/>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label><c:out value="${event.id}"/></label>
                    <label><c:out value="${event.competition.firstTeam.nameTeam}"/></label>
                    :
                    <label><c:out value="${event.competition.secondTeam.nameTeam}"/></label>
                </div>
                <div class="form-group col-md-5">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                        <fmt:message bundle="${loc}" key="button.confirm"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</c:forEach>

<c:set var="command" value="payouts" scope="request"/>
<jsp:include page="../fragments/pagination.jsp"/>