
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

<title>チーム検索</title>
</head>
<body>
	<%
		List<OrderSourceDTO> orderSourceList = (List<OrderSourceDTO>) request.getAttribute("orderSourceList");

		SearchTeamPageDTO searchTeamPageDTO = (SearchTeamPageDTO) session.getAttribute("searchTeamPageDTO");
	%>



	<a href="./menu.jsp"> <input type="button" value="戻る">
	</a>

	<div class="center">
		<div class="title">チーム検索</div>
		<br>
		<div class="errorMessage" id="errorList"></div><br>
		<h3>≪検索条件≫</h3>
		<p class="partition-around">
			<span>チームID <input type="text" name="teamId" id="teamId"
				value="<%if (searchTeamPageDTO != null) {%><%=searchTeamPageDTO.getTeamId()%><%}%>"></span>
			<span>チーム名 <input type="text" name="teamName" id="teamName"
				value="<%if (searchTeamPageDTO != null) {%><%=searchTeamPageDTO.getTeamName()%><%}%>">
			</span> <span>リーダー <input type="text" name="teamLeader"
				name="teamLeader" id="teamLeader"
				value="<%if (searchTeamPageDTO != null) {%><%=searchTeamPageDTO.getTeamLeaderName()%><%}%>"></span>
		</p>
		<p class="partition-around">
			<span>担当案件ID <input type="text" name="projectId"
				id="projectId"
				value="<%if (searchTeamPageDTO != null) {%><%=searchTeamPageDTO.getProjectId()%><%}%>"></span>
			<span>担当案件名 <input type="text" name="projectName"
				id="projectName"
				value="<%if (searchTeamPageDTO != null) {%><%=searchTeamPageDTO.getProjectName()%><%}%>">
			</span> <span>発注元 <input type="text" autocomplete="on"
				name="orderSource" list="orderSourceList" id="orderSource"
				value="<%if (searchTeamPageDTO != null) {%><%=searchTeamPageDTO.getOrderSourceName()%><%}%>">
				<datalist id="orderSourceList">
					<%
						List<String> orderSourceDtoList = new ArrayList<>();
						for (OrderSourceDTO orderSource : orderSourceList) {
							String orderSourceName = orderSource.getOrderSourceName();
							orderSourceDtoList.add(orderSourceName);
						}
						List<String> dedupOrderSourceList = new ArrayList<>(new HashSet<>(orderSourceDtoList));

						for (String orderSource : dedupOrderSourceList) {
					%>
					<option value="<%=orderSource%>"><%=orderSource%></option>
					<%
						}
					%>
				</datalist></span>
		</p>
		<br>
		<p class="partition-center">
			<span><input type="button" value="検索"
				onclick="createResultTeam()"></span><span><a
				href="./ShowCreateTeamAction"> <input type="button" value="新規登録"></a></span>

		</p>
	</div>
	<div id="sort">
		<table border="1" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="sort detail_button_width" ></th>
					<th class="sort teamID_width" data-sort="teamId">チームID</th>
					<th class="sort teamName_width" data-sort="teamName">チーム名</th>
					<th class="sort CompanyName_width" data-sort="belongCompanyName">所属会社</th>
					<th class="sort teamManagerName_width" data-sort="teamManagerName">所属部長</th>
					<th class="sort teamLeaderName_width" data-sort="teamLeaderName">チームリーダー</th>
					<th class="sort projectName_width" data-sort="projectName" >担当案件</th>
					<th class="sort totalBelongMember_width" data-sort="totalBelongMember">所属人数</th>
					<th class="delete_button_width"></th>
				</tr>
			</thead>
			<tbody id="team" class="list" style="word-wrap:break-word">
			</tbody>
		</table>
	</div>
		<div id="dialog-confirm-employee-delete" style="display:none;">本当に削除しますか？</div>
<script type="text/javascript" src="../js/searchteam/searchTeam.js"></script>
<script type="text/javascript" src="../js/searchteam/deleteTeam.js"></script>
			<!-- 再検索 -->
			<%if (searchTeamPageDTO != null) {%><script>createResultTeam();</script><%}%>
</body>
</html>