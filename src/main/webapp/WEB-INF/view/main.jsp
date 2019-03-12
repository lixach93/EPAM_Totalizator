<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
    <div class="container own-container">
        <div class="content">
            <div class="rate">
                <form method="get" action="${pageContext.request.contextPath}/totalizator">
                    <input type="hidden" name="command" value="showEventPage">
                    <button type="submit" class="content-block_1">
                        <span class="link">
                            <fmt:message bundle="${loc}" key="link.total"/>
                        </span>
                    </button>
                </form>
            </div>
            <div class="rate">
                <form method="get" action="${pageContext.request.contextPath}/totalizator">
                    <input type="hidden" name="command" value="showEventPage">
                    <input type="hidden" name="category" value="football">
                    <button type="submit" class="content-block_2">
                        <span class="link">
                            <fmt:message bundle="${loc}" key="link.football"/>
                        </span>
                    </button>
                </form>
            </div>
            <div class="rate">
                <form method="get" action="${pageContext.request.contextPath}/totalizator">
                    <input type="hidden" name="command" value="showEventPage">
                    <input type="hidden" name="category" value="hockey">
                    <button type="submit" class="content-block_3">
                         <span class="link">
                            <fmt:message bundle="${loc}" key="link.hockey"/>
                        </span>
                    </button>
                </form>
            </div>
            <div class="rate">
                <form method="get" action="${pageContext.request.contextPath}/totalizator">
                    <input type="hidden" name="command" value="showEventPage">
                    <input type="hidden" name="category" value="basketball">
                    <button type="submit" class="content-block_4">
                         <span class="link">
                            <fmt:message bundle="${loc}" key="link.basketball"/>
                        </span>
                    </button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
