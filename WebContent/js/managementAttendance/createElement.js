
//変数宣言・挿入html宣言
//表示年度初期値,年に応じて増えるため配列
let defaultYear = [2021];

// 曜日配列
const WEEKLIST = ['日', '月', '火', '水', '木', '金', '土'];
//月配列
const monthList = [4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3];

//休暇class
const HOLIDAYCLASS = 'holiday-mandatory';

//更新用css
const STARTCSS = 'sart-v';
const ENDCSS = 'end-v';
const RESTCSS = 'rest-v';
const HOLIDAYWORKCSS = 'holidaywork-v';
const HOLIDAYPLIODCSS = 'holidaypliod-v';
const HOLIDAYDAYSCSS = 'holidaydays-v';
const HOLIDAYTIMECSS = 'holidaytime-v';
const LATETIMECSS = 'latetime-v';
const REMARKS = 'remarks-v';

//id設定
//開始
const STARTID = 'start-';
//終了
const ENDID = 'end-'
//休憩
const RESTID = 'rest-';
//休日出勤
const HOLIDAYWORKID = 'holidayworkid-';
//有給休暇等
const HOLIDAYID = 'holidayid-';
//有給日数
const PAIDDAYSID = 'paiddaysid-';
//有給時間
const PAIDTIMEID = 'paidtimeid-';
//遅早時間
const LATETIMEID = 'latetimeid-';
//備考
const REMARKSID = 'remarksid-';

//有給日数
const PAIDDAYSFLGID = 'paiddays-flg-id-';
//有給時間
const PAIDTIMEFLGID = 'paidtime-flg-id-';
//遅早時間
const LATETIMEFLGID = 'latetime-flg-id-';

//有給日数
const PAIDDAYSDEFID = 'paiddays-default-id-';
//有給時間
const PAIDTIMEDEFID = 'paidtime-default-id-';
//遅早時間
const LATETIMEDEFID = 'latetime-default-id-';


/**
* Excel画面追加
*/
let tableInsert = () => {
	let $table = document.getElementById("Attendance");
	let $immonth = document.getElementById("immonth");
	let $imdays = document.getElementById("imdays");
	let $exyear = document.getElementById("ex-year");
	let $exmonth = document.getElementById("ex-month");
	let year = parseInt(document.getElementById("year").value, 10);
	let month = document.getElementById("month").value;
	let holidayPeriodDTOList;
	let request = {
		employeeId: document.getElementById("employee-id").value,
		year: document.getElementById("year").value,
		month: document.getElementById("month").value
	};
	// ajaxでservletにリクエストを送信
	$.ajax({
		type: "POST", // GET / POST
		url: "/Nerva/DisplayHolidayPeriodAction", // 送信先のServlet URL
		data: request, // リクエストJSON
		async: false,
		dataType: 'json',
		data: {
			json: request
		}
	}).done(function(data) {

		holidayPeriodDTOList = data['holidayPeriodDTOList'];

		//子要素削除
		deleteElementChild($table);

		//エクスポートフォームに追加
		$exyear.value = year;
		$exmonth.value = month;

		//月の最終日取得
		if (month == 1 || month == 2 || month == 3) year = year + 1;
		
		let $date = new Date(year, month, 0);
		let lastDay = $date.getDate();

		//インポートフォームに追加
		$immonth.value = month;
		$imdays.value = lastDay;

		//html挿入
		for (let i = 1; i <= lastDay; i++) {
			
			let week = getDayOfWeek(year, month, i);

			createData($table.insertRow(-1), month, i, week, holidayPeriodDTOList);
		}


	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		window.location.href = './jsp/error.jsp';
	});

}



//各メソッド

/**有給休暇等オプション文字列作成
 */
let optionHoliday = (holidayPeriodDTOList) => {
	let optionHolidayStr = getOption('', '');
	for (i = 0; i < holidayPeriodDTOList.length; i++) {
		optionHolidayStr += getOption(holidayPeriodDTOList[i].holidayId, holidayPeriodDTOList[i].holidayName);
	}
	return optionHolidayStr;
}

let holidayShowValue = (value) => {

	if (value == null) {
		value = '-';
	}
	return value;
}

let getHolidayHidden = (holidayPeriodDTOList, i) => {
	let holidayHidden = '';
	for (c = 0; c < holidayPeriodDTOList.length; c++) {
		let holidayDayValue = holidayShowValue(holidayPeriodDTOList[c].holidayDays);
		let holidayTimeValue = holidayShowValue(holidayPeriodDTOList[c].holidayTime);
		let lateTimeValue = holidayShowValue(holidayPeriodDTOList[c].lateTime);

		//休暇日数
		holidayHidden += '<input type="hidden" id="' + PAIDDAYSFLGID + holidayPeriodDTOList[c].holidayId + i + '"value="' + holidayPeriodDTOList[c].holidayDaysFlg + '"/>';
		holidayHidden += '<input type="hidden" id="' + PAIDDAYSDEFID + holidayPeriodDTOList[c].holidayId + i + '"value="' + holidayDayValue + '"/>';
		//休暇時間
		holidayHidden += '<input type="hidden" id="' + PAIDTIMEFLGID + holidayPeriodDTOList[c].holidayId + i + '"value="' + holidayPeriodDTOList[c].holidayTimeFlg + '"/>';
		holidayHidden += '<input type="hidden" id="' + PAIDTIMEDEFID + holidayPeriodDTOList[c].holidayId + i + '"value="' + holidayTimeValue + '"/>';
		//遅早時刻
		holidayHidden += '<input type="hidden" id="' + LATETIMEFLGID + holidayPeriodDTOList[c].holidayId + i + '"value="' + holidayPeriodDTOList[c].lateTimeFlg + '"/>';
		holidayHidden += '<input type="hidden" id="' + LATETIMEDEFID + holidayPeriodDTOList[c].holidayId + i + '"value="' + lateTimeValue + '"/>';


	}
	return holidayHidden;
}

/**
 * 曜日取得メソッド
 * @param {*} year 
 * @param {*} month 
 * @param {*} day 
 * @returns 
 */
let getDayOfWeek = (year, month, day) => {


	// 入力された数値から日付オブジェクトを作る
	let $date = new Date(year, month - 1, day);

	return WEEKLIST[$date.getDay()];
}

/**
 * 2021年度から現在の年度までを取得
 * @param {*} year 
 * @returns 
 */
let getSelectYear = (year) => {



	for (i = defaultYear[0] + 1; i <= year; i++) {

		defaultYear.push(i);

	}
	return defaultYear;


}

/**
 * 子要素の削除
 * @param {*} $element 
 */
let deleteElementChild = ($element) => {

	while ($element.lastChild) {

		$element.removeChild($element.lastChild);
	}
}

/**
 * span挿入
 * @param {*} $element 
 */
let createSpan = ($element, inElement, className) => {

	let $new_element = document.createElement('span');

	if (className) $new_element.classList.add(className);

	$new_element.innerHTML = inElement;
	$element.appendChild($new_element);


}

/**
 * 有給休暇等選択時処理
 * @param {*} i 
 */
let holidayOption = (i) => {

	//有給休暇等セレクタvalue取得
	let selectHoliday = document.getElementById(HOLIDAYID + i).value;
	//有給日数要素取得
	let $tbodyPaiddays = document.getElementById(PAIDDAYSID + i);
	//有給時間要素取得
	let $tbodyPaidtime = document.getElementById(PAIDTIMEID + i);
	//遅早時刻要素取得
	let $tbodyLatetime = document.getElementById(LATETIMEID + i);

	$tbodyPaiddays.innerHTML = getHidden('');
	$tbodyPaiddays.children.item(0).classList.add(HOLIDAYDAYSCSS);

	$tbodyPaidtime.innerHTML = getTextTimeChange('paidTimeChange(' + i + ')');
	$tbodyPaidtime.children.item(0).classList.add(HOLIDAYTIMECSS);

	$tbodyLatetime.innerHTML = getTextTimeChange('lateTimeChange(' + i + ')');
	$tbodyLatetime.children.item(0).classList.add(LATETIMECSS);

	//有給日数、有給時間、遅早時刻を初期化
	$tbodyPaiddays.value = '';
	$tbodyPaidtime.value = '--:--';
	$tbodyLatetime.value = '--:--';

	if (selectHoliday.length != 0) {

		//休暇日数フラグ取得
		let paidFlgvalue = document.getElementById(PAIDDAYSFLGID + selectHoliday + i).value;
		//表示する有給日数取得
		let paidDayDefValue = document.getElementById(PAIDDAYSDEFID + selectHoliday + i).value;
		//休暇時間フラグ取得
		let paidTimeFlgValue = document.getElementById(PAIDTIMEFLGID + selectHoliday + i).value;
		//表示する休暇時間取得
		let paidTimeDefValue = document.getElementById(PAIDTIMEDEFID + selectHoliday + i).value;
		//遅早時刻フラグ取得
		let lateTimeFlgValue = document.getElementById(LATETIMEFLGID + selectHoliday + i).value;
		//表示する遅早時刻取得
		let lateTimeDefValue = document.getElementById(LATETIMEDEFID + selectHoliday + i).value;

		//子要素
		let paiddaysChild = $tbodyPaiddays.children;
		let paidtimeChild = $tbodyPaidtime.children;
		let latetimeChild = $tbodyLatetime.children;

		if (paidFlgvalue == 'true') {

			$tbodyPaiddays.innerHTML = getText('', '');
			paiddaysChild = $tbodyPaiddays.children;
			paiddaysChild.item(0).classList.add('required4');
			paiddaysChild.item(0).classList.add(HOLIDAYDAYSCSS);
		} else {

			$tbodyPaiddays.innerHTML = paidDayDefValue + getHidden(paidDayDefValue);
			paiddaysChild.item(0).classList.add(HOLIDAYDAYSCSS);
		}

		if (paidTimeFlgValue == 'true') {

			paidtimeChild.item(0).classList.add('required4');
		} else if(paidTimeDefValue == '-') {
			
			$tbodyPaidtime.innerHTML = getText('','');
			paidtimeChild = $tbodyPaidtime.children;
			paidtimeChild.item(0).value = paidTimeDefValue;
			paidtimeChild.item(0).classList.add(HOLIDAYTIMECSS);
			paidtimeChild.item(0).setAttribute("disabled", "disabled");
			

		} else{
			paidtimeChild.item(0).value = paidTimeDefValue;
			paidtimeChild.item(0).setAttribute("disabled", "disabled");
		}

		if (lateTimeFlgValue == 'true') {

			latetimeChild.item(0).classList.add('required4');
		} else if(lateTimeDefValue == '-') {
			
			$tbodyLatetime.innerHTML = getText('','');
			latetimeChild = $tbodyLatetime.children;
			latetimeChild.item(0).value = lateTimeDefValue;
			latetimeChild.item(0).classList.add(LATETIMECSS);
			latetimeChild.item(0).setAttribute("disabled", "disabled");
		}
	}
}

/**
 * テーブルに挿入
 * @param {*} row 
 * @param {*} month 
 * @param {*} i 
 * @param {*} week 
 */
let createData = (row, month, i, week, holidayPeriodDTOList) => {

	let selectHoliday = getSelectAttedance('', '', HOLIDAYID + i, i, optionHoliday(holidayPeriodDTOList));
	let holidayHidden = getHolidayHidden(holidayPeriodDTOList, i);

	let cell1 = row.insertCell(-1);
	cell1.innerHTML = `${month}/${i}`;

	let cell2 = row.insertCell(-1);
	cell2.innerHTML = week;

	let cell3 = row.insertCell(-1);
	cell3.innerHTML = getTextTimeChange('startChane(' + i + ')');
	cell3.children.item(0).classList.add('start')
	cell3.children.item(0).classList.add(STARTCSS)
	cell3.children.item(0).id = STARTID + i;

	let cell4 = row.insertCell(-1);
	cell4.innerHTML = getTextTimeChange('endChane(' + i + ')');
	cell4.children.item(0).id = ENDID + i;
	cell4.children.item(0).classList.add('end');
	cell4.children.item(0).classList.add(ENDCSS);

	let cell5 = row.insertCell(-1);
	cell5.innerHTML = getTextTimeChange('restChane(' + i + ')');
	cell5.children.item(0).id = RESTID + i;
	cell5.children.item(0).classList.add('rest');
	cell5.children.item(0).classList.add(RESTCSS);

	let cell6 = row.insertCell(-1);
	let cell6Str = getTextTime('', 'attendanceText_width');
	if (week != '土') cell6Str = cell6Str.substring(0, cell6Str.length - 1) + 'disabled>';
	cell6.innerHTML = cell6Str;
	cell6.children.item(0).id = HOLIDAYWORKID + i;
	cell6.children.item(0).classList.add(HOLIDAYWORKCSS);

	let cell7 = row.insertCell(-1);
	cell7.innerHTML = selectHoliday + holidayHidden;
	cell7.children.item(0).classList.add(HOLIDAYPLIODCSS);

	let cell8 = row.insertCell(-1);
	cell8.id = PAIDDAYSID + i;
	cell8.innerHTML = getHidden('');
	cell8.children.item(0).classList.add(HOLIDAYDAYSCSS);

	let cell9 = row.insertCell(-1);
	cell9.innerHTML = getTextTimeChange('paidTimeChange(' + i + ')');
	cell9.id = PAIDTIMEID + i;
	cell9.children.item(0).classList.add(HOLIDAYTIMECSS);

	let cell10 = row.insertCell(-1);
	cell10.innerHTML = getTextTimeChange('lateTimeChange(' + i + ')');
	cell10.id = LATETIMEID + i;
	cell10.children.item(0).classList.add(LATETIMECSS);

	let cell11 = row.insertCell(-1);
	cell11.innerHTML = getText('', 'attendanceText_width');
	cell11.children.item(0).id = REMARKSID + i;
	cell11.children.item(0).classList.add(REMARKS);

}

/**
* セレクタにオプション追加メソッド
* @param {*} selectDocument 
* @param {*} value 
* @param {*} textContent 
*/
let selectOptionInsert = ($selectDocument, value, textContent) => {
	//オプション属性作成
	const $optionElement = document.createElement('option');

	$optionElement.value = value;
	$optionElement.textContent = textContent;

	//挿入
	$selectDocument.appendChild($optionElement);
}


/**
*ページ開いたときに年セレクタの書き換え 
*/
let onload = () => {
	// ページ読み込み時に実行したい処理
	let $selectYear = document.getElementById("year");

	//現在日時取得
	let $date = new Date();
	//年取得
	let yearNow = $date.getFullYear();
	//月取得
	let monthNow = $date.getMonth() + 1;

	//年度調整
	if (monthNow == 1 || monthNow == 2 || monthNow == 3) yearNow = yearNow - 1;

	//2021年度から現在までの年度取得
	let year = getSelectYear(yearNow);

	//オプションに追加
	for (i = 0; i < year.length; i++) {

		selectOptionInsert($selectYear, year[i], year[i]);

	}
};

//月のセレクタの中身変更
let onchange = ($select) => {

	//月のセレクタ取得
	let $selectMonth = document.getElementById("month");

	//セレクタの中身の数
	let l = $selectMonth.length;

	//一番上を残してオプションを削除
	for (i = 1; i < l; i++) {

		let u = $selectMonth.length - 1;

		$selectMonth.remove(u);
	}

	//年度が選択された場合の処理
	if ($select.value.length != 0) {

		//選択された年度格納
		let selectYear = $select.value;

		//現在日時取得
		let $date = new Date();
		//年取得
		let yearNow = $date.getFullYear();
		//月取得
		let monthNow = $date.getMonth() + 1;

		//年度調整
		if (monthNow == 1 || monthNow == 2 || monthNow == 3) yearNow = yearNow - 1;

		//今年度だった場合の処理
		if (selectYear == yearNow) {
			let i = 0;
			let monthValue = monthList[i];
			if (monthList[i] < 10) {
				monthValue = '0' + monthList[i];
			}
			selectOptionInsert($selectMonth, monthValue, monthList[i]);

			while (monthNow !== monthList[i]) {
				monthValue = monthList[i + 1];
				if (monthList[i + 1] < 10) {
					monthValue = '0' + monthList[i + 1];
				}
				selectOptionInsert($selectMonth, monthValue, monthList[i + 1]);

				i++;
			}
		} else {
			for (i = 0; i < 12; i++) {
				let monthValue = monthList[i];

				if (monthList[i] < 10) {
					monthValue = '0' + monthList[i];
				}
				selectOptionInsert($selectMonth, monthValue, monthList[i]);
			}
		}
	}
}