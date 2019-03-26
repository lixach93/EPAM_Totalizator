<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <c:import url="../fragments/header.jsp"/>
<body>
    <div class="container">
        <c:if test="${not empty sessionScope.status}">
            <div class="alert alert-success">
                <strong><fmt:message bundle="${loc}" key="${sessionScope.status}"/> </strong>
            </div>
        </c:if>
        <c:remove var="status" scope="session"/>
        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">
                <fmt:message bundle="${loc}" key="${sessionScope.errorMessage}"/>
            </div>
        </c:if>
        <c:remove var="errorMessage" scope="session"/>
        <div class="well">
            <div class="media">
                <form method="post" action="<c:url value="/totalizator"/>">
                    <input type="hidden" name="redirect" value="<c:url value="/totalizator/registration"/>"/>
                    <input type="hidden" name="command" value="registrationAction">
                    <div class="form-group">
                        <label for="usr" class="block"><fmt:message bundle="${loc}" key="label.login"/>:</label>
                        <div class="alert alert-info own-info">
                            <p><fmt:message bundle="${loc}" key="registration.login"/></p>
                        </div>
                        <input type="text" name="login" pattern="\w{4,20}" class="form-control" id="usr" required placeholder="<fmt:message bundle="${loc}" key="placeholder.login"/>">
                    </div>
                    <div class="form-group">
                        <label for="email"><fmt:message bundle="${loc}" key="label.email"/>:</label>
                        <input type="email" name="email" class="form-control" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}" id="email" required placeholder="<fmt:message bundle="${loc}" key="placeholder.email"/>">
                    </div>
                    <div class="form-group">
                        <label for="pwd" class="block"><fmt:message bundle="${loc}" key="label.password"/>:</label>
                        <div class="alert alert-info own-info">
                            <p><fmt:message bundle="${loc}" key="registration.password"/></p>
                        </div>
                        <input type="password" name="password" class="form-control" pattern="^[A-Za-zА-я0-9]{6,}$" id="pwd" required placeholder="<fmt:message bundle="${loc}" key="placeholder.password"/>">
                    </div>
                    <div class="form-group">
                        <label for="cpwd"><fmt:message bundle="${loc}" key="label.confirmPassword"/>:</label>
                        <input id ="cpwd" type="password" name="confirmPassword" pattern="^[A-Za-zА-я0-9]{6,}$"  required class="form-control" placeholder="<fmt:message bundle="${loc}" key="placeholder.confirmPassword"/>">
                    </div>
                    <div class="form-group">
                        <div class="text-center">
                            <button class="btn-register btn btn-lg btn-primary" type="submit">
                                <fmt:message bundle="${loc}" key="button.registration"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
