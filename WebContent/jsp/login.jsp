<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.OrderSourceDTO"%>
<%@ page import="jp.co.vaile.nerva.searchTeam.SearchTeamPageDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/list.min.js"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="../js/jquery-ui/jquery-ui.css">
<title>ログイン画面</title>
</head>

<body>
<%
//リクエストスコープからのデータの取得
String errorList = (String)request.getAttribute("errorList");
%>

	<div class="center">
		<div class="target">
			<div class="title">配属管理システムNerva</div>
			<br>
				<div class="errorMessage">
					<% if ( errorList != null ) { %><%= errorList %><% } %>
					</div>
					<div class="loginForm">
						<form method="POST" action="LoginAction" name="loginform" style="width:100%; height:100%;">
					<div class="formRow">
						<span>ユーザーID</span><input class="formRowInput" type="text" name="userId">
					</div>
					<br>
					<div class="formRow">
						<span>パスワード</span><input class="formRowInput" type="password" name="password">
					</div>
					<br>
					<div>
						<input class="loginBtn" type="submit" value="ログイン">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>