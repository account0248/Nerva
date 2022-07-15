//業務経験のテーブルの要素にidを付与
window.addEventListener('load', function() {
	$('#empWorkExp tr td').each(function(i) {
		$(this).attr('id', 'updateWorkExpTd_' + i);
	});
	$('#empWorkExp tr td .employeeExperienceId').each(function(i) {
		$(this).attr('id', 'updateEmployeeExperienceId_' + i);
	});
	$('#empWorkExp tr td .updateTeamBelongStartDate').each(function(i) {
		$(this).attr('id', 'updateTeamBelongStartDate_' + i);
	});
	$('#empWorkExp tr td .updateTeamBelongCompleteDate').each(function(i) {
		$(this).attr('id', 'updateTeamBelongCompleteDate_' + i);
	});
});

// 業務経験欄追加ボタン押下時の処理。Ajaxでチーム情報を取得
var addTeamInfo = function() {
	// リクエストJSON
	var request = {};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type : "POST", // GET / POST
		url : "./ReturnTeamInfoAction", // 送信先のServlet URL
		data : request, // リクエストJSON
		async : true, // true:非同期(デフォルト), false:同期
		dataType : 'json',
		timeout : 1000,
		data : {
			json : request
		},
		success : function(data) {
			//jsonのデータが空の場合
			if(data == "") {
				//エラー画面に遷移
				window.location.href = 'error.jsp';
			}
			// テーブル初期化
			var teamInfo = data["listDTO"];
			if (teamInfo.length == 0) {
    			var errorMsg = document.getElementById("errorMsg");
				errorMsg.innerHTML = "参加できるチームがありません。";
			} else {
				addMasterInfo(teamInfo);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//エラー画面に遷移
			window.location.href = 'error.jsp';
		}
	});
}

// Ajaxでマスタ情報を取得
var addMasterInfo = function(teamInfo) {
	// リクエストJSON
	var request = {};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type : "POST", // GET / POST
		url : "./ReturnMasterTableInfoAction", // 送信先のServlet URL
		data : request, // リクエストJSON
		async : true, // true:非同期(デフォルト), false:同期
		dataType : 'json',
		timeout : 1000,
		data : {
			json : request
		},
		success : function(data) {
			//jsonのデータが空の場合
			if(data == "") {
				//エラー画面に遷移
				window.location.href = 'error.jsp';
			}
			// テーブル初期化
			var roleList = data["roleList"];
			var contractTypeList = data["contractTypeList"];
			addEmpExpTable(teamInfo, roleList, contractTypeList);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//エラー画面に遷移
			window.location.href = 'error.jsp';
		}
	});
}

// 業務経験欄追加用変数の定義
var responsibleProjectTag = "<input type=\"text\" class=\"empWorkExpInput registResponsibleProject\" value=\"\" size=\"40\" name=\"registResponsibleProject\" readonly style=\"background-color:#EEEEEE;\">";
//担当業種
var responsibleIndustryTag = "<input type=\"text\" class=\"empWorkExpInput registResponsibleIndustry\" value=\"\" name=\"registResponsibleIndustry\" size=\"4\" readonly style=\"background-color:#EEEEEE;\">";
//案件帰属会社
var projectBelongingCompanyTag = "<input type=\"text\" class=\"empWorkExpInput registProjectBelongingCompany\" value=\"\" name=\"registProjectBelongingCompany\"size=\"9\" readonly style=\"background-color:#EEEEEE;\">";
//担当部長
var responsibleManagerTag = "<input type=\"text\" class=\"empWorkExpInput registResponsibleManager\" value=\"\" name=\"registResponsibleManager\" size=\"9\" readonly style=\"background-color:#EEEEEE;\">";
var monthlyUnitPriceTag = "<input type=\"text\" class=\"empWorkExpInput registMonthlyUnitPrice\" value=\"\" name=\"registMonthlyUnitPrice\"  size=\"6\" readonly style=\"background-color:#FFFFFF;\">";
var teamBelongStartDate = " <input type=\"date\" class=\"empWorkExpInput registTeamBelongStartDate required\" name=\"registTeamBelongStartDate\" required>";
var teamBelongCompleteDate = " <input type=\"date\" class=\"empWorkExpInput registTeamBelongCompleteDate\" name=\"registTeamBelongCompleteDate\">";
var workExpDelete = "<input type=\"button\" class=\"button\" value=\"削除\" onclick=\"deletePush( this.parentNode.parentNode )\">";

// 業務経験欄の追加された行に対するid付与
var addEmpExpTable = function(teamInfo, roleList, contractTypeList) {
	var table = document.getElementById("empExp");
	createEmpExpData(table.insertRow(-1), teamInfo, roleList, contractTypeList);

	$('#empWorkExp tr td').each(function(i) {
		$(this).attr('id', 'registWorkExpTd_' + i);
	});
	$('#empWorkExp tr td .registResponsibleProject').each(function(i) {
		$(this).attr('id', 'registResponsibleProject_' + i);
	});
	$('#empWorkExp tr td .registResponsibleIndustry').each(function(i) {
		$(this).attr('id', 'registResponsibleIndustry_' + i);
	});
	$('#empWorkExp tr td .registProjectBelongingCompany').each(function(i) {
		$(this).attr('id', 'registProjectBelongingCompany_' + i);
	});
	$('#empWorkExp tr td .registResponsibleManager').each(function(i) {
		$(this).attr('id', 'registResponsibleManager_' + i);
	});
	$('#empWorkExp tr td .registTeam').each(function(i) {
		$(this).attr('id', 'registTeam_' + i);
	});
	$('#empWorkExp tr td .registRole').each(function(i) {
		$(this).attr('id', 'registRole_' + i);
	});
	$('#empWorkExp tr td .registContractType').each(function(i) {
		$(this).attr('id', 'registContractType_' + i);
	});
	$('#empWorkExp tr td .registMonthlyUnitPrice').each(function(i) {
		$(this).attr('id', 'registMonthlyUnitPrice_' + i);
	});
	$('#empWorkExp tr td .registTeamBelongStartDate').each(function(i) {
		$(this).attr('id', 'registTeamBelongStartDate_' + i);
	});
	$('#empWorkExp tr td .registTeamBelongCompleteDate').each(function(i) {
		$(this).attr('id', 'registTeamBelongCompleteDate_' + i);
	});

	// 契約形態が変更された時の処理
	$(".registContractType")
			.change(
					function() {
						var selectParentTdId = $(this).parent().attr("id");
						var selectVal = $(this).val();
						var monthlyUnitPrice = $(this).parent().parent().find(
								'.registMonthlyUnitPrice');
						var monthlyUnitPriceId = monthlyUnitPrice.parent()
								.attr('id')
						var targetContractMonthlyUnitPriceSelector = '#'
								+ monthlyUnitPriceId + ' input';
						var targetMonthlyUnitPriceSelector = '#'
								+ monthlyUnitPriceId + ' div';
						if (selectVal == "C000000001") {
							$(targetContractMonthlyUnitPriceSelector)
									.parent()
									.html(
											"<input type=\"text\" class=\"empWorkExpInput registMonthlyUnitPrice\" value=\"\xA50\" size=\"6\" name=\"registMonthlyUnitPrice\" readonly=\"true\" style=\"background-color:#EEEEEE;\">");
						} else {
							$(targetMonthlyUnitPriceSelector).parent().html(
									monthlyUnitPriceTag);
							$(targetContractMonthlyUnitPriceSelector).prop(
									"readonly", false);
							$(targetContractMonthlyUnitPriceSelector).prop(
									"style", "background-color:#FFFFFF");
							$(targetContractMonthlyUnitPriceSelector).val('')
						}
					});

	// チームが選択された時の処理
	$(".registTeam")
			.change(
					function() {
						// 選択されたチームの番号を取得
						var selectedIndex = $(this).prop("selectedIndex") - 1;
						var selectParentTdId = $(this).parent().attr("id");
						var responsibleProject = $(this).parent().parent()
								.find('.registResponsibleProject');
						var responsibleIndustry = $(this).parent().parent()
								.find('.registResponsibleIndustry');
						var projectBelongingCompany = $(this).parent().parent()
								.find('.registProjectBelongingCompany');
						var responsibleManager = $(this).parent().parent()
								.find('.registResponsibleManager');
						var responsibleProjectId = responsibleProject.parent()
								.attr('id');
						var responsibleIndustryId = responsibleIndustry
								.parent().attr('id');
						var projectBelongingCompanyId = projectBelongingCompany
								.parent().attr('id');
						var responsibleManagerId = responsibleManager.parent()
								.attr('id');
						// 選択されたチームの情報をセットする
						if (selectedIndex == -1) {
							var targetResponsibleProject = '#'
									+ responsibleProjectId + ' input';
							$(targetResponsibleProject).parent().html(
									responsibleProjectTag);

							var targetResponsibleIndustry = '#'
									+ responsibleIndustryId + ' input';
							$(targetResponsibleIndustry).parent().html(
									responsibleIndustryTag);

							var targetProjectBelongingCompany = '#'
									+ projectBelongingCompanyId + ' input';
							$(targetProjectBelongingCompany).parent().html(
									projectBelongingCompanyTag);

							var targetResponsibleManager = '#'
									+ responsibleManagerId + ' input';
							$(targetResponsibleManager).parent().html(
									responsibleManagerTag);
						} else if (selectedIndex != -1) {
							var targetResponsibleProject = '#'
									+ responsibleProjectId + ' input';
							$(targetResponsibleProject)
									.parent()
									.html(
											"<input type=\"text\" class=\"empWorkExpInput registResponsibleProject\" value=\""
													+ teamInfo[selectedIndex].projectName
													+ "\" name=\"registResponsibleProject\" readonly=\"true\"  size=\"40\"style=\"background-color:#EEEEEE;\">");
							var targetResponsibleIndustry = '#'
									+ responsibleIndustryId + ' input';
							$(targetResponsibleIndustry)
									.parent()
									.html(
											"<input type=\"text\" class=\"empWorkExpInput registResponsibleIndustry\" value=\""
													+ teamInfo[selectedIndex].responsibleIndustry
													+ "\" name=\"responsibleIndustry\" readonly=\"true\" size=\"4\" style=\"background-color:#EEEEEE;\">");
							var targetProjectBelongingCompany = '#'
									+ projectBelongingCompanyId + ' input';
							$(targetProjectBelongingCompany)
									.parent()
									.html(
											"<input type=\"text\" class=\"empWorkExpInput registProjectBelongingCompany\" value=\""
													+ teamInfo[selectedIndex].resposibleProjectCompany
													+ "\" name=\"resposibleProjectCompany\" readonly=\"true\"size=\"9\" style=\"background-color:#EEEEEE;\">");
							var targetResponsibleManager = '#'
									+ responsibleManagerId + ' input';
							$(targetResponsibleManager)
									.parent()
									.html(
											"<input type=\"text\" class=\"empWorkExpInput registResponsibleManager\" value=\""
													+ teamInfo[selectedIndex].teamManagerName
													+ "\" name=\"teamManagerName\" readonly=\"true\" size=\"9\" style=\"background-color:#EEEEEE;\">");
						}
					});
};

// 業務経験欄の行追加
var createEmpExpData = function(row, teamInfo, roleList, contractTypeList) {

	var cell1 = row.insertCell(-1);
	cell1.innerHTML = responsibleProjectTag;

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = responsibleIndustryTag;

	var cell3 = row.insertCell(-1);
	cell3.innerHTML = projectBelongingCompanyTag;

	var cell4 = row.insertCell(-1);
	cell4.innerHTML = responsibleManagerTag;

	// 所属チームのセレクトボックス生成
	var cell5 = row.insertCell(-1);
	var teamOption = "";
	for (var i = 0; i < teamInfo.length; i++) {
		teamOption += "<option value=\"" + teamInfo[i].teamId + "\">"
				+ teamInfo[i].teamName + "</option>"
	}
	cell5.innerHTML = "<select class=\"registTeam required\" style=\"width:130px\" name=\"registTeam\" required><option value=\"\"></option>"
			+ teamOption + "</select>";

	// 担当のセレクトボックス作成
	var cell6 = row.insertCell(-1);
	var roleOption = "";
	for (var i = 0; i < roleList.length; i++) {
		roleOption += "<option value=\"" + roleList[i].masterDataId + "\">"
				+ roleList[i].masterData + "</option>"
	}
	cell6.innerHTML = "<select class=\"registRole required\" style=\"width:110px\" name=\"registRole\" required><option value=\"\"></option>"
			+ roleOption + "</select>";

	// 契約形態のセレクトボックス作成
	var cell7 = row.insertCell(-1);
	var contractTypeOption = "";
	for (var i = 0; i < contractTypeList.length; i++) {
		contractTypeOption += "<option value=\""
				+ contractTypeList[i].masterDataId + "\">"
				+ contractTypeList[i].masterData + "</option>"
	}
	cell7.innerHTML = "<select class=\"registContractType required\" style=\"width:90px\" name=\"registContractType\" required><option value=\"\"></option>"
			+ contractTypeOption + "</select>";

	var cell8 = row.insertCell(-1);
	cell8.innerHTML = monthlyUnitPriceTag;

	var cell9 = row.insertCell(-1);
	cell9.innerHTML = teamBelongStartDate;

	var cell10 = row.insertCell(-1);
	cell10.innerHTML = teamBelongCompleteDate;

	var cell11 = row.insertCell(-1);
	cell11.innerHTML = workExpDelete;
}