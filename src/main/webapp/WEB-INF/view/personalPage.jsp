<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>

<div class="container">
    <c:choose>
        <c:when test="${not empty requestScope.permissionMessage}">
            <h2>${requestScope.permissionMessage}</h2>
        </c:when>
        <c:otherwise>
            <button id="somebutton">press here</button>
            <div id="somediv"></div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
