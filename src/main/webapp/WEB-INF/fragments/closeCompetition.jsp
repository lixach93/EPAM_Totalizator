<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <c:forEach items="${requestScope.competitions}" var="competition">
        <c:out value="${competition.id}"/>
        <c:out value="${competition.firstOpponent.nameOpponent}"/>
        <c:out value="${competition.secondOpponent.nameOpponent}"/>
        <c:out value="${competition.firstOpponentResult}"/>
        <c:out value="${competition.secondOpponentResult}"/>
        <button type="button" onclick="closeCompetition(${competition.id})">Закрыть соревнование</button>
        <div id="info${competition.id}">

        </div>
    </c:forEach>



<script>

    function closeCompetition(id) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("info"+id).innerText=xhttp.responseText;

            }
        };
        xhttp.open('POST','/totalizator',true);
        xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhttp.send('command=closeCompetition&competitionId=' + id);
    }



</script>