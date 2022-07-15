var options = {
	valueNames : [ 'employeeId', 'employeeName', "companyName", "projectName",
			"teamName", "teamManager" ]
};
var createResultEmp = function() {


	empDeleteError = document.getElementById("empDeleteError");
	empDeleteError.innerHTML = "";

	// リクエストJSON
	var request = {
		employeeId : document.getElementById("employeeId").value,
		employeeName : document.getElementById("employeeName").value,
		projectName : document.getElementById("projectName").value,
		teamName : document.getElementById("teamName").value,
		teamManager : document.getElementById("teamManager").value,
		companyName : document.getElementById("companyName").value,
		skillFiltering : document.getElementById("skillFiltering").value
	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type : "POST", // GET / POST
		url : "./SearchEmpAction", // 送信先のServlet URL
		data : request, // リクエストJSON
		async : true, // true:非同期(デフォルト), false:同期
		dataType : 'json',
		data : {
			json : request
		}
	}).done(function(data) {
 if(data == ""){
						window.location.href = 'error.jsp';
						}
		var table = document.getElementById("employee");
		while (table.rows[0])
			table.deleteRow(0);
		// 通信が成功した場合に受け取るメッセージ
		var serchEmpDTO = data["searchEmpDTOList"];
		var errorList = data["errorListDTO"];
		target = document.getElementById("errorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (var i = 0; i < errorList.length; i++) {
				target.innerHTML += errorList[i] + "<br>";
			}
		}
		// 検索結果があった場合は検索結果を表示
		if (serchEmpDTO != null) {
			for (var i = 0; i < serchEmpDTO.length; i++) {
				var empinfo = serchEmpDTO[i];
				createData(table.insertRow(-1), i + 1, empinfo);
			}

		}
		
		new List('sort', options);
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = 'error.jsp';

	});

}

var createData = function(row, num, empinfo) {
	var cell1 = row.insertCell(-1);

	cell1.innerHTML = "	<form method=\"POST\" action=\"SearchEmpSessionAction\"><input type=\"hidden\" name=\"employeeId\" id=\""
			+ empinfo.employeeId
			+ "\" value=\""
			+ empinfo.employeeId
			+ "\"><input type=\"submit\" value=\"詳細\"/></form>";

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = empinfo.employeeId
	$(cell2).addClass("employeeId");

	var cell3 = row.insertCell(-1);
	cell3.innerHTML = empinfo.employeeName;
	$(cell3).addClass("employeeName");

	var cell4 = row.insertCell(-1);
	cell4.innerHTML = empinfo.companyName;
	$(cell4).addClass("companyName");

	var cell5 = row.insertCell(-1);
	if (empinfo.projectName == "" || empinfo.projectName == null) {
		cell5.innerHTML = "-";
	} else {
		cell5.innerHTML = empinfo.projectName
	}
	$(cell5).addClass("projectName");

	var cell6 = row.insertCell(-1);
	if (empinfo.teamName == "" || empinfo.teamName == null) {
		cell6.innerHTML = "-";
	} else {
		cell6.innerHTML = empinfo.teamName
	}
	$(cell6).addClass("teamName");

	var cell7 = row.insertCell(-1);
	if (empinfo.teamManager == "" || empinfo.teamManager == null) {
		cell7.innerHTML = "-";
	} else {
		cell7.innerHTML = empinfo.teamManager
	}
	$(cell7).addClass("teamManager");
	
	var cell8 = row.insertCell(-1);
	cell8.innerHTML = "	<form method=\"POST\" action=\"/Nerva/jsp/ManagementAttendancePageAction\"><input type=\"hidden\" name=\"employeeId\""
			+ "\" value=\""
			+ empinfo.employeeId
			+ '"><input type="hidden" name="employeeName" value="'
			+ empinfo.employeeName
			+ "\"><input type=\"submit\" value=\"勤怠管理\"/></form>";
}

var createResultEmployee = function() {
	var table = document.getElementById("employee");

	for (var i = 1; i <= 100; i++) {
		createData(table.insertRow(-1));
	}
	new List('sort', options)
};
