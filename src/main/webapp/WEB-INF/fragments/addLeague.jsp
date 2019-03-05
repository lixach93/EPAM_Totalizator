<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form method="post" action="${pageContext.request.contextPath}/totalizator">
    <label for="category">Выберете категорию</label>
        <select id="category" name="categoryId">
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.nameCategory}</option>
            </c:forEach>
        </select>
    <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=addLeaguePage" />">
    <input type="text" name="leagueName" value=" some text "/>
    <input type="hidden" name="command" value="addLeague"/>
    <p><input type="submit" value="Отправить"></p>
</form>

