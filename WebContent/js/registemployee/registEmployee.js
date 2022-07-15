function fnOpenNormalDialog() {

	// Define the Dialog and its properties.
	$("#dialog-confirm-regist-employee").dialog({
		resizable: false,
		modal: true,
		title: "従業員登録画面",
		height: 150,
		width: 400,
		buttons: {
			"登録する": function() {
				$(this).dialog('close');
				callback(true);
			},
			"登録しない": function() {
				$(this).dialog('close');
				callback(false);
			}
		}
	});
}

$('#btnRegistDialog').click(fnOpenNormalDialog);

function callback(value) {
	if (value) {
		pushRegist(true);
	}
}

var pushRegist = function(employeeRegistFlg) {
	if (employeeRegistFlg) {

		// リクエストJSON
		var request = {
			employeeId: document.getElementById("employeeId").value,
			employeeName: document.getElementById("employeeName").value,
			sex: document.getElementById("sex").value,
			birthDate: document.getElementById("birthDate").value,
			companyId: document.getElementById("companyId").value,
			office: document.getElementById("office").value,
			departmentId: document.getElementById("departmentId").value,
			postId: document.getElementById("postId").value,
			postalCode: document.getElementById("postalCode").value,
			address: document.getElementById("address").value,
			phoneNumber: document.getElementById("phoneNumber").value,
			mail: document.getElementById("mail").value	
		};
		
		
		
		if ($("#skill").children().children().first() != null) {
			// スキル欄に項目がある場合はリクエストに追加
			skillDataList = getSkillData();
			request.skillType = skillDataList.skillTypeList;
			request.skillDetail = skillDataList.skillDetailList;
			request.experienceYears = skillDataList.experienceYearsList;
			request.acquisitionYearMonth = skillDataList.acquisitionYearMonthList;
		}
		;
		// ajaxでservletにリクエストを送信
		$.ajax({
			type: "POST", // GET / POST
			url: "./RegistEmpCheckAction", // 送信先のServlet URL
			data: request, // リクエストJSON
			async: true, // true:非同期(デフォルト), false:同期
			dataType: 'json',
			data: {
				json: request

			},

		}).then(function(data) {
			if (data == "") {
				window.location.href = 'error.jsp';
			}
			var errorList = data["errorListDTO"];
			target = document.getElementById("errorList");
			target.innerHTML = "";
			// エラーがあった場合はエラーメッセージを表示
			if (errorList != null) {
				for (var i = 0; i < errorList.length; i++) {
					target.innerHTML += errorList[i] + "<br>";
				}
				scrollTo(0, 0);
				return false;
			}
			// エラーが無い場合登録処理を行う。
			if (errorList == null) {
				document.registEmpAction.submit();
				return true;
			}
		}, function(XMLHttpRequest, textStatus, errorThrown) {
			window.location.href = 'error.jsp';
			//return false;
		});
	} else {
		if (data == "") {
			window.location.href = 'error.jsp';
		}
		return false;
	}
}

// スキル情報を配列にしてもらってくる
var getSkillData = function() {
	var skillTypeArray = $('select[name="skillType[]"]').map(function() {
		return this.value
	}).get()

	var skillDetailArray = $('input[name="skillDetail[]"]').map(function() {
		return this.value
	}).get()

	var experienceYearsArray = $('input[name="experienceYears[]"]').map(
		function() {
			return this.value
		}).get()

	var acquisitionYearMonthArray = $('input[name="acquisitionYearMonth[]"]')
		.map(function() {
			return this.value
		}).get()

	var skillDataList = {
		skillTypeList: skillTypeArray,
		skillDetailList: skillDetailArray,
		experienceYearsList: experienceYearsArray,
		acquisitionYearMonthList: acquisitionYearMonthArray
	};
	return skillDataList;
}

var changePlaceholder = function() {
	
	
	 let companyId = document.getElementById("companyId").value;	
	 let companyCode =document.getElementById(companyId).value;

	if (companyCode != null) {
		employeeId.placeholder =companyCode+"000000000";
	}

}