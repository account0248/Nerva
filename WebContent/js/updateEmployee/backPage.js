// 戻るボタン押下時の処理
var UpdateEmpBackPage = function() {

	// フォームタグの生成
	var updateEmpBackPageForm = document.createElement('form');
	// リクエスト情報を格納するタグの生成
	var request = document.createElement('input');

	// 通信の方法の決定
	updateEmpBackPageForm.method = 'POST';
	// 遷移先のURL
	updateEmpBackPageForm.action = './UpdateEmpBackPageAction';

	// 入力フォームが表示されないようにする
	request.type = 'hidden';
	// リクエストパラメータのキー名
	request.name = 'employeeId';
	// リクエストパラメータの値
	request.value = document.UpdateEmp.employeeId.value;

	// フォームにリクエスト情報の追加
	updateEmpBackPageForm.appendChild(request);
	document.body.appendChild(updateEmpBackPageForm);

	// submitの実行
	updateEmpBackPageForm.submit();
}