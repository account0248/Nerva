<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css"
	href="../js/jquery-ui/jquery-ui.css">
<script type="text/javascript"
	src="../js/managementAttendance/element.js"></script>
<script type="text/javascript"
	src="../js/managementAttendance/fileCommon.js"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/list.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
<title>勤怠情報管理</title>
</head>
<%
String employeeId = (String) request.getAttribute("employeeId");
String employeeName = (String) request.getAttribute("employeeName");
%>
<body>
	<form action="ManagementAttendanceBackPageAction" method="get">
		<input type="submit" value="戻る">
	</form>
	<div class="center">
		<div class="title">勤怠情報管理</div>
		<br>
		<div class="errorMessage partition-center mt-2" id="registErrorList"></div>
		<br>
		<p><%=employeeName%></p>
		<input type="hidden" id="employee-id" value="<%=employeeId%>">
		<input type="hidden" id="employee-name" value="<%=employeeName%>">
		<br>
		<p>
			<span> <select id="year" name="year">
					<option value=""></option>
			</select>年度
			</span> <span> <select id="month" name="month">
					<option value=""></option>
			</select>月
			</span>
		</p>
	</div>
	<br>
	<br>
	<div class="center">
		<span><input type="button" id="show-all" value="表示"></span>
	</div>
	<br>
	<br>
	<div class="center outoption">
		<div id="output-action" style="display: none;">
			<label>ファイルを選択<input type="file" id="file" accept=".xlsx,.text" /></label> 
				<span><input type="button"
				value="インポート" id="import-Dialog" /></span>
			<div id="filename-out" class="center"></div>
		</div>
	</div>
	<br>
	<div>
		<table border="1" class="center">
			<thead>
				<tr>
					<th class="day_width">月/日</th>
					<th class="dayOfWeek_width">曜日</th>
					<th class="time_width">開始</th>
					<th class="time_width">終了</th>
					<th class="time_width">休憩</th>
					<th class="time_width">法定休日勤務時間</th>
					<th class="paidPeriod_width">休暇期間等</th>
					<th class="day_width">休暇日数</th>
					<th class="time_width">休暇時間</th>
					<th class="time_width">遅早時刻</th>
					<th>備考</th>
				</tr>
			</thead>
			<tbody id="Attendance">
			</tbody>
		</table>
	</div>
	<br>
	<p class="partition-center">
		<input type="button" value="更新" id="btnOpenDialog">
	</p>
	<div id="dialog-confirm" class="center" style="display: none;">この内容で更新しますか？</div>
	<div id="dialog-pass" class="center" style="display: none;">
		パスワード
		<p>
			<input type="text" id="input-impass">
		</p>
	</div>
	<div id="dialog-export-pass" class="center" style="display: none;">
		<span>パスワード入力</span>
		<p>
			<input type="text" id="input-pass">
		</p>
		<span>パスワード再入力</span>
		<p>
			<input type="text" id="input-repass">
		</p>
	</div>
	<form action="" id="import"enctype="multipart/form-data" style="display: none;">
		<div id="out"></div>
		<input type="text" name="fileName" id="filename">
		<input type="text"name="month" id="immonth" value="">
		<input type="text" name="days" id="imdays" value="">
		<input type="text" name="password" id="impassword" value="">
		<input type="submit" value="Submit" id="imSubmit"/>
	</form>
	<script type="text/javascript" src="../js/managementAttendance/createElement.js"></script>
	<script type="text/javascript" src="../js/managementAttendance/display.js"></script>
	<script type="text/javascript" src="../js/managementAttendance/update.js"></script>
	<script type="text/javascript" src="../js/managementAttendance/import.js"></script>
	<script type="text/javascript" src="../js/managementAttendance/showElement.js"></script>
	<script type="text/javascript" src="../js/managementAttendance/managementAttedanceMain.js"></script>
</body>

</html>