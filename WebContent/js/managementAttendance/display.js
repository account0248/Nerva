/**
 * 勤務表表示
 */
//勤務表表示
let showWorkShedule = (workScheduleDTOList, i) => {
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
		document.getElementById(STARTID + index).value = startTime;
	}
	if (endTime != null) {
		document.getElementById(ENDID + index).value = endTime;
	}
	if (restTime != null) {
		document.getElementById(RESTID + index).value = restTime;
	}
	if (holidayWork != null) {
		document.getElementById(HOLIDAYWORKID + index).value = holidayWork;
	}
	if (holidayPaid != null) {
		let holiday = document.getElementById(HOLIDAYID + index);
		let selctCount = document.getElementById(HOLIDAYID + index).childElementCount;
		for (let i = 0; i < selctCount; i++) {
			let holidayVal = holiday.children.item(i).value;
			if (holidayPaid == holidayVal) {
				holiday.options[i].selected = true;
			}
		}
		holidayOption(index);
	}
	if (holidayDay != null) {
		document.getElementById(PAIDDAYSID + index).children.item(0).value = holidayDay;
	}
	if (holidayTime != null) {
		document.getElementById(PAIDTIMEID + index).children.item(0).value = holidayTime;
	}
	if (lateTime != null) {
		document.getElementById(LATETIMEID + index).children.item(0).value = lateTime;
	}
	paidTimeChange(index);
	lateTimeChange(index);
	if (remarks != null) {
		document.getElementById(REMARKSID + index).value = remarks;
	}
}

let displayCheck = () => {

	let count1 = document.getElementById("registErrorList").childElementCount;


	if (count1 > 0) {
		target = document.getElementById("registErrorList");
		target.innerHTML = "";
	}
	let request = {
		year: document.getElementById("year").value,
		month: document.getElementById("month").value
	};
	// ajaxでservletにリクエストを送信
	return $.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/DisplayCheckAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		dataType: 'json',
		data: {
			json: request
		}
	})
}

let getWorkSchedule = () => {
	tableInsert();
	let a = document.getElementsByClassName("start");

	console.log(a.length);
	if (a.length > 1) {
		let request = {
			employeeId: document.getElementById("employee-id").value,
			year: document.getElementById("year").value,
			month: document.getElementById("month").value
		};
		// ajaxでservletにリクエストを送信
		$.ajax({
			type: "POST", // GET / POST
			url: "/Nerva/DisplayAction", // 送信先のServlet URL
			data: request, // リクエストJSON
			//async: true, // true:非同期(デフォルト), false:同期
			dataType: 'json',
			data: {
				json: request
			}
		}).then(function(data) {

			let workScheduleDTOList = data['workScheduleDTOList'];

			if (workScheduleDTOList != null) {
				for (i = 0; i < workScheduleDTOList.length; i++) {
					showWorkShedule(workScheduleDTOList, i);
				}
			}


		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			
			window.location.href = '../jsp/error.jsp';
		});

	}
}

let display = () => {

	let data = displayCheck();
	data.then(function(data) {
		let errorList = data;
		target = document.getElementById("registErrorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (let l = 0; l < errorList.length; l++) {
				target.innerHTML += errorList[l] + "<br>";
			}
			let count2 = document.getElementById("registErrorList").childElementCount;


			if (count2 < 1) {
				//ファイル、インポートボタン、エクスポートボタン追加
				showBottuns();
				//tbodyに勤務表追加
				getWorkSchedule();
			}

		}

	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './jsp/error.jsp';
	});
}
