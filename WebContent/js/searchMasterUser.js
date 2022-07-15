/**
 * 
 */
var options = {
	valueNames: ['userId', 'userName', 'company', 'post', 'adminFlg']
};


var search_button = function() {

	// リクエストJSON
	var request = {
		userId: document.getElementById("userId").value,
		userName: document.getElementById("userName").value,
		company: document.getElementById("company").value,
		post: document.getElementById("post").value,
		adminFlg: document.getElementById("adminFlg").value,

	};

	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/SearchUserMstAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		}
	}).done(function(data) {
		if (data == "") {
			window.location.href = 'error.jsp';
		}
		var table = document.getElementById("userMaster");
		while (table.rows[0])
			table.deleteRow(0);
		// 通信が成功した場合に受け取るメッセージ
		var searchUser = data["searchResultUserDTOList"];
		var errorList = data["errorListDTO"];
		const button = document.getElementById("updateUser");
		target = document.getElementById("errorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {

			for (var i = 0; i < errorList.length; i++) {

				target.innerHTML += errorList[i] + "<br>";
			}
		}
		// 検索結果があった場合は検索結果を表示
		if (searchUser != null) {
			for (var i = 0; i < searchUser.length; i++) {
				var userinfo = searchUser[i];
				createData(table.insertRow(-1), i, userinfo);
			}
			button.style.display = "block";
			/*button.disabled = true;*/
		}


		new List('sort', options);
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './jsp/error.jsp';

	});

}

var createData = function(row, i, userinfo) {

	//役職検索用セレクタ
	var postList = document.getElementById("post");
	
	//ユーザー権限セレクタ
	var privilege = document.getElementById("adminFlg");
	
	//所属会社セレクタ
	var companyList = document.getElementById("company");
	
	//ユーザーの所属会社名を格納
	var userCompany;

	//所属会社セレクタからユーザーの所属会社IDと一致するものを文字列として取得
	for (let i = 0; i < companyList.childElementCount; i++) {
		if (userinfo.company == companyList.children[i].value) {
			userCompany = companyList.children[i].textContent;
		}
	}

	//検索結果を表示
	var cell1 = row.insertCell(-1);
	cell1.innerHTML = userinfo.userId;
	let $new_element1 = document.createElement('input');
	$new_element1.setAttribute("type", "hidden");
	$new_element1.setAttribute("value", userinfo.userId);
	$new_element1.classList.add("userId");

	var cell2 = row.insertCell(-1);
	cell2.innerHTML = '<input type="text" class="origin-userName" value="' + userinfo.userName + '" onChange="cssChange(' + i + ')"/>';
	let $new_element2 = document.createElement('input');
	$new_element2.setAttribute("type", "hidden");
	$new_element2.setAttribute("value", userinfo.userName);
	$new_element2.classList.add("compare-userName");

	var cell3 = row.insertCell(-1);
	cell3.innerHTML = '<input type="password" class="origin-password" value="' + userinfo.password + '" onChange="cssChange(' + i + ')"/>';
	let $new_element3 = document.createElement('input');
	$new_element3.setAttribute("type", "hidden");
	$new_element3.setAttribute("value", userinfo.password);
	$new_element3.classList.add("compare-password");

	var cell4 = row.insertCell(-1);
	cell4.innerHTML = userCompany;
	let $new_element4 = document.createElement('input');
	$new_element4.setAttribute("type", "hidden");
	$new_element4.setAttribute("value", userinfo.company);
	$new_element4.classList.add("company");

	var cell5 = row.insertCell(-1);
	var postSelect = '<select class="origin-post" " onChange="cssChange(' + i + ')">';
	for (let i = 1; i < postList.childElementCount; i++) {
		if (postList.children[i].value == userinfo.post) {
			postSelect += '<option value="' + postList.children[i].value + '" selected>' + postList.children[i].textContent + '</option>'
		} else {
			postSelect += '<option value="' + postList.children[i].value + '">' + postList.children[i].textContent + '</option>'
		}
	}
	postSelect += '</select>';
	cell5.innerHTML = postSelect;
	let $new_element5 = document.createElement('input');
	$new_element5.setAttribute("type", "hidden");
	$new_element5.setAttribute("value", userinfo.post);
	$new_element5.classList.add("compare-post");

	var cell6 = row.insertCell(-1);
	var adminSelect = '<select class="origin-adminFlg" " onChange="cssChange(' + i + ')">';
	for (let i = 1; i < privilege.childElementCount; i++) {
		if (privilege.children[i].value == userinfo.adminFlg) {
			adminSelect += '<option value="' + privilege.children[i].value + '"  selected>' + privilege.children[i].textContent + '</option>'
		} else {
			adminSelect += '<option value="' + privilege.children[i].value + '">' + privilege.children[i].textContent + '</option>'
		}
	}
	adminSelect += '</select>';
	cell6.innerHTML = adminSelect;
	let $new_element6 = document.createElement('input');
	$new_element6.setAttribute("type", "hidden");
	$new_element6.setAttribute("value", userinfo.adminFlg);
	$new_element6.classList.add("compare-adminFlg");

	cell1.appendChild($new_element1);
	cell2.appendChild($new_element2);
	cell3.appendChild($new_element3);
	cell4.appendChild($new_element4);
	cell5.appendChild($new_element5);
	cell6.appendChild($new_element6);

};

//変更箇所の色を変更
let cssChange = (i) => {
	const button = document.getElementById("updateUser");
	//ユーザー名用
	var $getIdN = $('td .userId').eq(i);
	var $getNameEle = $('td .origin-userName').eq(i);
	var $getNameVal = $('td .origin-userName').eq(i).val();
	var $compareVal = $("td .compare-userName").eq(i).val();
	var $getCompare = $('td .compare-userName').eq(i);
	if ($getNameVal != $compareVal) {

		$getNameEle.addClass('text-updated');

		//更新用クラスを一度消してから追加する
		$getIdN.removeClass('update-Id');
		$getNameEle.removeClass('update-vaule');
		$getCompare.removeClass('update-compare');
		$getIdN.addClass('update-Id');
		$getNameEle.addClass('update-vaule');
		$getCompare.addClass('update-compare');

		if ($getNameVal.length == 0) {
			$getNameEle.removeClass('text-updated');
			$getNameEle.addClass('required4');
		} else {
			$getNameEle.removeClass('required4');

		}
	} else {
		$getNameEle.removeClass('text-updated');
		//更新用クラス
		$getNameEle.removeClass('update-vaule');
		$getCompare.removeClass('update-compare');
		$getNameEle.removeClass('required4');

	}
	//パスワード用
	var $getIdP = $('td .userId').eq(i);
	var $getPassEle = $('td .origin-password').eq(i);
	var $getPassVal = $('td .origin-password').eq(i).val();
	var $compareValP = $("td .compare-password").eq(i).val();
	var $getCompareP = $('td .compare-password').eq(i);
	if ($getPassVal != $compareValP) {

		$getPassEle.addClass('text-updated');

		//更新用クラスを一度消してから追加する
		$getIdP.removeClass('update-Id');
		$getPassEle.removeClass('update-vaule');
		$getCompareP.removeClass('update-compare');
		$getIdP.addClass('update-Id');
		$getPassEle.addClass('update-vaule');
		$getCompareP.addClass('update-compare');

		if ($getPassVal.length == 0) {
			$getPassEle.removeClass('text-updated');
			$getPassEle.addClass('required4');
		} else {
			$getPassEle.removeClass('required4');

		}
	} else {
		$getPassEle.removeClass('text-updated');
		//更新用クラス
		$getPassEle.removeClass('update-vaule');
		$getCompareP.removeClass('update-compare');
		$getPassEle.removeClass('required4');

	}
	//役職用
	var $getIdPT = $('td .userId').eq(i);
	var $getPostEle = $('td .origin-post').eq(i);
	var $getPostVal = $('td .origin-post').eq(i).val();
	var $compareValPT = $("td .compare-post").eq(i).val();
	var $getComparePT = $('td .compare-post').eq(i);
	if ($getPostVal != $compareValPT) {

		$getPostEle.addClass('text-updated');

		//更新用クラスを一度消してから追加する
		$getIdPT.removeClass('update-Id');
		$getPostEle.removeClass('update-vaule');
		$getComparePT.removeClass('update-compare');
		$getIdPT.addClass('update-Id');
		$getPostEle.addClass('update-vaule');
		$getComparePT.addClass('update-compare');

		if ($getPostVal.length == 0) {
			$getPostEle.removeClass('text-updated');
			$getPostEle.addClass('required4');
		} else {
			$getPostEle.removeClass('required4');

		}
	} else {
		$getPostEle.removeClass('text-updated');
		//更新用クラス
		$getPostEle.removeClass('update-vaule');
		$getComparePT.removeClass('update-compare');
		$getPostEle.removeClass('required4');

	}
	//権限用
	var $getIdA = $('td .userId').eq(i);
	var $getAdminEle = $('td .origin-adminFlg').eq(i);
	var $getAdminVal = $('td .origin-adminFlg').eq(i).val();
	var $compareValA = $("td .compare-adminFlg").eq(i).val();
	var $getCompareA = $('td .compare-adminFlg').eq(i);
	if ($getAdminVal != $compareValA) {

		$getAdminEle.addClass('text-updated');

		//更新用クラスを一度消してから追加する
		$getIdA.removeClass('update-Id');
		$getAdminEle.removeClass('update-vaule');
		$getCompareA.removeClass('update-compare');
		$getIdA.addClass('update-Id');
		$getAdminEle.addClass('update-vaule');
		$getCompareA.addClass('update-compare');

		if ($getAdminVal.length == 0) {
			$getAdminEle.removeClass('text-updated');
			$getAdminEle.addClass('required4');
		} else {
			$getAdminEle.removeClass('required4');

		}
	} else {
		$getAdminEle.removeClass('text-updated');
		//更新用クラス
		$getAdminEle.removeClass('update-vaule');
		$getCompareA.removeClass('update-compare');
		$getAdminEle.removeClass('required4');

	}
	//ボタンを活性化
	button.disabled = false;
	
	//更新対象列のユーザーIDにクラスを付与
	var $getId = $('td .userId').eq(i);
	if ($('td').eq(i).hasClass('update-vaule')) {
		$getId.addClass('update-Id');
	}
	
	//更新対象列の各カラムを変数に格納
	var $getUserName = $('td .origin-userName').eq(i);
	var $getPassword = $('td .origin-password').eq(i);
	var $getPost = $('td .origin-post').eq(i);
	var $getAdminFlg = $('td .origin-adminFlg').eq(i);
	
	//更新ためのクラスを更新対象列のカラムに付与
	if ($getId.hasClass("update-Id") == true) {
		$getUserName.addClass('update-vaule');
		$getPassword.addClass('update-vaule');
		$getPost.addClass('update-vaule');
		$getAdminFlg.addClass('update-vaule');
	}

}


$('#search_button').on('click', function() {
	search_button();
});
