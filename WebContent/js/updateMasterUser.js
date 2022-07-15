function fnOpenNormalDialog() {
	// Define the Dialog and its properties.
	$('#dialog-confirm').dialog({
		resizable: false,
		modal: true,
		title: "ユーザーマスタメンテナンス",
		height: 150,
		width: 400,
		buttons: {
			"更新する": function() {
				$(this).dialog('close');
				updateUserCallback(true);
			},
			"更新しない": function() {
				$(this).dialog('close');
				updateUserCallback(false);
			}
		}
	});
}

$('#updateUser').click(fnOpenNormalDialog);

function updateUserCallback(value) {
	if (value) {
		userPushUpdate(true);
	}
}

var userPushUpdate = function(userUpdateFlg) {
	if (userUpdateFlg) {
		let userId = [];
		let userIdClass = document.getElementsByClassName('update-Id');
		let userName = [];
		let userNameClass = document.getElementsByClassName('update-vaule origin-userName');
		let password = [];
		let passwordClass = document.getElementsByClassName('update-vaule origin-password');
		let post = [];
		let postClass = document.getElementsByClassName('update-vaule origin-post');
		let adminFlg = [];
		let adminFlgClass = document.getElementsByClassName('update-vaule origin-adminFlg');
		
		for (let i = 0; i < userIdClass.length; i++) {
			userId.push(userIdClass.item(i).value);
			userName.push(userNameClass.item(i).value);
			password.push(passwordClass.item(i).value);
			post.push(postClass.item(i).value);
			adminFlg.push(adminFlgClass.item(i).value);
			
		}
		var userIdj = JSON.stringify(userId);
		var userNamej = JSON.stringify(userName);
		var passwordj  = JSON.stringify(password);
		var postj = JSON.stringify(post);
		var adminFlgj  = JSON.stringify(adminFlg);

		// リクエストJSON
		var request = {
			userId: userIdj,
			userName: userNamej,
			password: passwordj,
			post: postj,
			adminFlg: adminFlgj
		};
		// ajaxでservletにリクエストを送信
		$.ajax({
			type: "POST", // GET / POST
			url: "/Nerva/UpdateUserMstAction", // 送信先のServlet URL
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
			//エラーがない場合にCSS反映用にクラスを張り替える
			else{
				
				/*const button = document.getElementById("updateUser");*/
				
				//各クラスを持っているカラムを取得
				let $textUpdated = $('.text-updated');
				let $updateValue = $('.update-vaule');
				let $compareValue =$('.update-compare');
				
				//更新対象のユーザーID
				let $did = $('.update-Id');
				
				//hiddenで持っている比較対象の値
				let $comName =$('.compare-userName');
				let $comPass =$('.compare-password');
				let $comPost =$('.compare-post');
				let $comAdmin =$('.compare-adminFlg');
				
				//繰り返し処理を用いて更新された行の更新フラグになるクラスを取り除く
				for(let i=0;i <$did.length*4; i++){
					
					$updateValue.eq(i).removeClass('update-vaule');
				}
				
				//繰り返し処理を用いてCSS側に編集済みであることを示す更新クラスを取り除く
				for (let j = 0; j < $textUpdated.length; j++) {
					
					//比較対象の値をユーザーの入力した更新値で更新
					 $comName.eq(j).val(userName[j]);
					 $comPass.eq(j).val(password[j]);
					 $comPost.eq(j).val(post[j]);
					 $comAdmin.eq(j).val(adminFlg[j]);
					 
					 //編集済みであるを示すクラスを取り除く
					 $textUpdated.eq(j).removeClass('text-updated');
					
					//編集済みカラムの比較値であることを示すクラスを取り除く
					 $compareValue.eq(j).removeClass('update-compare');

					//編集済みユーザーのIDであることを示すクラスを取り除く
					 $did.eq(j).removeClass('update-Id');

				}
				/*//ボタンの非活性化
				button.disabled =true;*/
			}
			return false;
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			window.location.href = './jsp/error.jsp';
		});
	} else {
		return false;
	}
}
