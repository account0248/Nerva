<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jp.co.vaile.nerva.teamDetail.TeamEntryEmpDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<title>チーム詳細</title>
</head>
<body>
	<form method="get" action="./TeamDetailBackPageAction">
		<button type="submit">戻る</button>
	</form>
	<div class="center">
		<div class="title">チーム詳細</div>
		<br>
		<br>
		<p class="partition-around">
			<span>チームID <input type="text"
				value="${ teamDetailPageDTO.getTeamId() }" disabled="disabled"></span>
			<span>チーム名 <input type="text"
				value="${ teamDetailPageDTO.getTeamName() }" disabled="disabled"></span>
			<span>担当部長 <input type="text"
				value="${ teamDetailPageDTO.getResponsibleManager() }"
				disabled="disabled"></span>
		</p>
		<p class="partition-around">
			<span style="letter-spacing: -1px">チームリーダー<input type="text"
				value="${ teamDetailPageDTO.getTeamLeaderName() }"
				disabled="disabled"></span> <span>担当案件ID <input type="text"
				value="${ teamDetailPageDTO.getResponsibleProjectId() }"
				disabled="disabled"></span> <span>担当案件 <input type="text"
				value="${ teamDetailPageDTO.getResponsibleProjectName() }"
				disabled="disabled"></span>
		</p>
		<br>
	</div>
	<div class="center">
		<h3>≪参加メンバー≫</h3>
	</div>
	<div id="sort">
		<table border="1">
			<thead>
				<tr>
					<th class="detail_button_width" ></th>
					<th>従業員ID</th>
					<th>従業員名</th>
					<th>担当</th>
					<th>所属会社</th>
					<th>契約形態</th>
					<th>所属開始日</th>
					<th>月単価</th>
				</tr>
			</thead>
			<tbody id="team" class="list">
				<%
					List<TeamEntryEmpDTO> teamEntryEmpList = (ArrayList<TeamEntryEmpDTO>) request
							.getAttribute("teamEntryEmpList");
					if (teamEntryEmpList.size()!=0) {
						for (TeamEntryEmpDTO EntryEmp : teamEntryEmpList) {
				%>
				<tr>
					<td><span>
							<form method="post" action="TeamDetailSessionAction">
								<button type="submit" name="empId"
									value="<%=EntryEmp.getEmpId()%>">詳細</button>
							</form>
					</span></td>

					<td><%=EntryEmp.getEmpId()%></td>
					<td><%=EntryEmp.getEmpName()%></td>
					<td><%=EntryEmp.getRole()%></td>
					<td><%=EntryEmp.getBelongCompany()%></td>
					<td><%=EntryEmp.getContractType()%></td>
					<td><%=EntryEmp.getAssignStartDate()%></td>
					<%
						if (EntryEmp.getMonthlyUnitPrice() == null) {
					%>
					<td>-</td>
					<%
						} else {
					%>
					<td>\<%=EntryEmp.getMonthlyUnitPrice()%></td>
					<%
						}
					%>

					<%
						}
						}
					%>

			</tbody>
		</table>
	</div>
</body>
</html>