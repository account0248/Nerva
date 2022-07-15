// 保有スキルのテーブルの要素にidを付与
window.addEventListener('load', function() {
	$('#empSkill tr td').each(function(i) {
		$(this).attr('id', 'UpdateSkillTd_' + i);
	});
	$('#empSkill tr td .skillInfoId').each(function(i) {
		$(this).attr('id', 'skillInfoId_' + i);
	});
	$('#empSkill tr td .updateSelectedSkill').each(function(i) {
		$(this).attr('id', 'updateSkillType_' + i);
	});
	$('#empSkill tr td .updateSkillDetail').each(function(i) {
		$(this).attr('id', 'updateSkillDetail_' + i);
	});
	$('#empSkill tr td .updateExperienceYears').each(function(i) {
		$(this).attr('id', 'updateExperienceYears_' + i);
	});
	$('#empSkill tr td .updateAcquisitionYearMonth').each(function(i) {
		$(this).attr('id', 'updateAcquisitionYearMonth_' + i);
	});
	var updateSelectedSkill = document.getElementsByClassName('updateSelectedSkill');
	for (var i = 0; i < updateSelectedSkill.length; i++) {
		updateSelectedSkill[i].addEventListener('change', changeSkillType);
	}
});

// 保有スキル欄のスキルタイプが変更されたことによる要素の変化
var changeSkillType = function() {
	var selectParentTdId = $(this).parent().parent().attr("id");
	//追記 スキル種別の何番目が選択されたか
	var selectIndex = $('#' + selectParentTdId).children(0).children(0).prop("selectedIndex");
	// 追記 選択されたvalue取得
	var selectValue = $('#' + selectParentTdId).children(0).children(0).val();
	// 選択されたスキル種別の年数取得日フラグの変数定義
	var $skillTypeFlg;
	
	//選択肢の先頭に空白の場合
	if($(this).children(0).val() == ""){
		
		//空白が選択された場合
		if(selectValue == ""){
			//年数取得日フラグを空文字にする
			$skillTypeFlg = "";
			
		//空白以外が選択された場合
		}else{
			//スキル種別の何番目が選択されたかを調整
			selectIndex = selectIndex - 1;
			//選択されたスキル種別の年数取得日フラグを取得
			$skillTypeFlg = $('.upSkillTypeArray').eq(selectIndex).val();
		}
		
	//選択肢の先頭に空白じゃない場合
	}else{
		//選択されたスキル種別の年数取得日フラグを取得
		$skillTypeFlg = $('.upSkillTypeArray').eq(selectIndex).val();
	}
	var experienceYears = $(this).parent().parent().parent().find(
		'.experienceYears');
	var acquisitionYearMonth = $(this).parent().parent().parent().find(
		'.acquisitionYearMonth');
	var experienceYearsId = experienceYears.parent().attr('id');
	var acquisitionYearMonthId = acquisitionYearMonth.parent().attr('id');
	var targetExperienceYearsSelector = '#' + experienceYearsId + ' input';
	var targetAcquisitionYearMonthSelector = '#' + acquisitionYearMonthId
		+ ' input';
	//変更　年数/取得日フラグがtrueの場合
	if ($skillTypeFlg == 'true') {
		$(targetExperienceYearsSelector).prop("readonly", false);
		$(targetExperienceYearsSelector).prop("required", true);
		$(targetExperienceYearsSelector).prop("style",
			"background-color:#FFFFFF");
		$(targetExperienceYearsSelector).addClass('required');
		$(targetAcquisitionYearMonthSelector).prop("readonly", true);
		$(targetAcquisitionYearMonthSelector).prop("required", false);
		$(targetAcquisitionYearMonthSelector).prop("style",
			"background-color:#EEEEEE");
		$(targetAcquisitionYearMonthSelector).val("");
		$(targetAcquisitionYearMonthSelector).removeClass('required');
	//変更　年数/取得日フラグがfalseの場合
	} else if($skillTypeFlg == 'false'){
		$(targetAcquisitionYearMonthSelector).prop("readonly", false);
		$(targetAcquisitionYearMonthSelector).prop("required", true);
		$(targetAcquisitionYearMonthSelector).prop("style",
			"background-color:#FFFFFF");
		$(targetAcquisitionYearMonthSelector).addClass('required');
		$(targetExperienceYearsSelector).prop("readonly", true);
		$(targetExperienceYearsSelector).prop("required", false);
		$(targetExperienceYearsSelector).prop("style",
			"background-color:#EEEEEE");
		$(targetExperienceYearsSelector).val("");
		$(targetExperienceYearsSelector).removeClass('required');
	} else{
		$(targetExperienceYearsSelector).addClass('required');
		$(targetAcquisitionYearMonthSelector).prop("readonly", true);
		$(targetAcquisitionYearMonthSelector).prop("required", false);
		$(targetAcquisitionYearMonthSelector).prop("style",
			"background-color:#EEEEEE");
		$(targetAcquisitionYearMonthSelector).val("");
		$(targetAcquisitionYearMonthSelector).removeClass('required');
		$(targetAcquisitionYearMonthSelector).addClass('required');
		$(targetExperienceYearsSelector).prop("readonly", true);
		$(targetExperienceYearsSelector).prop("required", false);
		$(targetExperienceYearsSelector).prop("style",
			"background-color:#EEEEEE");
		$(targetExperienceYearsSelector).val("");
		$(targetExperienceYearsSelector).removeClass('required');
	}
}

// スキル種別のデータをAjaxで取得する
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
		timeout: 1000,
		data: {
			json: request
		},
		success: function(data) {
			//jsonのデータが空の場合
			if (data == "") {
				//エラー画面に遷移
				window.location.href = 'jsp/error.jsp';
			}
			// テーブル初期化
			var skillMasterInfo = data["listDTO"];
			addSkillTable(skillMasterInfo);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//エラー画面に遷移
			window.location.href = 'jsp/error.jsp';
		}
	});
}

// 保有スキル欄の追加行に対するid付与
var addSkillTable = function(skillMasterInfo) {
	var table = document.getElementById("skill");
	createSkillData(table.insertRow(-1), skillMasterInfo);
	$('#empSkill tr td').each(function(i) {
		$(this).attr('id', 'registSkillTd_' + i);
	});
	$('#empSkill tr td .registSelectedSkill').each(
		function(i) {
			$(this).attr('id', 'registSkillType_' + i);
			var registSelectedSkill = document.getElementsByClassName('registSelectedSkill');
			registSelectedSkill[i].addEventListener('change',changeSkillType);
		});
	$('#empSkill tr td .registSkillDetail').each(function(i) {
		$(this).attr('id', 'registSkillDetail_' + i);
	});
	$('#empSkill tr td .registExperienceYears').each(function(i) {
		$(this).attr('id', 'registExperienceYears_' + i);
	});
	$('#empSkill tr td .registAcquisitionYearMonth').each(function(i) {
		$(this).attr('id', 'registAcquisitionYearMonth_' + i);
	});
}

// スキル情報欄追加用変数の定義
var skillDetail = "<input type=\"text\" value=\"\" name=\"registSkillDetail\" class=\"registSkillDetail required width93\" required>";
var experienceYears = "<input type=\"text\" value=\"\"  size=\"12\"name=\"registExperienceYears\" class=\"registExperienceYears experienceYears\" readonly style=\"background-color:#EEEEEE;\">";
var acquisitionYear = "<input type=\"month\" name=\"registAcquisitionYearMonth\" class=\"registAcquisitionYearMonth acquisitionYearMonth\" readonly style=\"background-color:#EEEEEE;\">";
var skillDelete = "<input type=\"button\" class=\"button\" value=\"削除\" onclick=\"deletePush( this.parentNode.parentNode )\">"

// 保有スキル欄の行追加
var createSkillData = function(row, skillMasterInfo) {
	var cell1 = row.insertCell(-1);
	var skillTypeOption = "";
	var hidden_str = "";
	for (var i = 0; i < skillMasterInfo.length; i++) {
		skillTypeOption += "<option value=\"" + skillMasterInfo[i].skillTypeId
			+ "\">" + skillMasterInfo[i].skillTypeName + "</option>";
	}
	// スキル種別の欄追加
	cell1.innerHTML = hidden_str + "<span><select class=\"registSelectedSkill required width93\" name=\"registSkillType\" required><option value=\"\"></option>"
		+ skillTypeOption + "</select></span>";

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = skillDetail;

	var cell3 = row.insertCell(-1);
	cell3.innerHTML = experienceYears;

	var cell4 = row.insertCell(-1);
	cell4.innerHTML = acquisitionYear;

	var cell5 = row.insertCell(-1);
	cell5.innerHTML = skillDelete;
}