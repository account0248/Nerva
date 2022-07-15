function fnOpenNormalDialog() {
	// Define the Dialog and its properties.
	$('#dialog-confirm').dialog({
		resizable: false,
		modal: true,
		title: "所属会社マスタメンテナンス",
		height: 150,
		width: 400,
		buttons: {
			"更新する": function() {
				$(this).dialog('close');
				updateCompanyCallback(true);
			},
			"更新しない": function() {
				$(this).dialog('close');
				updateCompanyCallback(false);
			}
		}
	});
}

$('#updateCompany').click(fnOpenNormalDialog);

function updateCompanyCallback(value) {
	if (value) {
		companyPushUpdate(true);
	}
}

var companyPushUpdate = function(companyUpdateFlg) {
	if (companyUpdateFlg) {
		let companyId = [];
		let companyIdClass = document.getElementsByClassName('update-Id');
		let companyName = [];
		let companyNameClass = document.getElementsByClassName('update-vaule');
		for (let i = 0; i < companyIdClass.length; i++) {
			companyId.push(companyIdClass.item(i).value);
			companyName.push(companyNameClass.item(i).value);
		}
		var companyIdj = JSON.stringify(companyId);
		var companyNamej = JSON.stringify(companyName);
		// リクエストJSON
		var request = {
			companyId: companyIdj,
			companyName: companyNamej
		};
		// ajaxでservletにリクエストを送信
		$.ajax({
			type: "POST", // GET / POST
			url: "/Nerva/UpdateCompanyMstAction", // 送信先のServlet URL
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
			if (errorList) {
				for (var i = 0; i < errorList.length; i++) {
					target.innerHTML += errorList[i] + "<br>";
				}
				scrollTo(0, 0);
			}
			else{
				let $did = $('.update-Id');
				let $dname = $('.update-vaule');
				let $dtx = $('.text-updated');
				let $dcom = $('.update-compare');
				for (let i = 0; i < $dtx.length; i++) {
					$did.eq(i).removeClass('update-Id');
					$dname.eq(i).removeClass('update-vaule');
					$dtx.eq(i).removeClass('text-updated');
					$dcom.eq(i).val(companyName[i]);
					$dcom.eq(i).removeClass('update-compare');
				}
			
			}
			
			return false;
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			window.location.href = 'error.jsp';
		});
	} else {
		return false;
	}
}
