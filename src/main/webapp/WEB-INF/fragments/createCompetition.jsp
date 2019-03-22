<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value="../../resources/js/competition.js"/>"></script>
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
        <form method="post" action="<c:url value="/totalizator"/>">
            <input type="hidden" name="redirect" value="<c:url value="/totalizator/createCompetition" />">
            <input type="hidden" name="command" value="createCompetitionAction"/>
            <div class="row">
                <div class="form-group col-md-2 own-form_2">
                    <label for="category">
                        <fmt:message bundle="${loc}" key="category"/>
                    </label>
                    <select id="category" class="form-control" name="categoryId" onchange="fillLeagues()">
                        <option value="0">
                            <fmt:message bundle="${loc}" key="select.categories"/>
                        </option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="league">
                        <fmt:message bundle="${loc}" key="label.firstLeague"/>
                    </label>
                    <select id="league" class="form-control" name="leagueId" onchange="fillOpponent()">
                        <option value="0"><fmt:message bundle="${loc}" key="select.league"/></option>
                    </select>
                    <label for="league2">
                        <fmt:message bundle="${loc}" key="label.secondLeague"/>
                    </label>
                    <select id="league2" class="form-control" name="leagueId" onchange="fillSecondOpponent()">
                        <option value="0"><fmt:message bundle="${loc}" key="select.league"/></option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="teamOne">
                        <fmt:message bundle="${loc}" key="label.firstTeam"/>
                    </label>
                    <select id="teamOne" class="form-control" name="teamFirstId">
                        <option value="0"><fmt:message bundle="${loc}" key="select.team"/></option>
                    </select>
                    <label for="teamTwo">
                        <fmt:message bundle="${loc}" key="label.secondTeam"/>
                    </label>
                    <select id="teamTwo" class="form-control" name="teamSecondId">
                        <option value="0"><fmt:message bundle="${loc}" key="select.team"/></option>
                    </select>
                </div>
                <div class="form-group col-md-6 own-form">
                    <p class="type_rate"><fmt:message bundle="${loc}" key="choose.bet"/></p>
                    <label class="radio-inline"><input type="radio" name="rate" value="team" checked>
                        <fmt:message bundle="${loc}" key="label.betTeam"/>
                    </label>
                    <label class="radio-inline"><input type="radio" name="rate" value="total">
                        <fmt:message bundle="${loc}" key="label.betScore"/>
                    </label>
                    <label class="radio-inline"><input type="radio" name="rate" value="all">
                        <fmt:message bundle="${loc}" key="label.betAll"/>
                    </label>
                    <input type="submit" class="btn btn-outline-success my-2 my-sm-0" value="<fmt:message bundle="${loc}" key="button.confirm"/>">
                </div>
            </div>
        </form>
</div>

