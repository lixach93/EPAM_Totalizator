<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    ${requestScope.competitionRate.id}
    ${requestScope.competitionRate.competition.firstOpponent.nameOpponent}
    ${requestScope.competitionRate.competition.secondOpponent.nameOpponent}
    ${requestScope.competitionRate.rate.value}
    <a href="<c:url value="totalizator?command=showCompetitionRate&category=${requestScope.competitionRate.competition.firstOpponent.league.category.nameCategory}"/> ">Back</a>
</body>
</html>
