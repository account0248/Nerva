/**
 *
 */

var txtArea = "<input type=\"text\" value=\"\"style=\"width:100%;\">"

var notDifine =  "<input type=\"text\" value=\"\" disabled = \"desabled\">"
var companyName =  "<input type=\"text\" value=\"株式会社V\" disabled = \"desabled\">"
var myName =  "<input type=\"text\" value=\"山田　太郎\" disabled = \"desabled\">"


var selectTeam = "<select name=\"team\"><option value=\"\"></option><option value=\"本武チーム\">本武チーム</option><option value=\"辺鈴チーム\">辺鈴チーム</option><option value=\"清部チーム\">清部チーム</option><option value=\"酒福チーム\">酒福チーム</option><option value=\"川近チーム\">川近チーム</option></select>"
var selectRole = "<select name=\"team\"><option value=\"\"></option><option value=\リーダー\">リーダー</option><option value=\"メンバー\">メンバー</option></select>"
var contractType = "<select name=\"contractType\"><option value=\"\"></option><option value=\請負契約\">請負契約</option><option value=\"準委任契約\">準委任契約</option><option value=\"派遣契約\">派遣契約</option><option value=\"在籍出向\">在籍出向</option></select>"

	var createSkillData = function(row) {
	var cell1 = row.insertCell(-1);
	cell1.innerHTML = txtArea;

    var cell2 = row.insertCell(-1);
    cell2.innerHTML = txtArea

    var cell3 = row.insertCell(-1);
    cell3.innerHTML = txtArea;

    var cell4 = row.insertCell(-1);
    cell4.innerHTML = txtArea;

}


var createTeamData = function(row) {
	var cell1 = row.insertCell(-1);
	cell1.innerHTML = notDifine;

    var cell2 = row.insertCell(-1);
    cell2.innerHTML = notDifine;

    var cell3 = row.insertCell(-1);
    cell3.innerHTML = companyName;

    var cell4 = row.insertCell(-1);
    cell4.innerHTML = myName;

    var cell5 = row.insertCell(-1);
    cell5.innerHTML = selectTeam;

    var cell6 = row.insertCell(-1);
    cell6.innerHTML = selectRole;

    var cell7 = row.insertCell(-1);
    cell7.innerHTML = contractType;

    var cell8 = row.insertCell(-1);
    cell8.innerHTML = txtArea;

    var cell9 = row.insertCell(-1);
    cell9.innerHTML = txtArea;
    
    var cell10 = row.insertCell(-1);
    cell10.innerHTML = txtArea;
}


var addSkill = function() {
	var table = document.getElementById("skill");
	createSkillData(table.insertRow(-1));
};

var addTeam = function() {
	var table = document.getElementById("team");
	createTeamData(table.insertRow(-1));
};


var pushUpdate = function() {
	if (window.confirm('更新を実行します')) {
		window.location.href = "./detailEmployee.html"
	}
}

var pushDelete = function() {
	if (window.confirm('削除してよろしいですか？')) {
		window.location.href = "./searchEmployee.html"
	}
}