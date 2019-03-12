function fillLeagues() {
    var categoryId = document.getElementById("category").value;
    var select = document.getElementById("league");
    var league = getLeagues(categoryId);
    createOptions(select, league);

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