<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="./css/general.css">
	<link rel="stylesheet" type="text/css" href="./css/table.css">
	<link rel="stylesheet" type="text/css" href="./css/scroll.css">
	<link rel="stylesheet" type="text/css" href="./js/jquery-ui/jquery-ui.css">
	<script type="text/javascript" src="./js/common.js"></script>
	<script type="text/javascript" src="./js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="./js/list.min.js"></script>
	<script type="text/javascript" src="./js/jquery-ui/jquery-ui.js"></script>
	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet"> 
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">	
	


	<title>所属組織マスタメンテナンス</title>
</head>

<body>
	<input type="button" value="戻る" onclick="location.href='./jsp/masterManagement.jsp'"/>
	<div class="center">
		<div class="title">所属組織マスタメンテナンス</div>
		<div class="errorMessage partition-center mt-2" id="errorList"></div>
		<h3>　≪所属組織検索≫　</h3>
		<form action="">
			<p class="partition-around">
				<span class=masterSpan>
					所属組織ID <input type="text" id=departmentId class=masterInput>
					所属組織名 <input type="text"id=departmentName class=masterInput>
				</span>
			</p>
			<br>
			<p class="partition-center">
				<span>
					<input type="button" value="検索"  id="search_button">
					<input type="button" value="新規登録" id="regist_button" >
				</span>
			</p>
		</form>
	</div>
	<div class="center">
		<h3 class="partition-center mt-5">　≪所属組織一覧≫　</h3>
	</div>
	<form action="#">

	
	
		<div id="sort" class="scroll width35">
			<table border="1" class="table-w100 tableList">
				<thead>
					<tr>
					<th class="sort employeeId_width" data-sort="departmentId">所属組織ID</th>
					<th class="sort employeeName_width" data-sort="departmentName">所属組織名</th>
					</tr>
				</thead>
				<tbody id="belongDepartment" class="list" style="word-wrap:break-word">
				</tbody>
			</table>

		</div>
		<br>
		<div class="partition-center mt-2">
			<input type="button" value="更新" id="updateDepartment" style="display: none;"/>
			<div id="dialog-confirm" style="display:none;">この内容で更新しますか？</div>
			<div id="dialog-confirm-insert-masterDepartment" style="display:none;">この内容で登録しますか？</div>
		</div>
	</form>

	<div id="display-grey"></div>
	<div class="center" id="new-registory">
		<i class="fas fa-times-circle"></i>
		<h3>　≪所属組織新規登録≫　</h3>
		<form action="">
			<div  class="errorMessage mt-2 partition-center" id="registErrorList"></div>
				<p class="partition-around mt-2">
				<span class=masterSpan>
					所属組織ID <input type="text" id="registDepartmentId" class="mr-1 masterInput">
					所属組織名 <input type="text" id="registDepartmentName" class=masterInput>
				</span>
			</p>
			<br>
			<p class="partition-center">
					<span><input type="button" value="登録"id = "btnRegistDialog"></span>
			</p>
		</form>
		<script type="text/javascript" src="./js/new_register.js"></script>
		<script type="text/javascript" src="./js/insertMasterBelongDepartment.js"></script>
	</div>
<script type="text/javascript" src="./js/searchMasterBelongDepartment.js"></script>	
<script type="text/javascript" src="./js/updateMasterBelongDepartment.js"></script>
</body>

</html>