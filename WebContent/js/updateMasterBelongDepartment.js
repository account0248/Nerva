function fnOpenNormalDialog() {
	// Define the Dialog and its properties.
	$('#dialog-confirm').dialog({
		resizable: false,
		modal: true,
		title: "所属組織更新",
		height: 150,
		width: 400,
		buttons: {
			"更新する": function() {
				$(this).dialog('close');
				callback(true);
			},
			"更新しない": function() {
				$(this).dialog('close');
				callback(false);
			}
		}
	});
}

$('#updateDepartment').click(fnOpenNormalDialog);

function callback(value) {
	if (value) {
		pushUpdate(true);
	}
}

var pushUpdate = function(departmentUpdateFlg) {
	if (departmentUpdateFlg) {
		let departmentId = [];
		let departmentIdClass = document.getElementsByClassName('update-Id');
		let departmentName = [];
		let departmentNameClass = document.getElementsByClassName('update-vaule');
		for (let i = 0; i < departmentIdClass.length; i++) {
			departmentId.push(departmentIdClass.item(i).value);
			departmentName.push(departmentNameClass.item(i).value);
		}
		var departmentIdj = JSON.stringify(departmentId);
		var departmentNamej = JSON.stringify(departmentName);
		// リクエストJSON
		var request = {
			departmentId: departmentIdj,
			departmentName: departmentNamej
		};

		// ajaxでservletにリクエストを送信
		$.ajax({
			type: "POST", // GET / POST
			url: "/Nerva/UpdateDepartmentMstAction", // 送信先のServlet URL
			data: request, // リクエストJSON
			async: true, // true:非同期(デフォルト), false:同期
			dataType: 'json',
			data: {
				json: request
			},
		}).then(function(data) {
			if (data == "") {
				window.location.href = './jsp/error.jsp';
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
					$dcom.eq(i).val(departmentName[i]);
					$dcom.eq(i).removeClass('update-compare');
				}
			}
			return false;
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			window.location.href = './jsp/error.jsp';
		});
	} else {
		return false;
	}
}
