<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container">
        <c:if test="${not empty sessionScope.success}">
            <div class="alert alert-success">
                <strong>Success!</strong>${requestScope.success}" user success registration!
            </div>
        </c:if>
        <c:remove var="success" scope="session"/>
        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">
                <strong>Danger!</strong>
                <c:out value="${sessionScope.userError.login}"/>
                <c:out value="${sessionScope.userError.email}"/>
                </br>
                <fmt:message bundle="${loc}" key="${sessionScope.errorMessage}"/>
            </div>
        </c:if>
        <c:remove var="errorMessage" scope="session"/>
        <form method="post" action="${pageContext.request.contextPath}/totalizator">
            <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showRegistrationPage"/>"/>
            <input type="hidden" name="command" value="registration">
            <div class="form-group">
                <label for="usr"><fmt:message bundle="${loc}" key="label.login"/>:</label>
                <input type="text" name="login" class="form-control" id="usr" placeholder=" User123">
            </div>
            <div class="form-group">
                <label for="email"><fmt:message bundle="${loc}" key="label.email"/>:</label>
                <input type="email" name="email" class="form-control" id="email" placeholder=" example@gmail.com">
            </div>
            <div class="form-group">
                <label for="pwd"><fmt:message bundle="${loc}" key="label.password"/>:</label>
                <input type="password" name="password" class="form-control" id="pwd" placeholder=" 300sA">
            </div>
            <div class="form-group">
                <label for="cpwd"><fmt:message bundle="${loc}" key="label.confirmPassword"/>:</label>
                <input type="password" name="confirmPassword" class="form-control" id="cpwd" placeholder=" 300sA">
            </div>
            <div class="form-group">
                <div class="text-center">
                    <button class="btn-register btn btn-lg btn-primary" type="submit">
                        <fmt:message bundle="${loc}" key="button.registration"/>
                    </button>
                </div>
            </div>
            <div class="form-group">
                <div class="alert alert-danger" id="error-password" style="display:none">
                    some text
                </div>
            </div>
            <div class="form-group">
                <div class="alert alert-danger" id="error-user" style="display:none">
                    some text1
                </div>
            </div>
        </form>
    </div>

<script>
    function validation() {


        var userInput = document.getElementById("usr");
        var userValue = userInput.value;



        if (userValue === "") {
            var x = document.getElementById("error-user")
            x.style.display = 'block';
            return false;
        }
    }
    function checkPassword(){
        var passwordInput = document.getElementById("pwd");
        var passwordValue = passwordInput.value;
        var x = document.getElementById("error-password")
        if (passwordValue === "") {
            x.style.display = 'block';
            return false;
        }else {

        }
    }
</script>
</body>
</html>
