<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container">
        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">
                <fmt:message bundle="${loc}" key="${sessionScope.errorMessage}"/>
            </div>
        </c:if>
        <c:remove var="errorMessage" scope="session"/>
    <div class="well">
        <div class="media">
            <form method="post" action="<c:out value="/totalizator"/>">
                <input type="hidden" name="redirect" value="<c:out value="/totalizator"/>">
                <input type="hidden" name="redirectError" value="<c:out value="/totalizator/login"/>">
                <input type="hidden" name="command" value="loginAction">
                <div class="form-group">
                    <label for="usr"><fmt:message bundle="${loc}" key="label.login"/>: </label>
                    <input type="text" name="login" class="form-control" id="usr" pattern="\w{4,20}" required>
                </div>
                <div class="form-group">
                    <label for="pwd"><fmt:message bundle="${loc}" key="label.password"/>:</label>
                    <input type="password" name="password" class="form-control" id="pwd" pattern="^[A-Za-zА-я0-9]{6,}$" required>
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
    </div>
    </div>
</body>
</html>
