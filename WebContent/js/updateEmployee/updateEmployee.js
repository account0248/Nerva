// リクエストJSONの定義
var requestJson = {
	employeeInfo: null,
	updateSkill: [],
	updateSkillRow: "0",
	registSkill: [],
	registSkillRow: "0",
	updateWorkExp: [],
	updateWorkExpRow: "0",
	registWorkExp: [],
	registWorkExpRow: "0",
	workExpDate: []
};

var updateSkillMap = new Map();
var updateWorkExpMap = new Map();

// 保有スキル欄が更新された場合
var changeSkill = function(data) {
	// 更新された行番号の取得
	var updateSkillRow = $(data).parent().parent().index()
	// スキル情報IDを取得
	var skillInfoId = document.getElementById('skillInfoId_' + updateSkillRow).value;
	// スキルタイプを取得
	var skillTypeId = document.getElementById('updateSkillType_' + updateSkillRow).value;
	// スキル内容を取得
	var skillDetail = document.getElementById('updateSkillDetail_' + updateSkillRow).value;
	// 経験年数を取得
	var experienceYears = document.getElementById('updateExperienceYears_' + updateSkillRow).value;
	// 取得年月を取得
	var acquisitionYearMonth = document.getElementById('updateAcquisitionYearMonth_' + updateSkillRow).value;
	// 保有スキル更新用のマップにセット
	updateSkillMap.set(skillInfoId, [skillTypeId, skillDetail,experienceYears, acquisitionYearMonth]);
}

// 業務経験欄が更新された場合の処理
var changeWorkExp = function(data) {
	// 更新された業務経験欄の行番号を取得
	var updateWorkExpRow = $(data).parent().parent().index();
	// 従業員業務経験IDを取得
	var employeeExperienceId = document
		.getElementById('updateEmployeeExperienceId_' + updateWorkExpRow).value;
	// 所属開始日を取得
	var teamBelongStartDate = document
		.getElementById('updateTeamBelongStartDate_' + updateWorkExpRow).value;
	// 所属完了日を取得
	var teamBelongCompleteDate = document
		.getElementById('updateTeamBelongCompleteDate_' + updateWorkExpRow).value;

	// 業務経験更新用のマップにセット
	updateWorkExpMap.set(employeeExperienceId, [teamBelongStartDate,
		teamBelongCompleteDate]);

}

function fnOpenNormalDialog() {

	// Define the Dialog and its properties.
	$("#dialog-confirm-employee-update").dialog({
		resizable: false,
		modal: true,
		title: "従業員更新画面",
		height: 150,
		width: 400,
		buttons: {
			"更新する": function() {
				$(this).dialog('close');
				callbackUpdate(true);
			},
			"更新しない": function() {
				$(this).dialog('close');
				callbackUpdate(false);
			}
		}
	});
}

$('#openUpdateDialog').click(fnOpenNormalDialog);

function callbackUpdate(value) {
	if (value) {
		console.log(value);
		UpdateEmployee(true);
	}
}

// 更新ボタンが押された際の処理
var UpdateEmployee = function(dialogUpdateFlg) {
	if (dialogUpdateFlg) {
		// 従業員情報をリクエストJSONにセット
		requestJson["employeeInfo"] = ({
			employeeName: document.getElementById("employeeName").value,
			office: document.getElementById("office").value,
			departmentId: document.getElementById("departmentId").value,
			postId: document.getElementById("postId").value,
			postalCode: document.getElementById("postalCode").value,
			address: document.getElementById("address").value,
			phoneNumber: document.getElementById("phoneNumber").value,
			mail: document.getElementById("mail").value
		});

		// 保有スキル更新欄の行数をリクエストJSONに追加
		requestJson["updateSkillRow"] = updateSkillMap.size;
		// リクエストJSONに追加
		updateSkillMap.forEach(function(value, key) {
			requestJson['updateSkill'].push({
				"skillInfoId": key,
				"skillTypeId": value[0],
				"skillDetail": value[1],
				"experienceYears": value[2],
				"acquisitionYearMonth": value[3]
			});
		});

		// 業務経験更新欄の行数をリクエストJSONにセット
		requestJson["updateWorkExpRow"] = updateWorkExpMap.size;
		// リクエストJSONに追加
		updateWorkExpMap.forEach(function(value, key) {
			requestJson['updateWorkExp'].push({
				"employeeExperienceId": key,
				"teamBelongStartDate": value[0],
				"teamBelongCompleteDate": value[1]
			});
		});

		// 保有スキル登録欄の行数を取得
		var registSkillRow = document.getElementsByClassName("registSelectedSkill").length;
		if (registSkillRow != 0) {
			requestJson["registSkillRow"] = registSkillRow;
			var registSkillTypeList = document
				.getElementsByName("registSkillType");
			var registSkillDetailList = document
				.getElementsByName("registSkillDetail");
			var registExperienceYearsList = document
				.getElementsByName("registExperienceYears");
			var registAcquisitionYearMonthList = document
				.getElementsByName("registAcquisitionYearMonth");
			// 保有スキル登録欄の行数分リクエストJSONに追加
			for (var i = 0; i < registSkillRow; i++) {
				requestJson["registSkill"].push({
					"skillTypeId": registSkillTypeList.item(i).value,
					"skillDetail": registSkillDetailList.item(i).value,
					"experienceYears": registExperienceYearsList
						.item(i).value,
					"acquisitionYearMonth": registAcquisitionYearMonthList
						.item(i).value
				});
			}
		}
		// 業務経験登録欄の行数を取得
		var registWorkExpRow = document.getElementsByClassName("registTeam").length;
		if (registWorkExpRow != 0) {
			requestJson["registWorkExpRow"] = registWorkExpRow;
			var registTeamList = document.getElementsByName("registTeam");
			var registRoleList = document.getElementsByName("registRole");
			var registContractTypeList = document
				.getElementsByName("registContractType");
			var registMonthlyUnitPriceList = document
				.getElementsByName("registMonthlyUnitPrice");
			var registTeamBelongStartDateList = document
				.getElementsByName("registTeamBelongStartDate");
			var registTeamBelongCompleteDateList = document
				.getElementsByName("registTeamBelongCompleteDate");
			// 業務経験登録欄の行数分リクエストJSONに追加
			for (var i = 0; i < registWorkExpRow; i++) {
				var str = registTeamBelongStartDateList.item(i).value;
				requestJson["registWorkExp"]
					.push({
						"teamId": registTeamList.item(i).value,
						"roleId": registRoleList.item(i).value,
						"contractTypeId": registContractTypeList.item(i).value,
						"monthlyUnitPrice": registMonthlyUnitPriceList
							.item(i).value,
						"teamBelongStartDate": registTeamBelongStartDateList
							.item(i).value,
						"teamBelongCompleteDate": registTeamBelongCompleteDateList
							.item(i).value
					});
			}
		}
		// 業務経験欄の日付情報を取得
		// 業務経験更新欄の行数を取得
		var updateWorkExpRow = document
			.getElementsByClassName("updateTeamBelongCompleteDate").length;
		// 業務経験欄の行数
		if (updateWorkExpRow != 0) {
			var updateTeamBelongStartDateList = document
				.getElementsByName("updateTeamBelongStartDate");
			var updateTeamBelongCompleteDateList = document
				.getElementsByName("updateTeamBelongCompleteDate");
			for (var i = 0; i < updateWorkExpRow; i++) {
				requestJson["workExpDate"]
					.push({
						"teamBelongStartDate": updateTeamBelongStartDateList[i].value,
						"teamBelongCompleteDate": updateTeamBelongCompleteDateList[i].value
					});
			}
		}
		if (registWorkExpRow != 0) {
			var registTeamBelongStartDateList = document
				.getElementsByName("registTeamBelongStartDate");
			var registTeamBelongCompleteDateList = document
				.getElementsByName("registTeamBelongCompleteDate");
			for (var i = 0; i < registWorkExpRow; i++) {
				requestJson["workExpDate"]
					.push({
						"teamBelongStartDate": registTeamBelongStartDateList[i].value,
						"teamBelongCompleteDate": registTeamBelongCompleteDateList[i].value
					});
			}
		}

		// 非同期通信で入力内容チェックを行う
		$.ajax({
			type: "POST", // GET / POST
			url: "./UpdateEmpCheckAction", // 送信先のServlet URL
			data: requestJson, // リクエストJSON
			async: true, // true:非同期(デフォルト), false:同期
			dataType: 'json',
			timeout: 100000,
			data: {
				json: requestJson
			},
			success: function(data) {
				//jsonのデータが空の場合
				if (data == "") {
					//エラー画面に遷移
					window.location.href = 'error.jsp';
				}
				// テーブル初期化
				targetErrorMsg = document.getElementById("errorMsg");
				targetErrorMsg.innerHTML = "";
				var errorList = [];
				Object.keys(data).forEach(function(value) {
					errorList.push(this[value]);
				}, data);
				// エラーがあった場合はエラーメッセージを表示
				if (errorList.length != 0) {
					for (var i = 0; i < errorList.length; i++) {
						targetErrorMsg.innerHTML += errorList[i] + "<br>";
					}
					// リクエストJSONの初期化
					requestJson = {
						employeeInfo: null,
						updateSkill: [],
						updateSkillRow: "0",
						registSkill: [],
						registSkillRow: "0",
						updateWorkExp: [],
						updateWorkExpRow: "0",
						registWorkExp: [],
						registWorkExpRow: "0",
						workExpDate: []
					};
					scrollTo(0, 0);
					return false;
				}
				// エラーが無い場合登録処理を行う。
				if (errorList.length == 0) {
					document.UpdateEmp.submit();
					return true;
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				//エラー画面に遷移
				window.location.href = 'error.jsp';
				return false;
			}
		});
	}
}