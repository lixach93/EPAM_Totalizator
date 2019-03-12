<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach items="${events}" var="event">
    <c:out value="${event.id}"/>
    <c:out value="${event.competition.firstTeam.nameTeam}"/>
    <c:out value="${event.competition.secondTeam.nameTeam}"/>
    <label for="percent${event.id}">Процент</label>
    <input id="percent${event.id}" type="text" name="percent" size="3" placeholder="0">
    <button type="button" onclick="addPercentForEvent(${event.id})">Add</button>
    <div id="compRate${event.id}">

    </div>
</c:forEach>

<script>

    function addPercentForEvent(id) {
        var percent = document.getElementById("percent" + id).value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("compRate"+id).innerText=xhttp.responseText;

            }
        };
        xhttp.open('POST','/totalizator',true);
        xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhttp.send('command=addPercentToEvent&eventId=' + id + '&percent=' + percent );
    }



</script>