<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container">
        <c:if test="${not empty sessionScope.userLogin}">
        <div class="alert alert-success">
            <strong>Success!</strong>${sessionScope.userLogin}" user success login!
        </div>
        </c:if>

        <c:if test="${not empty requestScope.errorMessage}">
            <div class="alert alert-danger">
                <strong>Danger!</strong>${requestScope.errorMessage}
                <c:out value="${userError.login}"/>
                <c:out value="${userError.email}"/>
            </div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/totalizator">
            <input type="hidden" name="redirect" value="<c:out value="/totalizator"/>">
            <input type="hidden" name="command" value="login">
            <div class="form-group">
                <label for="usr"><fmt:message bundle="${loc}" key="label.login"/>: </label>
                <input type="text" name="login" class="form-control" id="usr">
            </div>
            <div class="form-group">
                <label for="pwd"><fmt:message bundle="${loc}" key="label.password"/>:</label>
                <input type="password" name="password" class="form-control" id="pwd">
            </div>
            <div class="form-group">
                <div class="text-center">
                    <button class="btn-register btn btn-lg btn-primary" type="submit">
                        <fmt:message bundle="${loc}" key="button.login"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
