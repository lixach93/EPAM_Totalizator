<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container own-container">
        <div class="well">
            <div class="media">
                <div class="form-group">
                <div class="form-group col-md-6">
                    <form method="post" action="${pageContext.request.contextPath}/totalizator/scoreEvent">
                    <button type="submit" class="content-block_1">
                        <span class="link">
                            <fmt:message bundle="${loc}" key="link.total"/>
                        </span>
                    </button>
                    </form>
                </div>
                <div class="form-group col-md-6">
                    <form method="post" action="${pageContext.request.contextPath}/totalizator/teamEvent/football">
                        <button type="submit" class="content-block_2">
                        <span class="link">
                            <fmt:message bundle="${loc}" key="link.football"/>
                        </span>
                        </button>
                    </form>
                </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-md-6">
                        <form method="post" action="${pageContext.request.contextPath}/totalizator/teamEvent/hockey">
                            <button type="submit" class="content-block_3">
                         <span class="link">
                            <fmt:message bundle="${loc}" key="link.hockey"/>
                        </span>
                            </button>
                        </form>
                    </div>
                    <div class="form-group col-md-6">
                        <form method="post" action="${pageContext.request.contextPath}/totalizator/teamEvent/basketball">
                            <button type="submit" class="content-block_4">
                         <span class="link">
                            <fmt:message bundle="${loc}" key="link.basketball"/>
                        </span>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
