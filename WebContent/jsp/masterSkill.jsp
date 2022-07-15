<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/general.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/scroll.css">
<link rel="stylesheet" type="text/css" href="./js/jquery-ui/jquery-ui.css">
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="./js/list.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui/jquery-ui.js"></script>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

<title>スキル種別マスタメンテナンス</title>
</head>
<%
String years = (String) request.getAttribute("years");
String dateOfAcquisition = (String) request.getAttribute("dateOfAcquisition");
char yearsValue = (char) request.getAttribute("yearsValue");
char dateOfAcquisitionValue = (char) request.getAttribute("dateOfAcquisitionValue");
%>
<body>
	<input type="button" value="戻る" onclick="location.href='./jsp/masterManagement.jsp'">
	<div class="center">
		<div class="title">スキル種別マスタメンテナンス</div>
		<div class="errorMessage partition-center mt-2" id="errorList"></div>

		<h3 class="partition-center mt-2 ">≪スキル種別検索≫</h3>
		<form action="#">
			<p class="partition-around">
				<span class=masterSpan>
					スキル種別ID
					<input type="text" id=skillTypeId class=masterInput>
					スキル種別名
					<input type="text" id=skillTypeName class=masterInput>
					年数/取得日
					<select name="example" id=yearsDateOfAcquisition class=masterInput>
						<option value=""></option>
						<option value="<%=yearsValue%>"><%=years%></option>
						<option value="<%=dateOfAcquisitionValue%>"><%=dateOfAcquisition%></option>
					</select>
				</span>
			</p>
			<br>
			<p class="partition-center">
				<span>
					<input type="button" value="検索" id="search_button">
					<input type="button" value="新規登録" id="regist_button">
				</span>
			</p>
		</form>
		<h3 class="partition-center mt-5">≪スキル種別一覧≫</h3>
	</div>
	<form action="#">
		<div id="sort" class="width45 scroll">
			<table border="1" class="table-w100 tableList">
				<thead>
					<tr>
						<th class="table-w25">スキル種別ID</th>
						<th class="table-w50">スキル種別名</th>
						<th class="table-w25">年数/取得日</th>
					</tr>
				</thead>
				<tbody id="skillType" class="list" style="word-wrap: break-word">
				</tbody>
			</table>
		</div>
		<div class="partition-center mt-2">
			<input type="button" value="更新" id="updateSkillType" style="display:none;"/>
			<div id="dialog-confirm" style="display: none;">この内容で更新しますか？</div>
			<div id="dialog-confirm2" style="display: none;">この内容で登録しますか？</div>
		</div>
	</form>

	<div id="display-grey"></div>
	<div id="new-registory">
		<i class="fas fa-times-circle"></i>
		<h3 class="partition-center mt-5">≪スキル新規登録≫</h3>
		<div class="errorMessage mt-2 partition-center" id="registErrorList"></div>
		<form action="">
			<p class="partition-around mt-2">
				<span class=masterSpan>
					<label for="role_id">スキル種別ID</label>
					<input type="text" id="registSkillTypeId" class="mr-1 masterInput">
					<label for="role_name">スキル種別名</label>
					<input type="text" id="registSkillTypeName" class=masterInput>
					年数/取得日
					<select name="example" id="registYearsDateOfAcquisition" class=masterInput>
						<option value=""></option>
						<option value="<%=yearsValue%>"><%=years%></option>
						<option value="<%=dateOfAcquisitionValue%>"><%=dateOfAcquisition%></option>
					</select>
				</span>
			</p>
			<br>
			<p class="partition-center">
				<span>
					<input type="button" value="登録" id="insertSkillType">
				</span>
			</p>
		</form>
	</div>
	<script type="text/javascript" src="js/new_register.js"></script>
	<script type="text/javascript" src="./js/dialog.js"></script>
	<script type="text/javascript" src="js/searchMasterSkillType.js"></script>
	<script type="text/javascript" src="js/updateMasterSkillType.js"></script>
	<script type="text/javascript" src="js/insertMasterSkillType.js"></script>
</body>

</html>