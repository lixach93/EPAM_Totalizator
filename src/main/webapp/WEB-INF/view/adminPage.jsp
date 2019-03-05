<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            $.get("totalizator?command=showAddLeaguePage", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                $("#somediv").innerText(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
            });
        });
    </script>
<body>
    <div class="navbar own-container">
        <c:choose>
            <c:when test="${not empty requestScope.permissionMessage}">
                <div class="alert alert-warning">
                    <strong>Warning!</strong>
                    <h2>${requestScope.permissionMessage}</h2>
                </div>
            </c:when>
            <c:otherwise>
                <div class="vertical-menu">
                    <a  class="${requestScope.activeOne}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=league">League</a>
                    <a class="${requestScope.activeTwo}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=opponent">Team</a>
                    <a class="${requestScope.activeThree}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=competition">Competition</a>
                    <a class="${requestScope.activeFour}" href="${pageContext.request.contextPath}/totalizator?command=chooseAdminPage&page=rate">Rate</a>

                </div>
                <div class="right-container">
                    <div class="top-block">
                        <c:choose>
                            <c:when test="${requestScope.page eq 'league'}">
                                <jsp:include page="../fragments/league.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.page eq 'opponent'}">
                                <jsp:include page="../fragments/team.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.page eq 'competition'}">
                            <jsp:include page="../fragments/competition.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.page eq 'rate'}">
                                <jsp:include page="../fragments/rate.jsp"/>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="down-block">
                        <c:choose>
                            <c:when test="${requestScope.action eq 'addLeague'}">
                                <jsp:include page="../fragments/addLeague.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.action eq 'addTeam'}">
                                <jsp:include page="../fragments/addTeam.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.action eq 'createCompetition'}">
                                <jsp:include page="../fragments/createCompetition.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.action eq 'closeCompetition'}">
                                <jsp:include page="../fragments/closeCompetition.jsp"/>
                            </c:when>
                            <c:when test="${requestScope.action eq 'closeCompRate'}">
                                <jsp:include page="../fragments/closeCompRate.jsp"/>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
