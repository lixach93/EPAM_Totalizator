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
<div class="media">
    <nav class="navbar navbar-light bg-light">
        <form class="form-inline" method="post" action="<c:url value="/totalizator"/>">
            <input  type="hidden" name="command" value="createLeagueAction"/>
            <input type="hidden" name="redirect" value="<c:url value="/totalizator/createLeague" />">
            <label for="category">
                <fmt:message bundle="${loc}" key="select.categories"/>
            </label>
            <select class="form-control mr-sm-2" id="category" name="categoryName">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.categoryName}">${category.categoryName}</option>
                </c:forEach>
            </select>

            <input class="form-control mr-sm-2" type="text" name="leagueName" pattern="[a-zA-Z]+( [a-zA-Z]+)?"  minlength="3" required placeholder="<fmt:message bundle="${loc}" key="input.newLeague" />"/>
            <p><input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="<fmt:message bundle="${loc}" key="button.confirm"/>"></p>
        </form>
    </nav>
</div>


