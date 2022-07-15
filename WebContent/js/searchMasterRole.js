/**
 * 
 */
var options = {
	valueNames: ['roleId', 'roleName']
};
var search_button = function() {
	// リクエストJSON
	var request = {
		roleId: document.getElementById("roleId").value,
		roleName: document.getElementById("roleName").value,

	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/SearchRoleMstAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		}
	}).done(function(data) {
		if (data == "") {
			window.location.href = './error.jsp';
		}
		var table = document.getElementById("role");
		while (table.rows[0])
			table.deleteRow(0);
		// 通信が成功した場合に受け取るメッセージ
		var searchRole = data["searchRoleDTOList"];
		var errorList = data["errorListDTO"];
		const button = document.getElementById("updateRole");
		target = document.getElementById("errorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {

			for (var i = 0; i < errorList.length; i++) {

				target.innerHTML += errorList[i] + "<br>";
			}
		}
		// 検索結果があった場合は検索結果を表示
		if (searchRole != null) {
			for (var i = 0; i < searchRole.length; i++) {
				var roleinfo = searchRole[i];
				createData(table.insertRow(-1), i, roleinfo);
			}
			button.style.display = "block";
			

		}


		new List('sort', options);
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './error.jsp';

	});

}

var createData = function(row, i, roleinfo) {

	var cell1 = row.insertCell(-1);
	cell1.innerHTML = roleinfo.roleId;
	let $new_element1 = document.createElement('input');
	$new_element1.setAttribute("type", "hidden");
	$new_element1.setAttribute("value", roleinfo.roleId);
	$new_element1.classList.add("roleId");

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = '<input type="text" id="roleName' + i + '"class="updating" value="' + roleinfo.roleName + '" onChange="cssChange(' + i + ')"/>';
	$('input[value=リーダー]').attr('disabled','true');
	let $new_element2 = document.createElement('input');
	$new_element2.setAttribute("type", "hidden");
	$new_element2.setAttribute("value", roleinfo.roleName);
	$new_element2.classList.add("compare");

	cell1.appendChild($new_element1);
	cell2.appendChild($new_element2);

};

let cssChange = (i) => {
const button = document.getElementById("updateRole");
var element = document.getElementById('roleName' + i + '');
	var $getId = $('td .roleId').eq(i);
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
	button.disabled = false;
}


$('#search_button').on('click', function() {
	search_button();
});

/*
var createRole = function() {
	var table = document.getElementById("role");

	for (var i = 1; i <= 100; i++) {
		createData(table.insertRow(-1));
	}
	new List('sort', options)
}*/
