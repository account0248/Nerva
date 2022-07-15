
let getHolidayParm = (days) =>{
	let rtnParm = [];
	for (let i = 1; i <= days; i++) {

		let pushValue = $('#'+HOLIDAYID+i+ ' option:selected').val();
		rtnParm.push(pushValue);
	}
	return rtnParm;
}

let getUpdateParm = (parm) => {

	let rtnParm = [];
	for (i = 0; i < parm.length; i++) {

		let pushValue = parm.item(i).value;
		if(pushValue=='-'){
			pushValue='';
		}
		rtnParm.push(pushValue);
	}
	return rtnParm;
}

let updateWorkSchedule = () => {
	let days = document.getElementById("imdays").value;
	let startValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(STARTCSS)));
	let endValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(ENDCSS)));
	let restValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(RESTCSS)));
	let holidayWorkValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(HOLIDAYWORKCSS)));
	let holidayValue = JSON.stringify(getHolidayParm(days));
	let holidayDaysValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(HOLIDAYDAYSCSS)));
	let holidayTimeValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(HOLIDAYTIMECSS)));
	let lateTimeValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(LATETIMECSS)));
	let remarksValue = JSON.stringify(getUpdateParm(document.getElementsByClassName(REMARKS)));

	let request = {
		employeeId: document.getElementById("employee-id").value,
		year: document.getElementById("year").value,
		month: document.getElementById("month").value,
		start: startValue,
		end: endValue,
		rest: restValue,
		holidayWork: holidayWorkValue,
		holiday: holidayValue,
		holidayDays: holidayDaysValue,
		holidayTime: holidayTimeValue,
		lateTime: lateTimeValue,
		remarks: remarksValue
	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/UpdateWorkScheduleAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: true, // true:非同期(デフォルト), false:同期
		dataType: 'json',
		data: {
			json: request
		}
	}).then(function(data) {
		let errorList = data;
		target = document.getElementById("registErrorList");
		target.innerHTML = "";
		// エラーがあった場合はエラーメッセージを表示
		if (errorList != null) {
			for (var i = 0; i < errorList.length; i++) {
				target.innerHTML += errorList[i] + "<br>";
			}
		}
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './jsp/error.jsp';
	});
}