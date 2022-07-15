<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<title>メインメニュー</title>
</head>
<body>
	<%
	boolean adminFlg = (boolean) session.getAttribute("adminFlg");
	%>

	<div style="margin: 30px;"></div>
	<div style="margin: auto; width: 30%;"></div>
	<div class="center">
		<div class="title center">メインメニュー</div>
		<br>
		<table align="center" height="150" width="500">
			<tr class="mt-5  center" align="center">
				<td>
					<a href="ShowSearchTeamAction">チーム検索</a>
				</td>
				<td>
					<a href="./ShowSearchEmpAction">従業員検索</a>
				</td>
			</tr>
			<tr class="mt-5" align="center">
				<td>
					<a href="./ShowTransferApplicationAction">移管承認</a>
				</td>
				<td>
					<a href="./ShowProjectAction">案件検索</a>
				</td>
		</table>
		<%
		if (adminFlg) {
		%>
		<p>
			<a href="./masterManagement.jsp" class="center">マスタ管理</a>
		</p>
		<%
		}
		%>
		<br>
		<p class="mt-5">
			<a href="LogoutAction">ログアウト</a>
		</p>

	</div>
</body>
</html>



<!-- jsでリンクでサーブレットにpost遷移する -->
<!-- 	<div class="center">
		<div class="title">メインメニュー</div>
		<br>
		<p class="partition-around">
		<form method="post" name="form1" action="ShowSearchTeamAction">
			<input type="hidden" name="user_name" value="チーム検索">
			<a href="javascript:form1[0].submit()">チーム検索</a>
		</form>
		<form method="post" name="form1" action="ShowSerchEmpAction">
			<input type="hidden" name="user_name" value="従業員検索">
			<a	href="javascript:form1[1].submit()">従業員検索</a>
		</form>
		</p>
		<br>
		<p class="partition-around">
		<form method="post" name="form1" action="ShowTransferApplicationAction">
			<input type="hidden" name="user_name" value="移管承認"> <a
				href="javascript:form1[2].submit()">移管承認</a>
		</form>
				<form method="post" name="form1" action="ShowProjectAction">
			<input type="hidden" name="user_name" value="案件検索"> <a
				href="javascript:form1[2].submit()">案件検索</a>
		</form>
		</p>
		<br>
		<p class="partition-around">
				<form method="post" name="form1" action="link.php">
			<input type="hidden" name="user_name" value="ユーザー管理"> <a
				href="javascript:form1[2].submit()">ユーザー管理</a>
		</form>
				<form method="post" name="form1" action="LogoutAction">
			<input type="hidden" name="user_name" value="ログアウト"> <a
				href="javascript:form1[2].submit()">ログアウト</a>
		</form>
	</div> -->
