//ページが開いたとき
$(document).ready(() => {

	onload();
});

//勤務表フォーマット表示
$('#show-all').click(() => {
	display();
	
})


//インポート処理
$('#import-Dialog').click(() => {
	let data = importCheckAction();

	data.done(function(data) {

		response = data;
		if (response === true) {

			//Excelインポート
			fnOpenpaswrodDialog();
		} else if (response === false) {

			//テキストインポート
			textImport();
		} else {
			target = document.getElementById("registErrorList");
			target.innerHTML = "";
			let errorList = response;
			for (let l = 0; l < errorList.length; l++) {
				target.innerHTML += errorList[l] + "<br>";
			}
		}

	}), (function(error) {
		window.location.href = './jsp/error.jsp';
		
	});

});


//ファイル選択したとき
document.getElementById('file').addEventListener('change', e => {
	showFileName();
	createFile(e);
});


//年度が変更されたときの処理
/**
 * 
 * @param {*} event 
 */
$('#year').change(() => {
	let $select = document.querySelector('[name="year"]');
	onchange($select);
}
);

/**
 * 有給休暇等が変わったとき
 * @param {*} i 
 */
let HolidaySelect = (i) => {

	//変更処理
	holidayOption(i);
	//必須項目入力時
	holidayChane();
}

/**
 * 更新の確認
 */
$('#btnOpenDialog').click(fnOpenNormalDialog);

