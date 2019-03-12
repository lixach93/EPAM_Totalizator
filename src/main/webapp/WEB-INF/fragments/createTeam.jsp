<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value="../../resources/js/league.js"/>"></script>

<c:if test="${not empty sessionScope.status}">
    <p><fmt:message bundle="${loc}" key="${sessionScope.status}" /></p>
    <c:remove var="status" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.errorMessage}">
    <p><fmt:message bundle="${loc}" key="${sessionScope.errorMessage}" /></p>
    <c:remove var="errorMessage" scope="session"/>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/totalizator">
    <label for="category">
        <fmt:message bundle="${loc}" key="select.categories"/>
    </label>
    <select id="category" name="categoryId" onchange="fillLeagues()">
        <option value="0">
            <fmt:message bundle="${loc}" key="select.categories"/>
        </option>
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}">${category.nameCategory}</option>
        </c:forEach>
    </select>
    <label for="league">
        <fmt:message bundle="${loc}" key="select.league"/>
    </label>
    <select id="league" name="leagueId">
        <option value="0"><fmt:message bundle="${loc}" key="select.league"/></option>
    </select>

    <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showCreateTeamPage" />">
    <input type="text" name="teamName"  placeholder="<fmt:message bundle="${loc}" key="input.data"/>"/>
    <input type="hidden" name="command" value="createTeam"/>
    <p><input type="submit" value="<fmt:message bundle="${loc}" key="button.create"/>"></p>
</form>


