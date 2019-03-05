<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form method="post" action="${pageContext.request.contextPath}/totalizator">
    <label for="category">Выберете категорию</label>
    <select id="category" name="categoryId" onchange="fillLeagues()">
        <option value="0">Some text</option>
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}">${category.nameCategory}</option>
        </c:forEach>
    </select>
    <label for="league">Выберете категорию</label>
    <select id="league" name="leagueId">
        <option value="0">Some text</option>
    </select>

    <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showAddTeamPage" />">
    <input type="text" name="teamName" value=" some text "/>
    <input type="hidden" name="command" value="addTeam"/>
    <p><input type="submit" value="Отправить"></p>
</form>

<script>
    function fillLeagues() {
        var categoryId = document.getElementById("category").value;
        var select = document.getElementById("league");
        var league = getLeagues(categoryId,league);
        createOptions(select, league);

    }
    function getLeagues(categoryId) {
        var xhttp = new XMLHttpRequest();
        xhttp.open('POST','/totalizator',false);
        xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhttp.send('command=getLeaguesByCategoryId&categoryId=' + categoryId);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            return JSON.parse(xhttp.responseText);
        }
    }
    function createOptions(select, league){
        setDefaultSelect(select);
        for (var i = 0; i < league.length; i++){
            var item = league[i];
            var id = item["id"];
            var name = item["name"];
            var opt = new Option(name, id);
            select.appendChild(opt);
        }
    }
    function setDefaultSelect(select) {
        var opts = select.options;
        if(opts) {
            var length = opts.length;
            for (i = length - 1; i >=0; i--) {
                select.remove(i)
            }
        }
    }
    
</script>
