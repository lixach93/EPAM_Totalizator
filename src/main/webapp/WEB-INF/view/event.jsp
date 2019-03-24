<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container own-container">
            <c:forEach var="event" items="${requestScope.events}" begin="0" end="1">
                <c:set var="category" value="${event.competition.firstTeam.league.category.categoryName}"/>
                <c:set var="rateType" value="${event.rate.value}"/>
            </c:forEach>
            <div class="well">
                <div class="row">
                    <div class="form-group col-md-4">
                        <c:choose>
                            <c:when test="${rateType eq 'total'}">
                                <h3 class="h3">
                                    <fmt:message bundle="${loc}" key="h3.score"/>
                                </h3>
                            </c:when>
                            <c:when test="${rateType eq 'team'}">
                                <c:if test="${category eq 'football'}">
                                    <h3 class="h3">
                                        <fmt:message bundle="${loc}" key="h3.football"/>
                                    </h3>
                                </c:if>
                                <c:if test="${category eq 'hockey'}">
                                    <h3 class="h3">
                                        <fmt:message bundle="${loc}" key="h3.hockey"/>
                                    </h3>
                                </c:if>
                                <c:if test="${category eq 'basketball'}">
                                    <h3 class="h3">
                                        <fmt:message bundle="${loc}" key="h3.basketball"/>
                                    </h3>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <h3 class="h3">
                                    <fmt:message bundle="${loc}" key="h3.empty"/>
                                </h3>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
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
                        <c:if test="${not empty sessionScope.errorMessage}">
                            <div class="alert alert-danger">
                                <p><fmt:message bundle="${loc}" key="${sessionScope.errorMessage}" /></p>
                                <c:remove var="errorMessage" scope="session"/>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.userRole ne 'user'}">
                            <div class="alert alert-warning own-warning">
                                <h4><fmt:message bundle="${loc}" key="h.level"/></h4>
                            </div>
                        </c:if>
                    </div>
                </div>
        <c:forEach var="event" items="${requestScope.events}">
            <div class="media own-media">
                <form method="post" action="<c:url value="/totalizator"/>">
                    <input type="hidden" name="eventId" value="${event.id}">
                    <input type="hidden" name="command" value="makeBet">
                    <div class="row">
                        <div class="form-group col-md-2">
                            <label>${event.id}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-2">
                            <span>
                                <fmt:message bundle="${loc}" key="link.league"/>
                                    <c:out value="${event.competition.firstTeam.league.leagueName}"/>
                            </span>
                            <label>
                                <fmt:message bundle="${loc}" key="link.team"/>
                                <c:out value="${event.competition.firstTeam.nameTeam}"/>
                            </label>
                        </div>
                        <div class="form-group col-md-1">
                            -
                        </div>
                        <div class="form-group col-md-2">
                            <span>
                                <fmt:message bundle="${loc}" key="link.league"/>
                                <c:out value="${event.competition.secondTeam.league.leagueName}"/>
                            </span>
                            <label>
                                <fmt:message bundle="${loc}" key="link.team"/>
                                <c:out value="${event.competition.secondTeam.nameTeam}"/>
                            </label>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <c:if test="${sessionScope.userRole eq 'user'}">
                            <c:choose>
                                <c:when test="${event.rate.value eq 'team'}">
                                    <label class="radio-inline"><input type="radio" name="team" value="1" checked>
                                        <fmt:message bundle="${loc}" key="bet.win1"/>
                                    </label>
                                    <label class="radio-inline"><input type="radio" name="team" value="0">
                                        <fmt:message bundle="${loc}" key="bet.draw"/>
                                    </label>
                                    <label class="radio-inline"><input type="radio" name="team" value="2">
                                        <fmt:message bundle="${loc}" key="bet.win2"/>
                                    </label>
                                </c:when>
                                <c:when test="${event.rate.value eq 'total'}">
                                    <p><fmt:message bundle="${loc}" key="p.total"/></p>
                                    <input type="text" name="firstScore" class="btn btn-outline-success my-2 my-sm-0 own-money" size="1" required>
                                    <input type="text" name="secondScore" class="btn btn-outline-success my-2 my-sm-0 own-money" size="1" required>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="form-group col-md-4 own-group1">
                            <label for="money"><fmt:message bundle="${loc}" key="label.money"/></label>
                            <input id="money" class="btn btn-outline-success my-2 my-sm-0 own-money" type="text" size="5" name="money" required/>
                            <button type="submit" class="btn btn-outline-success my-2 my-sm-0" >
                                <fmt:message bundle="${loc}" key="button.makeRate"/>
                            </button>
                            </c:if>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${rateType eq 'total'}">
                            <c:set var="command" value="scoreEvent" scope="request"/>
                            <input type="hidden" name="redirect" value="<c:url value="/totalizator/scoreEvent"/>">
                        </c:when>
                        <c:when test="${rateType eq 'team'}">
                            <c:set var="command" value="teamEvent" scope="request"/>
                            <c:if test="${category eq 'football'}">
                                <c:set var="category" value="football" scope="request"/>
                                <input type="hidden" name="redirect" value="<c:url value="/totalizator/teamEvent/football"/>">
                            </c:if>
                            <c:if test="${category eq 'hockey'}">
                                <c:set var="category" value="hockey" scope="request"/>
                                <input type="hidden" name="redirect" value="<c:url value="/totalizator/teamEvent/hockey"/>">
                            </c:if>
                            <c:if test="${category eq 'basketball'}">
                                <c:set var="category" value="basketball" scope="request"/>
                                <input type="hidden" name="redirect" value="<c:url value="/totalizator/teamEvent/basketball"/>">
                            </c:if>
                        </c:when>
                    </c:choose>
                </form>
            </div>
        </c:forEach>
            <jsp:include page="../fragments/pagination.jsp"/>
    </div>
    </div>
</body>
</html>