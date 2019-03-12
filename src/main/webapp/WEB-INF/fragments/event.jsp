<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a class="${requestScope.linkOne}" href="<c:url value="/totalizator?command=showActiveEventPage"/>">
    <fmt:message bundle="${loc}" key="link.activeEvent"/>
</a>
<a class="${requestScope.linkTwo}" href="<c:url value="/totalizator?command=showCloseCompetitionPage"/>">Closed Event</a>