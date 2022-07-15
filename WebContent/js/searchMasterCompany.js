/**
 * 
 */
var options = {
	valueNames: ['companyId', 'companyName','companyGroup','companyCode']
};
var search_button = function() {

	// リクエストJSON
	var request = {
		companyId: document.getElementById("companyId").value,
		companyName: document.getElementById("companyName").value,
		companyGroup: document.getElementById("companyGroup").value,
		companyCode: document.getElementById("companyCode").value,

	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/SearchCompanyMstAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		//async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		}
	}).done(function(data) {
		if (data == "") {
			window.location.href = './error.jsp';
		}
		var table = document.getElementById("company");
		while (table.rows[0])
			table.deleteRow(0);
		// 通信が成功した場合に受け取るメッセージ
		var searchCompany = data["searchCompanyDTOList"];
		const button = document.getElementById("updateCompany");
		var errorList = data["errorListDTO"];
		target = document.getElementById("errorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {

			for (var i = 0; i < errorList.length; i++) {

				target.innerHTML += errorList[i] + "<br>";
			}
		}
		// 検索結果があった場合は検索結果を表示
		if (searchCompany != null) {
			for (var i = 0; i < searchCompany.length; i++) {
				var companyinfo = searchCompany[i];
				createData(table.insertRow(-1), i, companyinfo);
			}
			button.style.display = "block";
		}

		new List('sort', options);
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './error.jsp';

	});

}

var createData = function(row, i, companyinfo) {

	var cell1 = row.insertCell(-1);
	cell1.innerHTML = companyinfo.companyId;
	let $new_element1 = document.createElement('input');
	$new_element1.setAttribute("type", "hidden");
	$new_element1.setAttribute("value", companyinfo.companyId);
	$new_element1.classList.add("companyId");

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = '<input type="text" id="companyName' + i + '"class="updating" value="' + companyinfo.companyName + '" onChange="cssChange(' + i + ')"/>';
	let $new_element2 = document.createElement('input');
	$new_element2.setAttribute("type", "hidden");
	$new_element2.setAttribute("value", companyinfo.companyName);
	$new_element2.classList.add("compare");
	
	let text = document.getElementById('companyGroup');
	var companyPrint="";
	var cell3 = row.insertCell(-1);
	for(let i=0;i<text.length;i++){
	if (companyinfo.companyGroup == text.children[i].value) {
		companyPrint=text.children[i].textContent;
		}
		}
	
	cell3.innerHTML = companyPrint;
	let $new_element3 = document.createElement('input');
	$new_element3.setAttribute("type", "hidden");
	$new_element3.setAttribute("value", companyinfo.companyGroup);
	$new_element3.classList.add("compare2");
	
	
	var cell4 = row.insertCell(-1);
	cell4.innerHTML = companyinfo.companyCode
	let $new_element4 = document.createElement('input');
	$new_element4.setAttribute("type", "hidden");
	$new_element4.setAttribute("value", companyinfo.companyCode);
	

	cell1.appendChild($new_element1);
	cell2.appendChild($new_element2);
	cell1.appendChild($new_element3);
	cell2.appendChild($new_element4);

};


let cssChange = (i) => {
const button = document.getElementById("updateCompany");
var element = document.getElementById('companyName' + i + '');
	var $getId = $('td .companyId').eq(i);
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
	} else if($getVal == $compareVal && element.classList.contains('required4') == true) {
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
	button.disabled = false;
}



$('#search_button').on('click', function() {
	search_button();
});

