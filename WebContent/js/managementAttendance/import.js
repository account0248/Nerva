/**
 * インポート処理
 */
importCheckAction = () => {
	let request = {
		fileName: document.getElementById("filename").value
	}

	return $.ajax({
		type: 'POST',
		url: '/Nerva/ImportCheckAction',
		dataType: 'json',
		data: request,
		data: {
			json: request
		}
	})
}


//勤務表表示
let showImportWorkShedule = (workScheduleDTOList, i) => {
	//日取得
	let index = workScheduleDTOList[i].days;
	//変数に格納
	let startTime = workScheduleDTOList[i].startTime;
	let endTime = workScheduleDTOList[i].endTime;
	let restTime = workScheduleDTOList[i].rest;
	let holidayWork = workScheduleDTOList[i].holidayWorking;
	let holidayPaid = workScheduleDTOList[i].holidayPeriod;
	let holidayDay = workScheduleDTOList[i].holidayDays;
	let holidayTime = workScheduleDTOList[i].holidayTime;
	let lateTime = workScheduleDTOList[i].lateTime;
	let remarks = workScheduleDTOList[i].remarks;

	//勤務表に挿入
	if (startTime != null) {
		document.getElementById(STARTID+index).value = startTime;
	}
	if (endTime != null) {
		document.getElementById(ENDID + index).value = endTime;
	}
	if (restTime != null) {
		document.getElementById(RESTID + index).value = restTime;
	}
	if (holidayWork != null) {
		document.getElementById(HOLIDAYWORKID + index).children.item(0).value = holidayWork;
	}
	if (holidayPaid != null) {
		let holiday = document.getElementById(HOLIDAYID + index);
		let selctCount = document.getElementById(HOLIDAYID + index).childElementCount;
		for(let i=0;i<selctCount;i++){
			let holidayVal = holiday.children.item(i).textContent;
			if(holidayPaid==holidayVal){
				holiday.options[i].selected = true;
			}
		}
		holidayOption(index);
	}
	if (holidayPaid != null) {
	document.getElementById(PAIDDAYSID + index).children.item(0).value = holidayDay;
	}
	if (holidayPaid != null) {
	document.getElementById(PAIDTIMEID + index).children.item(0).value = holidayTime;
	}
	if (holidayPaid != null) {
	document.getElementById(LATETIMEID + index).children.item(0).value = lateTime;
	}
	paidTimeChange(index);
	lateTimeChange(index);
	
	if (remarks != null) {
		document.getElementById(REMARKSID + index).value = remarks;
	}
}
let excelImport = () => {
	let $from = $("#import")[0];;
	let formData = new FormData($from);
	
	$.ajax({
		url: "/Nerva/ImportExcelAction",
		data: formData,
		enctype: 'multipart/form-data',
		type: 'POST',
		processData: false,
		contentType: false, // 送信するデータをFormDataにする場合、こうしないといけない。
	
	}).then(function(data) {

		let workScheduleDTOList = data['workScheduleDTOList'];
		if (workScheduleDTOList != null) {
			for (i = 0; i < workScheduleDTOList.length; i++) {
				showWorkShedule(workScheduleDTOList, i);
			}
			clearFile();
		}

		let errorList = data;
		target = document.getElementById("registErrorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (var i = 0; i < errorList.length; i++) {
				target.innerHTML += errorList[i] + "<br>";
			}
		}
	})

}


let textImport = () => {
	
	let $from = $("#import")[0];
	let formData = new FormData($from);

	$.ajax({
		url: '/Nerva/ImportTextAction',
		data: formData,
		enctype: 'multipart/form-data',
		type: 'POST',
		processData: false,
		contentType: false, // 送信するデータをFormDataにする場合、こうしないといけない。
		
	}).then(function(data) {
		
		let workScheduleDTOList = data['workScheduleDTOList'];
		if (workScheduleDTOList != null) {
			for (i = 0; i < workScheduleDTOList.length; i++) {
				showWorkShedule(workScheduleDTOList, i);
			}
			clearFile();
		}

		let errorList = data;
		target = document.getElementById("registErrorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (var i = 0; i < errorList.length; i++) {
				target.innerHTML += errorList[i] + "<br>";
			}
		}

	})
}

/**
 *インポート完了時、表示されているファイル、フォームのファイル、ファイル名をクリアする
 */
let clearFile = () =>{
	let $showFile = document.getElementById('file');
	let $showFileName = document.getElementById('filename-out');
	let $formFile = document.getElementById('upfile');;
	let $formFileName = document.getElementById('filename');
	let $formImportPassword = document.getElementById('impassword');
	let $dialogPassword = document.getElementById("input-impass");
	$showFile.value = '';
	$formFile.value = '';
	$formFileName.value = '';
	$formImportPassword.value = '';
	$dialogPassword.value = '';
	deleteElementChild($showFileName);
}