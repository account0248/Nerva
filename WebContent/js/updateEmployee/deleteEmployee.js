// 削除ボタンが押下された時の処理

function fnOpenNormalDialog() {

    // Define the Dialog and its properties.
    $("#dialog-confirm-employee-delete").dialog({
        resizable: false,
        modal: true,
        title: "従業員更新画面",
        height: 150,
        width: 400,
        buttons: {
            "削除する": function () {
                $(this).dialog('close');
                callbackDelete(true);
            },
                "削除しない": function () {
                $(this).dialog('close');
                callbackDelete(false);
            }
        }
    });
}

$('#openDeleteDialog').click(fnOpenNormalDialog);

function callbackDelete(value) {
    if (value) {
    	console.log(value);
    	DeleteEmployee(true);
    }
}

function DeleteEmployee(dialogDeleteFlg) {
	if (dialogDeleteFlg) {

		// フォームタグの生成
		var DeleteEmployeeForm = document.createElement('form');
		// リクエスト情報を格納するタグの生成
		var request = document.createElement('input');

		// 通信の方法の決定
		DeleteEmployeeForm.method = 'POST';
		// 遷移先のURL
		DeleteEmployeeForm.action = './DeleteEmpAction';

		// 入力フォームが表示されないようにする
		request.type = 'hidden';
		// リクエストパラメータのキー名
		request.name = 'employeeId';
		// リクエストパラメータの値
		request.value = document.UpdateEmp.employeeId.value;

		// フォームにリクエスト情報の追加
		DeleteEmployeeForm.appendChild(request);
		document.body.appendChild(DeleteEmployeeForm);

		// submitの実行
		DeleteEmployeeForm.submit();
		return true;
	}
	return false;
}