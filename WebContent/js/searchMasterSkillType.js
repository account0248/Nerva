/**
 * 
 */
var options = {
	valueNames: ['skillTypeId', 'skillTypeName', 'yearsDateOfAcquisition']
};
var search_button = function() {

	// リクエストJSON
	var request = {
		skillTypeId: document.getElementById("skillTypeId").value,
		skillTypeName: document.getElementById("skillTypeName").value,
		yearsDateOfAcquisition: document.getElementById("yearsDateOfAcquisition").value,
	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/SearchSkillTypeMstAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		}
	}).done(function(data) {
		if (data == "") {
			window.location.href = 'jsp/error.jsp';
		}
		var table = document.getElementById("skillType");
		while (table.rows[0])
			table.deleteRow(0);
		// 通信が成功した場合に受け取るメッセージ
		var searchSkillType = data["searchSkillTypeDTOList"];
		var errorList = data["errorListDTO"];
		const button = document.getElementById("updateSkillType");
		target = document.getElementById("errorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (var i = 0; i < errorList.length; i++) {
				target.innerHTML += errorList[i] + "<br>";
			}
		}
		// 検索結果があった場合は検索結果を表示
		if (searchSkillType != null) {
			for (var i = 0; i < searchSkillType.length; i++) {
				var skillTypeinfo = searchSkillType[i];
				createData(table.insertRow(-1), i, skillTypeinfo);
			}
			button.style.display = "block";
		}
		new List('sort', options);
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = 'jsp/error.jsp';

	});

}

var createData = function(row, i, skillTypeinfo) {

	var cell1 = row.insertCell(-1);
	cell1.innerHTML = skillTypeinfo.skillTypeId;
	let $new_element1 = document.createElement('input');
	$new_element1.setAttribute("type", "hidden");
	$new_element1.setAttribute("value", skillTypeinfo.skillTypeId);
	$new_element1.classList.add("skillTypeId");

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = '<input type="text" id="skillTypeName' + i + '" class="updating1" value="' + skillTypeinfo.skillTypeName + '" onChange="cssChange(' + i + ')"/>';
	let $new_element2 = document.createElement('input');
	$new_element2.setAttribute("type", "hidden");
	$new_element2.setAttribute("value", skillTypeinfo.skillTypeName);
	$new_element2.classList.add("compare1");

	var select = document.getElementById("yearsDateOfAcquisition");

	var cell3 = row.insertCell(-1);
	var selectValue = '';
	if (skillTypeinfo.yearsDateOfAcquisition == "1") {
		selectValue += '<select class="updating2" onChange="cssChange(' + i + ')">';
		selectValue += '<option value="' + skillTypeinfo.yearsDateOfAcquisition + '" selected>' + select.children[1].textContent + '</option>'
		selectValue += '<option value="0">' + select.children[2].textContent + '</option>'
	} else {
		selectValue += '<select class="updating2" onChange="cssChange(' + i + ')">';
		selectValue += '<option value="' + skillTypeinfo.yearsDateOfAcquisition + '" selected>' + select.children[2].textContent + '</option>'
		selectValue += '<option value="1">' + select.children[1].textContent + '</option>'
	}
	selectValue += '</select>';
	cell3.innerHTML = selectValue;
	let $new_element3 = document.createElement('input');
	$new_element3.setAttribute("type", "hidden");
	$new_element3.setAttribute("value", skillTypeinfo.yearsDateOfAcquisition);
	$new_element3.classList.add("compare2");

	cell1.appendChild($new_element1);
	cell2.appendChild($new_element2);
	cell3.appendChild($new_element3);

};

let cssChange = (i) => {
	var $getId = $('td .skillTypeId').eq(i);
	//色、更新用
	var element = document.getElementById('skillTypeName' + i + '');
	var $getEle1 = $('td .updating1').eq(i);
	var $getEle2 = $('td .updating2').eq(i);
	var $getCompare1 = $('td .compare1').eq(i);
	var $getCompare2 = $('td .compare2').eq(i);
	//変更確認用
	var $getVal1 = $('td .updating1').eq(i).val();
	var $getVal2 = $('td .updating2').eq(i).val();
	var $compareVal1 = $("td .compare1").eq(i).val();
	var $compareVal2 = $("td .compare2").eq(i).val();
	//文字が変わったら(スキル種別名)
	if ($getVal1 != $compareVal1) {
		//黄色付与
		$getEle1.addClass('text-updated');
		//空白になったら
		if ($getVal1.length == 0) {
			//黄色削除
			$getEle1.removeClass('text-updated');
			//赤色付与
			$getEle1.addClass('required4');
			//1文字以上は赤色削除
		} else {
			$getEle1.removeClass('required4');
		}
	//文字が同じかつrequired4が付与されていた場合、赤色削除	
	} else if ($getVal1 == $compareVal1 && element.classList.contains('required4') == true) {
		$getEle1.removeClass('required4');
	//文字が同じだった場合、黄色削除
	} else {
		$getEle1.removeClass('text-updated');
	}
	//文字が変わったら(年数/取得日)
	if ($getVal2 != $compareVal2) {
		//黄色付与
		$getEle2.addClass('text-updated');
		//文字が同じだった場合、黄色削除
	} else {
		$getEle2.removeClass('text-updated');
	}

	//更新用クラスを削除
	$getId.removeClass('update-Id');
	$getEle1.removeClass('update-vaule1');
	$getEle2.removeClass('update-vaule2');
	$getCompare1.removeClass('update-compare1');
	$getCompare2.removeClass('update-compare2');
	//更新用css付与
	if ($getVal1 != $compareVal1 || $getVal2 != $compareVal2) {
		$getId.addClass('update-Id');
		$getEle1.addClass('update-vaule1');
		$getEle2.addClass('update-vaule2');
		$getCompare1.addClass('update-compare1');
		$getCompare2.addClass('update-compare2');
	}
}


$('#search_button').on('click', function() {
	search_button();
});