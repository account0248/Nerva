/**
 * 勤務表パスワード入力ダイアログ
 */
function fnOpenpaswrodDialog() {
	$("#dialog-pass").dialog({
		resizable: false,
		modal: true,
		title: '勤務表パスワード入力',
		height: 220,
		width: 400,
		buttons: {
			"OK": function() {
				let passVal = document.getElementById("input-impass");
				document.getElementById("impassword").value = passVal.value;

				$(this).dialog('close');
				excelImport();

			}
		}
	});
}


let showFileName = () => {
	//ファイル取得
	let file = document.getElementById('file').files[0];
	let fileDiv = document.getElementById('filename-out');
	//ファイル名取得
	let fileName = file.name;
	//子要素削除
	deleteElementChild(fileDiv);
	createSpan(fileDiv, '<p>' + fileName + '</p>');
	$("#filename").val(fileName);
}

let createFile = (e) => {

	const output = document.getElementById('import');
	const inputTemplate = document.createElement('input');
	inputTemplate.type = 'file';
	inputTemplate.id = 'upfile';
	inputTemplate.name = 'file';

	const { files } = e.target;
	output.removeChild(output.firstChild);
	[].forEach.call(files, file => {
		const dt = new DataTransfer();
		dt.items.add(file);
		const input = inputTemplate.cloneNode(false);
		input.files = dt.files;
		output.insertBefore(input, output.firstChild);

	}, false)
}


/**
 * ファイル、インポート、エクスポート、更新表示
 */
let showBottuns = () => {

	//要素取得 
	let $inDiv = document.getElementById("output-action");
	let $upDiv = document.getElementById("btnOpenDialog");
	$inDiv.style.display = "block";
	$upDiv.style.display = "block";
}

/**
 * 必須に入力がなければ赤表示
 */
let holidayChane = () => {
	$('.' + HOLIDAYCLASS).on('change', function() {
		let $getVal = $(this).val();
		if ($getVal) {
			$(this).removeClass('required4');

		} else {
			$(this).addClass('required4');
		}
	})
}

/**
 * 開始入力時、必須に入力がなければ赤表示
 */
let startChane = (i) => {
	let $getEle = $('#' + STARTID + i);
	let endId = ENDID + i;
	let restId = RESTID + i;
	let $endEle = $('#' + endId);
	let $restEle = $('#' + restId);

	if ($getEle.val()) {
		$getEle.removeClass('required4');
		if ($endEle.val()) {
			$endEle.removeClass('required4');

		} else {
			$endEle.addClass('required4');
		}
		if ($restEle.val()) {
			$restEle.removeClass('required4');

		} else {
			$restEle.addClass('required4');
		}
	} else {
		$endEle.removeClass('required4');
		$restEle.removeClass('required4');
	}


}

/**
 * 終了入力時、必須に入力がなければ赤表示
 */
let endChane = (i) => {


	let $getEle = $('#' + ENDID + i);
	let startId = STARTID + i;
	let $startEle = $('#' + startId);

	if ($getEle.val()) {

		$getEle.removeClass('required4');

		if ($startEle.val()) {

			$startEle.removeClass('required4');
		} else {

			$startEle.addClass('required4');
		}
	} else {

		$startEle.removeClass('required4');
	}

}

//休憩の赤表示削除
let restChane = (i) => {
	let $getEle = $('#' + RESTID + i);
	let startId = STARTID + i;
	let $startEle = $('#' + startId);
	if ($getEle.val()) {

		$getEle.removeClass('required4');
	} else if ($startEle) {

		$getEle.addClass('required4');
	}
}

//更新
function fnOpenNormalDialog() {
	// Define the Dialog and its properties.
	let $title = $('title').text();

	$("#dialog-confirm").dialog({
		resizable: false,
		modal: true,
		title: $title,
		height: 170,
		width: 400,
		buttons: {
			"更新する": function() {
				$(this).dialog('close');
				updateWorkSchedule();
			},
			"更新しない": function() {
				$(this).dialog('close');
				callback(false);
			}
		}
	});
}

//休暇時間変更時
let paidTimeChange = (i) => {
	let holidayValue = $('#'+HOLIDAYID+i+ ' option:selected').val();
	if (holidayValue) {
		let paidTimeFlgValue = document.getElementById(PAIDTIMEFLGID + holidayValue + i).value;
		if (paidTimeFlgValue == "true") {
			let $paidTimeEle = $("#"+PAIDTIMEID + i+" :first-child");
			$paidTimeEle.removeClass('required4');
			if ($paidTimeEle.val()) {
				$paidTimeEle.removeClass('required4');
			}else{
				$paidTimeEle.addClass('required4');
			}
		}
	}
}

//遅早時刻変更時
let lateTimeChange = (i) => {
	let holidayValue = $('#'+HOLIDAYID+i+ ' option:selected').val();
	if (holidayValue) {
		let lateTimeFlgValue = document.getElementById(LATETIMEFLGID + holidayValue + i).value;
		if (lateTimeFlgValue == "true") {
			let $lateTimeEle = $("#"+LATETIMEID + i+" :first-child");
			$lateTimeEle.removeClass('required4');
			if ($lateTimeEle.val()) {
				$lateTimeEle.removeClass('required4');
			}else{
				$lateTimeEle.addClass('required4');
			}
		}
	}
}

