<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <select id="category" name="categoryName">
            <c:forEach items="${categories}" var="category">
                <option value="${category.nameCategory}">${category.nameCategory}</option>
            </c:forEach>
        </select>
    <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showCreateLeaguePage" />">
    <input type="text" name="leagueName" placeholder="<fmt:message bundle="${loc}" key="input.data" />"/>
    <input type="hidden" name="command" value="createLeague"/>
    <p><input type="submit" value="<fmt:message bundle="${loc}" key="button.create"/>"></p>
</form>

