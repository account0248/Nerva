<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO"%>
<%@page import="jp.co.vaile.nerva.updateProject.AddableTeamDTO"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="../js/jquery-ui/jquery-ui.css">

<title>案件更新</title>
</head>
<body>

	<form method="post" action="./ShowProjectDetailAction"
		name="showProjectDetailAction">
		<button type="submit">戻る</button>
	</form>
	<form action="./UpdateProjectAction" method="post"
		name="doUpdateProject"></form>
	<!-- onSubmit="return pushUpdate()">   -->
	<div class="center">
		<div class="title">案件更新</div>
		
		<br>
		<div class="errorMessage" id="errorList"></div>
		<br>
		
		<p class="partition-around">
			<span>案件ID <input type="text"
				value="${ updatePjtPageDto.projectId }" disabled="disabled"></span>
			<span>案件名 <input type="text"
				value="${ updatePjtPageDto.projectName }" disabled="disabled"></span>
			<span>責任者 <input type="text"
				value="${ updatePjtPageDto.responsibleName }" disabled="disabled"></span>
		</p>
		<p class="partition-around">
			<span>受注元 <input type="text"
				value="${ updatePjtPageDto.contractorName }" disabled="disabled"></span>
			<span>発注元 <input type="text"
				value="${ updatePjtPageDto.clientName }" disabled="disabled"></span>
			<span>業種 <input type="text"
				value="${ updatePjtPageDto.industryName }" disabled="disabled"></span>
		</p>
		<br>
		<p class="partition-around">
			<span>案件開始日 <input required class="required" type="date"
				name="projectStartDate"
				value="${ updatePjtPageDto.projectStartDate }"></span> <span>案件完了日
				<input type="date" name="projectCompleteDate"
				value="${ updatePjtPageDto.projectCompleteDate }">
			</span>
		</p>
	</div>
	<div class="center">
		<h3>≪参加チーム≫</h3>
	</div>
	<div class="partition-right">
		<span> <input type="button" value="追加" onclick="addTeam()">
		</span>
	</div>
	<!-- 		<div> -->
	<table border="1">
		<thead>
			<tr>
				<th></th>
				<th>チームID</th>
				<th>チーム名</th>
				<th>所属会社</th>
				<th>所属部長</th>
				<th>チームリーダー</th>
				<th>配属開始日</th>
				<th>配属完了日</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="team" class="list">
			<%
			ArrayList<ProjectEntryTeamDTO> updatePjtEntryTeamList = (ArrayList<ProjectEntryTeamDTO>) session
					.getAttribute("updatePjtEntryTeamList");
			if (updatePjtEntryTeamList != null) {
				for (ProjectEntryTeamDTO updatePjtEntryTeamDto : updatePjtEntryTeamList) {
			%>
			<tr>
				<td></td>
				<td><%=updatePjtEntryTeamDto.getTeamId()%></td>
				<td><%=updatePjtEntryTeamDto.getTeamName()%></td>
				<td><%=updatePjtEntryTeamDto.getBelongCompanyName()%></td>
				<td><%=updatePjtEntryTeamDto.getTeamManagerName()%></td>
				<%
				if (updatePjtEntryTeamDto.getTeamLeaderName() != null) {
				%>
				<td><%=updatePjtEntryTeamDto.getTeamLeaderName()%></td>
				<%
				} else {
				%><td></td>
				<%
				}
				%>
				<td><%=updatePjtEntryTeamDto.getAssignStartDate()%><input
					type="hidden" name="assignStartDate[]"
					value="<%=updatePjtEntryTeamDto.getAssignStartDate()%>"></td>
				<td><input type="date" name="assignCompleteDate[]"
					<%if (updatePjtEntryTeamDto.getAssignCompleteDate() != null) {%>
					value="<%=updatePjtEntryTeamDto.getAssignCompleteDate()%>"
					<%} else {%> value=""> <%
 }
 %></td>
				<td></td>
			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>
	<div style="margin-top: 13px;" class="partition-center">
		<span><input type="button" value="更新" id="updateProject"></span>
		<span> <input type="button" value="削除" id="deleteProject"></span>
		<form action="./DeleteProjectAction" method="post"
			onSubmit="return pushDelete()" name="deleteProjectAction"></form>
	</div>
	<div id="dialog-confirm-employee-update" style="display: none;">この内容で更新しますか？</div>
	<div id="dialog-confirm-employee-delete" style="display: none;">本当に削除しますか？</div>
	<script type="text/javascript" src="../js/updateProject.js"></script>
</body>
</html>
