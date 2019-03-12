<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a class="${requestScope.action}" href="<c:url value="/totalizator?command=showCreateLeaguePage"/> ">
  <fmt:message bundle="${loc}" key="link.createLeague"/>
</a>


 <a href="/totalizator/addLeaguePage">EditLeague</a>