<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<title>案件詳細</title>
</head>
<body>
	<form method="get" action="./DetailPjtBackPageAction">
		<button type="submit">戻る</button>
	</form>
	<div class="center">
		<div class="title">案件詳細</div>
		
		<br>
		<%
		ArrayList<String> errorMsgList = (ArrayList<String>) request.getAttribute("errorMsgList");
		if (errorMsgList != null) {
			for (String errorMsg : errorMsgList) {
		%>
		<div class="errorMessage">
			<%=errorMsg%>
		</div>
		<%
		}
		}
		%>
		<br>
		
		<p class="partition-around">
			<span>案件ID <input type="text"
				value="${ projectDetailPageDto.projectId } " disabled="disabled"></span>
			<span>案件名 <input type="text"
				value="${ projectDetailPageDto.projectName }" disabled="disabled"></span>
			<span>責任者 <input type="text"
				value="${ projectDetailPageDto.responsibleName }"
				disabled="disabled">
			</span>
		</p>
		<p class="partition-around">
			<span>受注元 <input type="text"
				value="${ projectDetailPageDto.contractorName }" disabled="disabled"></span>
			<span>発注元 <input type="text"
				value="${ projectDetailPageDto.clientName }" disabled="disabled"></span>
			<span>業種 <input type="text"
				value="${ projectDetailPageDto.industryName }" disabled="disabled"></span>
		</p>
		<br>
		<p class="partition-around">
			<span>案件開始日 <input type="text"
				value="${ projectDetailPageDto.projectStartDate }"
				disabled="disabled"></span> <span>案件完了日 <input type="text"
				value="${ projectDetailPageDto.projectCompleteDate }"
				disabled="disabled"></span>
		</p>
	</div>
	<div class="center">
		<h3>≪参加チーム≫</h3>
	</div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th class="detail_button_width"></th>
					<th>チームID</th>
					<th>チーム名</th>
					<th>所属会社</th>
					<th>所属部長</th>
					<th>チームリーダー</th>
					<th>配属開始日</th>
					<th>配属完了日</th>
				</tr>
			</thead>
			<tbody id="team" class="list">

				<%
				ArrayList<ProjectEntryTeamDTO> projectEntryTeamList = (ArrayList<ProjectEntryTeamDTO>) request
						.getAttribute("projectEntryTeamList");
				if (projectEntryTeamList != null) {
					for (ProjectEntryTeamDTO projectEntryTeamDto : projectEntryTeamList) {
				%>
				<tr>
					<td><span><form method="post"
								action="./DetailProjectSessionAction">
								<button type="submit" name="teamId"
									value="<%=projectEntryTeamDto.getTeamId()%>">詳細</button>
							</form></span></td>
					<td><%=projectEntryTeamDto.getTeamId()%></td>
					<td><%=projectEntryTeamDto.getTeamName()%></td>
					<td><%=projectEntryTeamDto.getBelongCompanyName()%></td>
					<td><%=projectEntryTeamDto.getTeamManagerName()%></td>
					<%
					if (projectEntryTeamDto.getTeamLeaderName() == null) {
					%>
					<td>-</td>
					<%
					} else {
					%>
					<td><%=projectEntryTeamDto.getTeamLeaderName()%></td>
					<%
					}
					%>
					<td><%=projectEntryTeamDto.getAssignStartDate()%></td>
					<%
					if (projectEntryTeamDto.getAssignCompleteDate() == null) {
					%>
					<td>-</td>
					<%
					} else {
					%>
					<td><%=projectEntryTeamDto.getAssignCompleteDate()%></td>
					<%
					}
					%>
				</tr>

				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>
	<form method="post" action="./ShowUpdateProjectAction">
		<p class="partition-center">
			<span>
				<button type="submit">更新</button>
			</span>
		</p>
	</form>
</body>
</html>

