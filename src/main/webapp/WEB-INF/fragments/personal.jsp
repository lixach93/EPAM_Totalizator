<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a class="${requestScope.linkOne}" href="<c:url value="/totalizator?command=showUserPage"/> ">
    <fmt:message bundle="${loc}" key="href.personalInfo"/>
</a>
