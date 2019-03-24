<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value="../../resources/js/league.js"/>"></script>


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
<div class="media">
    <form method="post" action="<c:url value="/totalizator"/>">
        <input type="hidden" name="command" value="createTeamAction"/>
        <input type="hidden" name="redirect" value="<c:url value="/totalizator/createTeam" />">
        <div class="row">
            <div class="form-group col-md-2 own-form_2">
                <label for="category">
                    <fmt:message bundle="${loc}" key="category"/>
                </label>
                <select id="category" class="form-control" name="categoryId" onchange="fillLeagues()">
                    <option value="0">
                        <fmt:message bundle="${loc}" key="select.categories"/>
                    </option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="league">
                    <fmt:message bundle="${loc}" key="link.league"/>
                </label>
                <select id="league" class="form-control" name="leagueId">
                    <option value="0"><fmt:message bundle="${loc}" key="select.league"/></option>
                </select>
            </div>
            <div class="form-group col-md-2 own-form_1">
                <input type="text" name="teamName" pattern="[a-zA-Z0-9 ]+" class="form-control" required placeholder="<fmt:message bundle="${loc}" key="input.team"/>"/>
            </div>
            <div class="form-group col-md-3 own-form_1">
                <input type="submit" class="btn btn-outline-success my-2 my-sm-0" value="<fmt:message bundle="${loc}" key="button.confirm"/>">
            </div>
        </div>
</form>
</div>
