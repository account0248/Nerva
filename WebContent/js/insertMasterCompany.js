/**
 * 
 */var companyPushInsert = function() {
	// リクエストJSON
	var request = {
		companyId: document.getElementById("registCompanyId").value,
		companyName: document.getElementById("registCompanyName").value,
		companyGroup: document.getElementById("registCompanyGroup").value,
		companyCode: document.getElementById("registCompanyCode").value,
	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/InsertCompanyMstAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		}
	}).then(function(data) {
		var errorList = data["registErrorListDTO"];
		target = document.getElementById("registErrorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (var i = 0; i < errorList.length; i++) {
				target.innerHTML += errorList[i] + "<br>";
			}
		}
		else {
			var $registInput = $('#new-registory input[type=password],#new-registory input[type=text],#new-registory select');
			var $displayGrey = $('#display-grey');
			var $newRegistory = $('#new-registory');
			$('#registCompanyCode > option:selected').remove();
			$displayGrey.hide();
			$($registInput).val("");
			$($registInput).addClass('required4');
			$newRegistory.hide();
		}
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './jsp/error.jsp';
	});
}

let insertCompanyCallBack = function(value) {
	companyPushInsert();
};


var fnOpenNormalDialog2 = function() {
	// Define the Dialog and its properties.
	$("#dialog-confirm-insert-masterCompany").dialog({
		resizable: false,
		modal: true,
		title: "所属会社マスタメンテナンス",
		height: 150,
		width: 400,
		buttons: {
			"登録する": function() {
				$(this).dialog('close');
				insertCompanyCallBack(true);
			},
			"登録しない": function() {
				$(this).dialog('close');
				insertCompanyCallBack(false);
			}
		}
	});
}

$('#btnRegistDialog').click(() => {
	fnOpenNormalDialog2();
});
