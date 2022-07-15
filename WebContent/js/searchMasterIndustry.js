/**
 * 
 */
var options = {
	valueNames: ['industryId', 'industryName']
};
var search_button = function() {

	// リクエストJSON
	var request = {
		industryId: document.getElementById("industryId").value,
		industryName: document.getElementById("industryName").value,

	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/SearchIndustryMstAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		}
	}).done(function(data) {
		if (data == "") {
			window.location.href = './jsp/error.jsp';
		}
		var table = document.getElementById("industry");
		while (table.rows[0])
			table.deleteRow(0);
		// 通信が成功した場合に受け取るメッセージ
		var searchIndustry = data["searchIndustryDTOList"];
		var errorList = data["errorListDTO"];
		const button = document.getElementById("updateIndustry");
		target = document.getElementById("errorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {

			for (var i = 0; i < errorList.length; i++) {

				target.innerHTML += errorList[i] + "<br>";
			}
		}
		// 検索結果があった場合は検索結果を表示
		if (searchIndustry != null) {
			for (var i = 0; i < searchIndustry.length; i++) {
				var industryinfo = searchIndustry[i];
				createData(table.insertRow(-1), i, industryinfo);
			}
			button.style.display = "block";
			
		}


		new List('sort', options);
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './jsp/error.jsp';

	});

}

var createData = function(row, i, industryinfo) {

	var cell1 = row.insertCell(-1);
	cell1.innerHTML = industryinfo.industryId;
	let $new_element1 = document.createElement('input');
	$new_element1.setAttribute("type", "hidden");
	$new_element1.setAttribute("value", industryinfo.industryId);
	$new_element1.classList.add("industryId");

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = '<input type="text" id="industryName' + i + '"class="updating" value="' + industryinfo.industryName + '" onChange="cssChange(' + i + ')"/>';
	let $new_element2 = document.createElement('input');
	$new_element2.setAttribute("type", "hidden");
	$new_element2.setAttribute("value", industryinfo.industryName);
	$new_element2.classList.add("compare");

	cell1.appendChild($new_element1);
	cell2.appendChild($new_element2);

};

let cssChange = (i) => {
	
var element = document.getElementById('industryName' + i + '');
	var $getId = $('td .industryId').eq(i);
	var $getEle = $('td .updating').eq(i);
	var $getVal = $('td .updating').eq(i).val();
	var $compareVal = $("td .compare").eq(i).val();
	var $getCompare = $('td .compare').eq(i);
	if ($getVal != $compareVal) {

		$getEle.addClass('text-updated');

		//更新用クラスを一度消してから追加する
		$getId.removeClass('update-Id');
		$getEle.removeClass('update-vaule');
		$getCompare.removeClass('update-compare');
		$getId.addClass('update-Id');
		$getEle.addClass('update-vaule');
		$getCompare.addClass('update-compare');

		if ($getVal.length == 0) {
			$getEle.removeClass('text-updated');
			$getEle.addClass('required4');
		} else {
			$getEle.removeClass('required4');
		}
		//文字が同じかつrequired4が付与されていた場合、赤色削除	
	} else if ($getVal == $compareVal && element.classList.contains('required4') == true) {
		$getEle.removeClass('required4');
		$getId.removeClass('update-Id');
		$getEle.removeClass('update-vaule');
		$getCompare.removeClass('update-compare');
	} else {
		$getEle.removeClass('text-updated');
		//更新用クラス
		$getId.removeClass('update-Id');
		$getEle.removeClass('update-vaule');
		$getCompare.removeClass('update-compare');

	}
	
}


$('#search_button').on('click', function() {
	search_button();
});


