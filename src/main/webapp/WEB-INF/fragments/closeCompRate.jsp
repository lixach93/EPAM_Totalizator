<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

    <c:forEach items="${competitionRates}" var="competitionRate">
        <c:out value="${competitionRate.id}"/>
        <c:out value="${competitionRate.competition.firstOpponent.nameOpponent}"/>
        <c:out value="${competitionRate.competition.secondOpponent.nameOpponent}"/>
        </br>
        <button type="button" onclick="closeCompetitionRate(${competitionRate.id})">Payment</button>
        <div id="compRate${competitionRate.id}">

        </div>
    </c:forEach>

<script>

    function closeCompetitionRate(id) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("compRate"+id).innerText=xhttp.responseText;

            }
        };
        xhttp.open('POST','/totalizator',true);
        xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhttp.send('command=closeCompetitionRate&competitionRateId=' + id );
    }



</script>