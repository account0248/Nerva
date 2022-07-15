var txtArea = "<input required class=\"required width93\" type=\"text\" value=\"\" name=\"skillDetail[]\">"
var qualification = "<input required class=\"required\" type=\"text\" value=\"\" size=\"12\" name=\"experienceYears[]\" readonly=\"true\" style=\"background-color:#e9e9e9;\">";
var deleteButton = "<input required class=\"required\" type=\"button\" value=\"削除\" onclick=\"deletePush( this.parentNode.parentNode )\">"
var acquisitionYear = "<input required class=\"required\" type=\"month\"  name=\"acquisitionYearMonth[]\" readonly=\"true\"style=\"background-color:#e9e9e9;\" >";

var deletePush = function(id) {
	id.parentNode.removeChild(id);
	resetId();
}

var resetId = function() {
	$('tr td').each(function(i) {
		$(this).attr('id', (i + 1));
	});
}

var addSkillTable = function(skillData) {
	var table = document.getElementById("skill");
	createData(table.insertRow(-1), skillData);
	resetId();

	$(".select").change(
		function() {
			//追記 スキル種別の何番目が選択されたか
			var selectIndex = $(this).prop("selectedIndex")-1;
			//追記 選択されたスキル種別の年数取得日フラグ取得
			var $skillTypeFlg = $('.skillTypeArray').eq(selectIndex).val();
			//空白が選択された場合、$skillTypeFlgを空白にする
			if(selectIndex == -1){
				$skillTypeFlg = "";
			}
			var selectParentTdId = $(this).parent().attr("id");
			var targetQualificationSelector = '#'
				+ (parseInt(selectParentTdId) + 2) + ' input';
			var targetAcquisitionSelector = '#'
				+ (parseInt(selectParentTdId) + 3) + ' input';
			//変更　年数/取得日フラグがtrueの場合
			if ($skillTypeFlg == 'true') {
				$(targetQualificationSelector).prop("readonly", false);
				$(targetQualificationSelector).prop("style",
					"background-color:#FFFFFF");
				$(targetAcquisitionSelector).prop("value", "");
				$(targetAcquisitionSelector).prop("readonly", true);
				$(targetAcquisitionSelector).prop("style",
					"background-color:#e9e9e9");
			//変更　年数/取得日フラグがfalseの場合
			} else if ($skillTypeFlg == 'false') {
				$(targetAcquisitionSelector).value = "";
				$(targetAcquisitionSelector).prop("readonly", false);
				$(targetAcquisitionSelector).prop("style",
					"background-color:#FFFFFF");
				$(targetQualificationSelector).prop("value", "");
				$(targetQualificationSelector).prop("readonly", true);
				$(targetQualificationSelector).prop("style",
					"background-color:#e9e9e9");
			//変更　空白が選択されたとき
			} else {
				$(targetAcquisitionSelector).prop("value", "");
				$(targetAcquisitionSelector).prop("readonly", true);
				$(targetAcquisitionSelector).prop("style",
					"background-color:#e9e9e9");
				$(targetQualificationSelector).prop("value", "");
				$(targetQualificationSelector).prop("readonly", true);
				$(targetQualificationSelector).prop("style",
					"background-color:#e9e9e9");
			}
		});
}

var addSkill = function() {
	// リクエストJSON
	var request = {};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "./ReturnSkillInfoAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		},

	}).then(function(data) {
		if (data == "") {
			window.location.href = 'jsp/error.jsp';
		}
		// テーブル初期化
		var skillData = data["listDTO"];
		addSkillTable(skillData);
	}, function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = 'jsp/error.jsp';
	});
}

var createData = function(row, skillData) {
	var cell1 = row.insertCell(-1);
	var skillType = "";
	var hidden_str = "";
	for (var i = 0; i < skillData.length; i++) {
		skillType += "<option  class=\"select\" value=\"" + skillData[i].skillTypeId + "\" name=\"sikaku\">" + skillData[i].skillTypeName + "</option>";
		//追記 年数/取得日フラグをhiddenで入れる
		hidden_str += '<input type="hidden" class="skillTypeArray" value="' + skillData[i].yearsDateOfAcquisition + '"/>';
	}
	cell1.innerHTML = hidden_str + "<select required id=\"skillType\" class=\"select required\" name=\"skillType[]\"><option value=\"\"></option>"
		+ skillType + "</select>";

	$(this).attr('id', ("detail" + (1)));
	var cell2 = row.insertCell(-1);
	cell2.innerHTML = txtArea;

	var cell3 = row.insertCell(-1);
	cell3.innerHTML = qualification;

	var cell4 = row.insertCell(-1);
	cell4.innerHTML = acquisitionYear;

	var cell5 = row.insertCell(-1);
	cell5.innerHTML = deleteButton;
}