<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

    <c:forEach items="${events}" var="event">
        <c:out value="${event.id}"/>
        <c:out value="${event.competition.firstTeam.nameTeam}"/>
        <c:out value="${event.competition.secondTeam.nameTeam}"/>
        </br>
        <button type="button" onclick="closeCompetitionRate(${event.id})">Payment</button>
        <div id="compRate${event.id}">

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