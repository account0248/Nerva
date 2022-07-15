/*
* 挿入するhtml
*/

//テキストボックス
getText = (name, className) => {
	let text = '<input type="text" class="' + className + '" name="' + name + '" value="">';
	return text;
};

//オプション
getOption = (value, text) => {
	let option = '<option value="' + value + '">' + text + '</option>';
	return option;
};

//タイム
getTextTime = (name, className) => {
	let textTime = '<input type="time" class="' + className + '" name="' + name + '">';
	return textTime;

};

//セレクタ
getSelect = (name, className, option) => {

	let select = '<select name="' + name + '" class="' + className + '">';
	select += option;
	select += '</select>';
	return select;
};

//勤務表セレクタ
getSelectAttedance = (name, className, id, i, option) => {

	let select = '<select name="' + name + '" class="' + className + '"id="' + id + '" onchange="HolidaySelect(' + i + ')">';
	select += option;
	select += '</select>';
	return select;
};

//hidden
getHidden = (value) => {
let hidden = '<input type="hidden" value="'+value+'"/>';
return hidden;
}

//タイム
getTextTimeChange = (change) => {
	let textTime = '<input type="time" onchange="'+ change + '">';
	return textTime;

};
