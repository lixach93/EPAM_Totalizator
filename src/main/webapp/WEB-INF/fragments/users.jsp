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
    <c:forEach items="${users}" var="user">
        <div class="media">
        <form method="post" action="<c:url value="/totalizator/users"/>">
            <input type="hidden" name="redirect" value="<c:url value="/totalizator/users" />">
            <input type="hidden" name="command" value="changeRole"/>
            <input type="hidden" name="userId" value="${user.id}"/>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <p>ID :<c:out value="${user.id}"/></p>
                    <label>
                        <fmt:message bundle="${loc}" key="label.login"/>
                    </label>
                    <c:out value="${user.login}"/>
                    <label>
                        <fmt:message bundle="${loc}" key="label.role"/>
                    </label>
                    <c:out value="${user.roleType.value}"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="role">
                        <fmt:message bundle="${loc}" key="label.changeRole"/>
                    </label>
                    <select id ="role" class="form-control" name="role">
                        <option value="administrator">Administrator</option>
                        <option value="user">User</option>
                        <option value="moderator">Moderator</option>
                    </select>
                </div>
                <div class="form-group col-md-3 own-form_4">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                        <fmt:message bundle="${loc}" key="button.confirm"/>
                    </button>
                </div>
            </div>
        </form>
        <br/>
        </div>
    </c:forEach>

<c:set var="command" value="users" scope="request"/>
<jsp:include page="../fragments/pagination.jsp"/>
