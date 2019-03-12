<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a class="${requestScope.linkOne}" href="<c:url value="/totalizator?command=showActiveBettingPage"/> ">
    <fmt:message bundle="${loc}" key="href.betting"/>
</a>
<a class="${requestScope.linkTwo}" href="<c:url value="/totalizator?command=showResultBettingPage"/> ">
    <fmt:message bundle="${loc}" key="href.result"/>
</a>

