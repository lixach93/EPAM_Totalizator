<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<p>ID :<c:out value="${user.id}"/></p>
<p><fmt:message bundle="${loc}" key="label.login"/> <c:out value="${user.login}"/></p>
<p><fmt:message bundle="${loc}" key="label.email"/> <c:out value="${user.email}"/></p>
<p><fmt:message bundle="${loc}" key="label.userMoney"/> <c:out value="${user.money}"/></p>

