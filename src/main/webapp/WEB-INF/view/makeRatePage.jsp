<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    ${requestScope.event.id}
    ${requestScope.event.competition.firstTeam.nameTeam}
    ${requestScope.event.competition.secondTeam.nameTeam}
    ${requestScope.event.rate.value}
    <a href="<c:url value="totalizator?command=showCompetitionRate&category=${requestScope.event.competition.firstTeam.league.category.nameCategory}"/> ">Back</a>
</body>
</html>
