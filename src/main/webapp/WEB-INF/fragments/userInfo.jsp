<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="media">
    <span class="pull-left">
        <img id="profile-image" class="media-object avatar" src="../../resources/images/avatar.png" alt="avatar">
    </span>
    <div class="media-body container-fluid own-media-body">
        <p>
    <span class="user glyphicon glyphicon-user">
        <fmt:message bundle="${loc}" key="label.login"/>
    </span>
            <span class="output ">
        <c:out value="${requestScope.user.login}"/>
    </span>
        </p>
        <p>
    <span class="user glyphicon glyphicon-envelope">
        <fmt:message bundle="${loc}" key="label.email"/>
    </span>
            <span class="output">
        <c:out value="${requestScope.user.email}"/>
    </span>
        </p>
        <p>
    <span class="user glyphicon glyphicon-eur">
        <fmt:message bundle="${loc}" key="label.userMoney"/>
    </span>
            <span class="output">
        <c:out value="${requestScope.user.money}"/>
    </span>
        </p>
    </div>
</div>