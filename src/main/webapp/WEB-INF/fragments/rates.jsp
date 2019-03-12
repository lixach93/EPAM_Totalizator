<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${category eq 'football' and footballCounter == 1}">
        <p class="category"><fmt:message bundle="${loc}" key="link.football"/></p>
        <c:set var="footballCounter" value="${footballCounter+1}" scope="request"/>
    </c:when>
    <c:when test="${category eq 'hockey' and hockeyCounter == 1}">
        <p class="category"><fmt:message bundle="${loc}" key="link.hockey"/></p>
        <c:set var="hockeyCounter" value="${hockeyCounter+1}" scope="request"/>
    </c:when>
    <c:when test="${category eq 'basketball' and basketballCounter == 1}">
        <p><fmt:message bundle="${loc}" key="link.basketball"/></p>
        <c:set var="basketballCounter" value="${basketballCounter+1}" scope="request"/>
    </c:when>
</c:choose>

<form method="post" action="${pageContext.request.contextPath}/totalizator">
    <input type="hidden" name="eventId" value="${event.id}">
    <input type="hidden" name="command" value="makeBetting">
    <div class="rate">
        <span class="span_label">
            <fmt:message bundle="${loc}" key="span.league"/>
            <c:out value="${event.competition.firstTeam.league.nameLeague}"/>
        </span>
        <span class="span_name">
            <fmt:message bundle="${loc}" key="span.team"/>
            <c:out value="${event.competition.firstTeam.nameTeam}"/>
        </span>
    </div>
    -
    <div class="rate">
        <span class="span_label">
            <fmt:message bundle="${loc}" key="span.league"/>
            <c:out value="${event.competition.secondTeam.league.nameLeague}"/>
        </span>
        <span class="span_name">
            <fmt:message bundle="${loc}" key="span.team"/>
            <c:out value="${event.competition.secondTeam.nameTeam}"/>
        </span>
    </div>
    <button type="button" class = "buttonRate" onclick=makeRate(${event.id})>
        <fmt:message bundle="${loc}" key="button.makeRate"/>
    </button>
    <br/>
    <div class="no-js" id="myDIV${event.id}" style="display: none">
        <c:choose>
            <c:when test="${event.rate.value eq 'team'}">
                <label class="radio-inline"><input type="radio" name="team" value="1" checked>X1 </label>
                <label class="radio-inline"><input type="radio" name="team" value="0">XX </label>
                <label class="radio-inline"><input type="radio" name="team" value="2">X2 </label>
            </c:when>
            <c:when test="${event.rate.value eq 'total'}">
                <p><fmt:message bundle="${loc}" key="p.total"/></p>
                <input type="text" name="firstScore"  size="1" required>
                <input type="text" name="secondScore" size="1" required>
            </c:when>
        </c:choose>
        <label for="money"><fmt:message bundle="${loc}" key="label.money"/></label>
        <input id="money" type="text" size="4" name="money" required/>
        <c:choose>
            <c:when test="${sessionScope.userRole eq 'user'}">
                <input type="submit" class = "buttonRate" value="<fmt:message bundle="${loc}" key="button.makeRate"/>"/>
            </c:when>
            <c:otherwise>
                <h4><fmt:message bundle="${loc}" key="h.level"/></h4>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${event.rate.value eq 'total'}">
                <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showEventPage"/>">
            </c:when>
            <c:otherwise>
                <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showEventPage&category=${category}"/>">
            </c:otherwise>
        </c:choose>
    </div>
  <br>
</form>

<script>
    function makeRate(a) {
        var x = document.getElementById('myDIV'+a);
        if (x.style.display === 'none') {
            x.style.display = 'block';
        } else {
            x.style.display = 'none';
        }
    }
</script>