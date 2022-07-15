function updateSkillTypeCallback(value) {
	if (value) {
		skillTypePushUpdate(true);
	}
}

function fnOpenNormalDialog() {
	// Define the Dialog and its properties.
	$('#dialog-confirm').dialog({
		resizable: false,
		modal: true,
		title: "スキル種別更新",
		height: 150,
		width: 400,
		buttons: {
			"更新する": function() {
				$(this).dialog('close');
				updateSkillTypeCallback(true);
			},
			"更新しない": function() {
				$(this).dialog('close');
				updateSkillTypeCallback(false);
			}
		}
	});
}

$('#updateSkillType').click(fnOpenNormalDialog);

var skillTypePushUpdate = function(skillTypeUpdateFlg) {
	if (skillTypeUpdateFlg) {
		//更新用cssが付与されたものを取得、リスト宣言
		let skillTypeId = [];
		let skillTypeIdClass = document.getElementsByClassName('update-Id');
		let skillTypeName = [];
		let skillTypeNameClass = document.getElementsByClassName('update-vaule1');
		let yearsDateOfAcquisition = [];
		let yearsDateOfAcquisitionClass = document.getElementsByClassName('update-vaule2');
		//更新用cssが付与されたもののvalueをリストで取得
		for (let i = 0; i < skillTypeIdClass.length; i++) {
			skillTypeId.push(skillTypeIdClass.item(i).value);
			skillTypeName.push(skillTypeNameClass.item(i).value);
			yearsDateOfAcquisition.push(yearsDateOfAcquisitionClass.item(i).value);
		}
		//リストで取得したvalueを1つのJSON文字列に変換
		var skillTypeIdj = JSON.stringify(skillTypeId);
		var skillTypeNamej = JSON.stringify(skillTypeName);
		var yearsDateOfAcquisitionj = JSON.stringify(yearsDateOfAcquisition);
		// リクエストJSON
		var request = {
			skillTypeId: skillTypeIdj,
			skillTypeName: skillTypeNamej,
			yearsDateOfAcquisition: yearsDateOfAcquisitionj
		};

		// ajaxでservletにリクエストを送信
		$.ajax({
			type: "POST", // GET / POST
			url: "/Nerva/UpdateSkillTypeMstAction", // 送信先のServlet URL
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
			// エラーがない場合は更新時に付与したcss削除し、valueの中身に更新後の値を格納
			else {
				let $did = $('.update-Id');
				let $dname = $('.update-vaule1');
				let $dyearsDateOfAcquisition = $('.update-vaule2');
				let $dtx = $('.text-updated');
				let $dcom1 = $('.update-compare1');
				let $dcom2 = $('.update-compare2');
				for (let i = 0; i < $dtx.length; i++) {
					$did.eq(i).removeClass('update-Id');
					$dname.eq(i).removeClass('update-vaule1');
					$dyearsDateOfAcquisition.eq(i).removeClass('update-vaule2');
					$dtx.eq(i).removeClass('text-updated');
					$dcom1.eq(i).val(skillTypeName[i]);
					$dcom2.eq(i).val(yearsDateOfAcquisition[i]);
					$dcom1.eq(i).removeClass('update-compare1');
					$dcom2.eq(i).removeClass('update-compare2');
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