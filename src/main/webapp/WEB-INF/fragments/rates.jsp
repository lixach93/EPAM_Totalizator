<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${category eq 'football' and footballCounter == 1}">
        <p>Футбол</p>
        <c:set var="footballCounter" value="${footballCounter+1}" scope="request"/>
    </c:when>
    <c:when test="${category eq 'hockey' and hockeyCounter == 1}">
        Хоккей
        <c:set var="hockeyCounter" value="${hockeyCounter+1}" scope="request"/>
    </c:when>
</c:choose>

<form method="post" action="${pageContext.request.contextPath}/totalizator?command=makeRate">

    <c:out value="${rate.competition.firstOpponent.nameOpponent}"/>
    <c:out value="${rate.competition.firstOpponent.league.nameLeague}"/>
    <c:out value="${rate.competition.secondOpponent.nameOpponent}"/>
    <c:out value="${rate.competition.secondOpponent.league.nameLeague}"/>
    <c:out value="${rate.rate.value}"/>
    <button type="button" onclick=myFunction(${rate.id})>Сделать ставку</button>

    <div class="no-js" id="myDIV${rate.id}" style="display: none">
        <label class="radio-inline"><input type="radio" name="opponent" value="1" checked>X1 </label>
        <label class="radio-inline"><input type="radio" name="opponent" value="0">XX </label>
        <label class="radio-inline"><input type="radio" name="opponent" value="2">X2 </label>
        <label for="money">Money</label>
        <input id="money" type="number" name="money" required/>
        <c:choose>
            <c:when test="${rate.rate.value eq 'total'}">
                <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showCompetitionRate" />">
            </c:when>
            <c:otherwise>
                <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showCompetitionRate&category=${category}" />">
            </c:otherwise>
        </c:choose>
        <input type="hidden" name="competitionRateId" value="${rate.id}">
        <c:choose>
            <c:when test="${sessionScope.userRole eq 'user'}">
                <input type="submit" value="Make Rate"/>
            </c:when>
            <c:otherwise>
                <h4>Только пользователи могут делать ставки</h4>
            </c:otherwise>
        </c:choose>
    </div>
  <br>
</form>

<script>
    function myFunction(a) {
        var x = document.getElementById('myDIV'+a);
        if (x.style.display === 'none') {
            x.style.display = 'block';
        } else {
            x.style.display = 'none';
        }
    }
</script>