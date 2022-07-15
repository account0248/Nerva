<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%
session.removeAttribute("origin");
session.removeAttribute("searchCondition");
%>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<title>マスタ管理</title>
</head>

<body>
	<input type="button" value="戻る" onclick="location.href='./menu.jsp'">
	<div style="margin: 30px;"></div>
	<div style="margin: auto; width: 30%;"></div>
	<div class="center">
		<div class="title center">マスタ管理</div>
		<br><br><br>
		<table align="center" height="150" width="500" >
			<tr class="mt-5  center" align="center">
				<td><a href="/Nerva/ShowCompanyMstAction">所属会社</a></td>
				<td><a href="/Nerva/ShowDepartmentMstAction">所属組織</a></td>
			</tr>
			<tr class="mt-5" align="center">
				<td><a href="/Nerva/ShowSkillTypeMstAction">スキル種別</a></td>
				<td><a href="/Nerva/ShowIndustryMstAction">業種</a></td>
			</tr>
		</table>
		<p>
			<a href="/Nerva/ShowUserMstAction" class="center">ユーザー</a>
		</p>
	</div>
</body>

</html>