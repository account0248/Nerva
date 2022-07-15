// 追加する行のプロパティを設定
var addTeamAssignStartDate = "<input required class=\"required\" type=\"date\" value=\" \" name= \"addTeamAssignStartDate[]\">"
var addTeamAssignCompleteDate = "<input type=\"date\" value=\" \" name= \"addTeamAssignCompleteDate[]\">"

var addTeamId = "<input type=\"text\"  value=\" \" disabled = \"desabled\" style=\"text-align:center;\">"
var hiddenAddTeamId = "<input type=\"hidden\" name= \"addTeamId[]\" value=\"\" >"

var notDifine = "<input type=\"text\"  value=\" \" disabled = \"desabled\" style=\"text-align:center;\">"

var companyName = "<input type=\"text\" value=\"\" disabled = \"desabled\" style=\"text-align:center;\">"

var myName = "<input type=\"text\" value=\"\" disabled = \"desabled\" style=\"text-align:center;\">"

var deleteButton = "<input type=\"button\" value=\"削除\" onclick=\"deletePush( this.parentNode.parentNode )\">"

// 行を追加する
var createTeamData = function(row, teamData) {
	var cell1 = row.insertCell(-1);
	cell1.innerHTML = hiddenAddTeamId;

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = addTeamId;

	var cell3 = row.insertCell(-1);
	var aaa = ""
	for (var i = 0; i < teamData.length; i++) {
		aaa += "<option value=\"" + teamData[i].teamName + "\">"
			+ teamData[i].teamName + "</option>"
	}
	cell3.innerHTML = "<select required class=\"select required\" name=\"addTeamName\"><option value=\"\"></option>"
		+ aaa + "</select>";

	var cell4 = row.insertCell(-1);
	cell4.innerHTML = companyName;

	var cell5 = row.insertCell(-1);
	cell5.innerHTML = myName;

	var cell6 = row.insertCell(-1);
	cell6.innerHTML = notDifine;

	var cell7 = row.insertCell(-1);
	cell7.innerHTML = addTeamAssignStartDate;

	var cell8 = row.insertCell(-1);
	cell8.innerHTML = addTeamAssignCompleteDate;

	var cell9 = row.insertCell(-1);
	cell9.innerHTML = deleteButton;
}

var addTeamTable = function(teamData) {

	var table = document.getElementById("team");
	createTeamData(table.insertRow(-1), teamData);
	$('tr td').each(function(i) {
		$(this).attr('id', (i + 1));
	});

	// 選択オプションからチームリーダーとチームIDを表示する処理
	$(".select").change(
		function() {
			var selectIndex = $(this).prop("selectedIndex") - 1;
			var selectParentTdId = $(this).parent().attr("id");
			var selectVal = $(this).val();
			var targetHiddenTeamId = '#' + (parseInt(selectParentTdId) - 2)
				+ ' input';
			var targetTeamId = '#' + (parseInt(selectParentTdId) - 1)
				+ ' input';
			var targetTeamLeader = '#' + (parseInt(selectParentTdId) + 3)
				+ ' input';
			var targetTeamManager = '#' + (parseInt(selectParentTdId) + 2)
				+ ' input';
			var targetTeamCompany = '#' + (parseInt(selectParentTdId) + 1)
				+ ' input';
			if (selectIndex == -1) {
				$(targetHiddenTeamId).prop("value", null);
				$(targetTeamId).prop("value", null);
				$(targetTeamLeader).prop("value", null);
				$(targetTeamManager).prop("value", null);
				$(targetTeamCompany).prop("value", null);
			} else {
				$(targetHiddenTeamId).prop("value",
					teamData[selectIndex].teamId);
				$(targetTeamId).val(teamData[selectIndex].teamId);
				$(targetTeamLeader).prop("value",
					teamData[selectIndex].teamLeaderName);
				$(targetTeamManager).prop("value",
					teamData[selectIndex].teamManagerName);
				$(targetTeamCompany).prop("value",
					teamData[selectIndex].belongCompanyName);
			}
		});
}

var deletePush = function(id) {
	id.parentNode.removeChild(id);
}

var pushUpdate = function() {
	if (window.confirm('更新を実行します')) {
		return updateProject();
	}
	return false;
}
//-----------------------------------------------------------------------------
function fnOpenNormalDialog() {
	// Define the Dialog and its properties.
	$("#dialog-confirm-employee-update").dialog({
		resizable: false,
		modal: true,
		title: "案件更新画面",
		height: 150,
		width: 400,
		buttons: {
			"更新する": function() {
				$(this).dialog('close');
				callback1(true);
			},
			"更新しない": function() {
				$(this).dialog('close');
				callback1(false);
			}
		}
	});
}

$('#updateProject').click(fnOpenNormalDialog);

function callback1(value) {
	if (value) {
		updateProject();
	}
}
//-----------------------------------------------------------------------------



var pushDelete = function() {
	if (window.confirm('削除してよろしいですか？')) {
		return document.deleteProjectAction.submit();
	} else {
		return false;
	}
	return false;
}
//-----------------------------------------------------------------------------
function fnOpenNormalDialog2() {
	// Define the Dialog and its properties.
	$("#dialog-confirm-employee-delete").dialog({
		resizable: false,
		modal: true,
		title: "案件更新画面",
		height: 150,
		width: 400,
		buttons: {
			"削除する": function() {
				$(this).dialog('close');
				callback(true);
			},
			"削除しない": function() {
				$(this).dialog('close');
				callback(false);
			}
		}
	});
}

$('#deleteProject').click(fnOpenNormalDialog2);

function callback(value) {
	if (value) {
		return document.deleteProjectAction.submit();
	}
}
//-----------------------------------------------------------------------------

var addTeam = function() {

	// リクエストJSON
	var request = {};

	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "./RetrunAddableTeamListAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		},

		success: function(data) {

			//if json error 
			if (data == "") {
				window.location.href = 'error.jsp';
			}

			// テーブル初期化
			var teamData = data["addableTeam"];

			addTeamTable(teamData);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("通信に失敗しました。：" + textStatus + ":\n" + errorThrown);
			window.location.href = 'error.jsp';
		}
	});

}

var updateProject = function() {
	console.log("update処理")
	// 案件開始日
	var projectStartDate = $('input[name="projectStartDate"]').val();

	// 案件完了日
	var projectCompleteDate = $('input[name="projectCompleteDate"]').val();
	// 配属開始日
	var assignStartDate = $('input[name="assignStartDate[]"]').map(function() {
		return this.value
	}).get()
	// 配属完了日
	var assignCompleteDate = $('input[name="assignCompleteDate[]"]').map(
		function() {
			return this.value
		}).get()
	// 追加されたチームID addTeamId[]
	var addTeamId = $('input[name="addTeamId[]"]').map(function() {
		return this.value
	}).get()
	// 追加された配属開始日
	var addTeamAssignStartDate = $('input[name="addTeamAssignStartDate[]"]')
		.map(function() {
			return this.value
		}).get()
	// 追加された配属完了日
	var addTeamAssignCompleteDate = $(
		'input[name="addTeamAssignCompleteDate[]"]').map(function() {
			return this.value
		}).get()

	var request = {
		projectStartDate: projectStartDate,
		projectCompleteDate: projectCompleteDate,
		assignStartDate: assignStartDate,
		assignCompleteDate: assignCompleteDate,
		addTeamId: addTeamId,
		addTeamAssignStartDate: addTeamAssignStartDate,
		addTeamAssignCompleteDate: addTeamAssignCompleteDate
	};
	$
		.ajax({

			type: "POST",
			url: "./CheckUpdateProjectAction",
			data: request,
			async: true, // true:非同期(デフォルト), false:同期
			dataType: 'json',
			data: {
				json: request
			},
			success: function(data) {

				//if json error 
				if (data == "") {
					window.location.href = 'error.jsp';
				}

				var errorList = data["updateProjectErrorMsgJsonDTO"];
				target = document.getElementById("errorList");
				target.innerHTML = "";
				console.log(errorList);

				if (errorList == null || errorList == "") {

					document.doUpdateProject.submit();

				} else {
					for (var i = 0; i < errorList.length; i++) {
						target.innerHTML += errorList[i] + "<br>";
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				//alert("リクエスト時になんらかのエラーが発生しました：" + textStatus + ":\n"
				//+ errorThrown);
				window.location.href = 'error.jsp';
			}

		});

}
