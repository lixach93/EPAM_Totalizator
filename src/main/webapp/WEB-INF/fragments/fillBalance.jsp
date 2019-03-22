<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty sessionScope.status}">
    <c:choose>
        <c:when test="${sessionScope.status eq 'info.successful'}">
            <div class="alert alert-success">
                <strong><fmt:message bundle="${loc}" key="${sessionScope.status}"/> </strong>
            </div>
        </c:when>
        <c:when test="${sessionScope.status eq 'info.unsuccessful'}">
            <div class="alert alert-danger">
                <strong><fmt:message bundle="${loc}" key="${sessionScope.status}"/> </strong>
            </div>
        </c:when>
    </c:choose>
    <c:remove var="status" scope="session"/>
</c:if>
<div class="media">
    <nav class="navbar navbar-light bg-light">
        <form class="form-inline" method="post" action="<c:url value="totalizator"/>">
            <input type="hidden" name="redirect" value="<c:url value="/totalizator/updateBalance"/>">
            <input type="hidden" name="command" value="fillBalance">
            <div class="form-row">
                <div class="form-group col-md-2 own-form_2">
                    <label for="cdn">Введите номер карты</label>
                    <input class="form-control mr-sm-2" id="cdn" name="cardNumber" type="tel" pattern ="[\d]{4}\s[\d]{4}\s[\d]{4}\s[\d]{4}" maxlength="19" placeholder="<fmt:message bundle="${loc}" key="input.cardNumber" />" aria-label="Card Number" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2 own-form_2">
                    <label for="cdn">Введите cумму</label>
            <input class="form-control mr-sm-2" type="tel" name="money" pattern="\d+(\.\d+)?" maxlength="5" placeholder="<fmt:message bundle="${loc}" key="input.money" />" aria-label="Money" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2 own-form_2">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                <fmt:message bundle="${loc}" key="button.confirm"/>
            </button>
                </div>
            </div>
        </form>
    </nav>
</div>