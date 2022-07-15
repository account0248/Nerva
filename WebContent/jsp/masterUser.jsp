<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<%
String userId = (String) session.getAttribute("userId");
String admin = (String) request.getAttribute("admin");
String general = (String) request.getAttribute("general");
char adminValue =(char) request.getAttribute("adminValue");
char generalValue = (char) request.getAttribute("generalValue");
%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/general.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/scroll.css">
<link rel="stylesheet" type="text/css"
	href="./js/jquery-ui/jquery-ui.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="./js/list.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui/jquery-ui.js"></script>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<title>ユーザーマスタメンテナンス</title>
</head>

<body>
	<%
	List<FetchAnyMasterTableDTO> companyList = (List<FetchAnyMasterTableDTO>) request.getAttribute("companyNameList");
	List<FetchAnyMasterTableDTO> postList = (List<FetchAnyMasterTableDTO>) request.getAttribute("postList");
	%>
		<input type="button" value="戻る" onclick="location.href='./jsp/masterManagement.jsp'">
	<div class="center">
		<div class="title">ユーザーマスタメンテナンス</div>
		<div class="errorMessage partition-center mt-2" id="errorList"></div>
		<div id="sort">
			<h3 class="partition-center mt-2 ">≪ユーザー検索≫</h3>
		</div>
		<p class="partition-around">
			<span class=masterSpan>ユーザーID <input type="text" id="userId" class=masterInput> ユーザー名 <input
				type="text" id="userName"  class=masterInput>
			</span>
		<p class="partition-around">
			<span class=masterSpan>所属会社 <select name="company" id="company" class=masterInput>
					<option value="" />
					<%
					if (companyList.size() >= 1) {
					%>
					<%
					for (int i = 0; i < companyList.size(); i++) {
					%>
					<option value="<%=companyList.get(i).getMasterDataId()%>"><%=companyList.get(i).getMasterData()%></option>
					<%
					}
					%>
					<%
					}
					%>
			</select> 役職 <select name="post" id="post" class=masterInput>
					<option value="" />
					<%
					if (postList.size() >= 1) {
					%>
					<%
					for (int i = 0; i < postList.size(); i++) {
					%>
					<option value="<%=postList.get(i).getMasterDataId()%>"><%=postList.get(i).getMasterData()%></option>
					<%
					}
					%>
					<%
					}
					%>
			</select> 権限 <select name="privilege" id="adminFlg" class=masterInput>
					<option value="" />
					<option value=<%=adminValue%>><%=admin%></option>
					<option value=<%=generalValue%>><%=general%></option>
			</select>
			</span>
		</p>
		<br> <br>
		<p class="partition-center">
			<span> <input type="button" value="検索" id="search_button">
				<input type="button" value="新規登録" id="regist_button" />
			</span>
		</p>
		<h3 class="partition-center mt-5" id="update">≪ユーザー一覧≫</h3>
		<form action="#">
			<div class="scroll width95 user_scroll">
				<table border="1" class="table-w100 tableList">
					<thead>
						<tr>
							<th class="table-w12" data-sort="userId">ユーザーID</th>
							<th class="table-w24" data-sort="usertName">ユーザー名</th>
							<th class="table-w24" data-sort="password">パスワード</th>
							<th class="table-w10" data-sort="company">所属会社</th>
							<th class="table-w15" data-sort="post">役職</th>
							<th class="table-w15" data-sort="privilege">権限</th>
						</tr>
					</thead>
					<tbody id="userMaster">
					</tbody>
				</table>
			</div>
			<br>
			<div class="partition-center mt-2">
				<input type="button" value="更新" id="updateUser"
					style="display: none;" />
				<div id="dialog-confirm" style="display: none;">この内容で更新しますか？</div>
				<div id="dialog-confirm-insert-masterUser" style="display: none;">この内容で登録しますか？</div>
			</div>
		</form>


		<div id="display-grey"></div>
		<div id="new-registory">
			<i class="fas fa-times-circle"></i>

			<h3 class="mt-5">≪ユーザー新規登録≫</h3>
			<form action="">
			<div  class="errorMessage mt-2 partition-center" id="registErrorList"></div>
			<p class="partition-around mt-2">
				<p class="partition-around mt-2">
					<label for="user_id">ユーザーID</label><input type="text"
						class="registUser-form required masterInput" id="registUserId" required>
					<label for="password">パスワード</label><input type="password"
						class="registUser-form required  masterInput" id="registPassword" required>
					<label for="user_name">ユーザー名</label><input type="text"
						class="registUser-form required  masterInput" id="registUserName" required>
				</p>
				<p class="partition-around">
					<span class="regist-form masterSpan  masterSpan">所属会社 <select name="company"
						class="required masterInput" id="registCompany" required>
							<option value="" />
							<%
							if (companyList.size() >= 1) {
							%>
							<%
							for (int i = 0; i < companyList.size(); i++) {
							%>
							<option value="<%=companyList.get(i).getMasterDataId()%>"><%=companyList.get(i).getMasterData()%></option>
							<%
							}
							%>
							<%
							}
							%>
					</select></span> <span class="regist-form masterSpa  masterSpan">役職 <select name="example"
						class="required  masterInput" id="registPost" required>
							<option value="" />
							<%
							if (postList.size() >= 1) {
							%>
							<%
							for (int i = 0; i < postList.size(); i++) {
							%>
							<option value="<%=postList.get(i).getMasterDataId()%>"><%=postList.get(i).getMasterData()%></option>
							<%
							}
							%>
							<%
							}
							%>
					</select>
					</span> <span class="regist-form masterSpan">権限 <select name="privilege"
						class="required  masterInput" id="registAdminFlg" required>
							<option value="" />
							<option value="<%=adminValue%>"><%=admin%></option>
							<option value="<%=generalValue%>"><%=general%></option>
					</select>
					</span>
				</p>
				<p class="partition-center">
					<span><input type="button" value="登録" id="btnRegistDialog"></span>
				</p>
			</form>
			<script type="text/javascript" src="./js/new_register.js"></script>
			<script type="text/javascript"
				src="./js/insertMasterUser.js"></script>
		</div>
	</div>
	<script type="text/javascript" src="css/js/dialog.js"></script>
	<script type="text/javascript" src="css/js/masterUser.js"></script>
	<script type="text/javascript" src="./js/searchMasterUser.js"></script>
	<script type="text/javascript" src="./js/updateMasterUser.js"></script>

</body>

</html>