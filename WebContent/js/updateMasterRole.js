function fnOpenNormalDialog() {
	// Define the Dialog and its properties.
	$('#dialog-confirm').dialog({
		resizable: false,
		modal: true,
		title: "担当マスタメンテナンス",
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

$('#updateRole').click(fnOpenNormalDialog);

function callback(value) {
	if (value) {
		pushUpdate(true);
	}
}

var pushUpdate = function(roleUpdateFlg) {
	if (roleUpdateFlg) {
		let roleId = [];
		let roleIdClass = document.getElementsByClassName('update-Id');
		let roleName = [];
		let roleNameClass = document.getElementsByClassName('update-vaule');
		for (let i = 0; i < roleIdClass.length; i++) {
			roleId.push(roleIdClass.item(i).value);
			roleName.push(roleNameClass.item(i).value);
		}
		var roleIdj = JSON.stringify(roleId);
		var roleNamej = JSON.stringify(roleName);
		// リクエストJSON
		var request = {
			roleId: roleIdj,
			roleName: roleNamej
		};

		// ajaxでservletにリクエストを送信
		$.ajax({
			type: "POST", // GET / POST
			url: "/Nerva/UpdateRoleMstAction", // 送信先のServlet URL
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
					$dcom.eq(i).val(roleName[i]);
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
