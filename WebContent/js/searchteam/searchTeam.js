var options = {
	valueNames : [ 'teamId', 'teamName', 'belongCompanyName',
			'teamManagerName', 'teamLeaderName', 'projectName',
			'totalBelongMember' ]
};

var createData = function(row, num, teamInfo) {

	var cell1 = row.insertCell(-1);

	cell1.innerHTML = "	<form method=\"POST\" action=\"SearchTeamSessionAction\"><input type=\"hidden\" name=\"teamDetailName\" id=\""
			+ teamInfo.teamId
			+ "\" value=\""
			+ teamInfo.teamId
			+ "\"><input type=\"submit\" value=\"詳細\"/></form>";

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = teamInfo.teamId;
	$(cell2).addClass("teamId");

	var cell3 = row.insertCell(-1);

	cell3.innerHTML = teamInfo.teamName;
	$(cell3).addClass("teamName");

	var cell4 = row.insertCell(-1);
	cell4.innerHTML = teamInfo.belongCompanyName;
	$(cell4).addClass("belongCompanyName");

	var cell5 = row.insertCell(-1);
	cell5.innerHTML = teamInfo.teamManagerName;
	$(cell5).addClass("teamManagerName");

	var cell6 = row.insertCell(-1);
	if (teamInfo.teamLeaderName != null) {
		cell6.innerHTML = teamInfo.teamLeaderName;
	}
	if (teamInfo.teamLeaderName == null) {
		cell6.innerHTML = "-";
	}
	$(cell6).addClass("teamLeaderName");

	var cell7 = row.insertCell(-1);
	if (teamInfo.projectName != null) {
		cell7.innerHTML = teamInfo.projectName;
	}
	if (teamInfo.projectName == null) {
		cell7.innerHTML = "-";
	}
	$(cell7).addClass("projectName");

	var cell8 = row.insertCell(-1);
	cell8.innerHTML = teamInfo.totalBelongMember;
	$(cell8).addClass("totalBelongMember");

	var cell9 = row.insertCell(-1);
	cell9.innerHTML = "<input type=\"button\" value=\"削除\" onclick=\"pushDelete(this,this.parentNode.parentNode);\" id=\""+ teamInfo.teamId +"\" />";
}

var createResultTeam = function() {
	// リクエストJSON
	var request = {
		teamId : document.getElementById("teamId").value,
		teamName : document.getElementById("teamName").value,
		teamLeader : document.getElementById("teamLeader").value,
		projectId : document.getElementById("projectId").value,
		projectName : document.getElementById("projectName").value,
		orderSource : document.getElementById("orderSource").value
	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type : "POST", // GET / POST
		url : "./SearchTeamAction", // 送信先のServlet URL
		data : request, // リクエストJSON
		async : true, // true:非同期(デフォルト), false:同期
		dataType : 'json',
		data : {
			json : request
		},
	}).then(function(data) {
		 if(data == ""){
						window.location.href = 'error.jsp';
						}
		// テーブル初期化
		var table = document.getElementById("team");
		while (table.rows[0])
			table.deleteRow(0);
		// 通信が成功した場合に受け取るメッセージ
		var searchTeamDTO = data["searchTeamList"];
		var errorList = data["errorListDTO"];
		target = document.getElementById("errorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (var i = 0; i < errorList.length; i++) {
				target.innerHTML += errorList[i] + "<br>";
			}
			scrollTo(0, 0);
		}
		// 検索結果があった場合は検索結果を表示
		if (searchTeamDTO != null) {
			for (var i = 0; i < searchTeamDTO.length; i++) {
				var teamInfo = searchTeamDTO[i];
				createData(table.insertRow(-1), i + 1, teamInfo);
			}
			new List('sort', options);
		}
	}, function(XMLHttpRequest, textStatus, errorThrown) {
	window.location.href = 'error.jsp';
	});
}
