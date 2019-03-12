<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form method="post" action="${pageContext.request.contextPath}/totalizator">
    <label for="category">Выберете категорию</label>
    <select id="category" name="categoryId" onchange="fillLeagues()">
        <option value="0">Выберите категорию</option>
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}">${category.nameCategory}</option>
        </c:forEach>
    </select>
    <label for="league">Выберете лигу первой команды</label>
    <select id="league" name="leagueId" onchange="fillOpponent()">
        <option value="0">Выберите лигу</option>
    </select>

    <label for="league2">Выберете лигу второй команды</label>
    <select id="league2" name="leagueId" onchange="fillSecondOpponent()">
        <option value="0" >Выберите лигу</option>
    </select>

    <label for="teamOne">Выберете первой команды</label>
    <select id="teamOne" name="opponentOneId">
        <option value="0">Выберете команду</option>
    </select>

    <label for="teamTwo">Выберете второй команды</label>
    <select id="teamTwo" name="opponentTwoId">
        <option value="0">Выберете команду</option>
    </select>
    <input type="hidden" name="redirect" value="<c:url value="/totalizator?command=showCreateCompetitionPage" />">
    <label class="radio-inline"><input type="radio" name="rate" value="team" checked>Team </label>
    <label class="radio-inline"><input type="radio" name="rate" value="total">Total</label>
    <label class="radio-inline"><input type="radio" name="rate" value="all">Обе</label>
    <input type="hidden" name="command" value="createCompetition"/>
    <p><input type="submit" value="Отправить"></p>
</form>

<script>

    function fillOpponent() {
        var leagueIdOne = document.getElementById("league").value;
        var selectOne =   document.getElementById("teamOne");
        if(leagueIdOne > 0){
            var team = getOpponents(leagueIdOne);
            createOptions(selectOne, team);
        } else {
            setDefaultSelect(selectOne);
        }

    }

    function fillSecondOpponent() {
        var leagueIdTwo = document.getElementById("league2").value;
        var selectTwo =   document.getElementById("teamTwo");
        if(leagueIdTwo > 0){
            var team = getOpponents(leagueIdTwo);
            createOptions(selectTwo, team);
         } else {
            setDefaultSelect(selectTwo);
        }
    }

    function getOpponents(leagueId) {
        var xhttp = new XMLHttpRequest();
        xhttp.open('POST','/totalizator',false);
        xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhttp.send('command=getOpponentsByLeagueId&leagueId=' + leagueId);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            return JSON.parse(xhttp.responseText);
        }
    }

    function fillLeagues() {
        var categoryId = document.getElementById("category").value;
        var selectOne = document.getElementById("league");
        var selectTwo = document.getElementById("league2");
        var selectThree = document.getElementById("teamOne");
        var selectFour = document.getElementById("teamTwo");
        if(categoryId > 0){
            var league = getLeagues(categoryId);
            createOptions(selectOne, league);
            createOptions(selectTwo, league);
            setDefaultSelect(selectThree);
            setDefaultSelect(selectFour);
        }else{
            setDefaultSelect(selectOne);
            setDefaultSelect(selectTwo);
            setDefaultSelect(selectThree);
            setDefaultSelect(selectFour);
        }



    }
    function getLeagues(categoryId) {
        var xhttp = new XMLHttpRequest();
        xhttp.open('POST','/totalizator',false);
        xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhttp.send('command=getLeaguesByCategory&categoryId=' + categoryId);
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
            for (i = length - 1; i >0; i--) {
                select.remove(i)
            }
        }

    }

</script>
