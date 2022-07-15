<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.OrderSourceDTO"%>
<%@ page
	import="jp.co.vaile.nerva.searchProject.SearchProjectToStringDTO"%>
<%@ page import="jp.co.vaile.nerva.searchProject.SearchProjectPageDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/list.min.js"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>


<title>案件検索</title>
</head>
<%
List<FetchAnyMasterTableDTO> constractorList = (List<FetchAnyMasterTableDTO>) request.getAttribute("constractorData");
List<OrderSourceDTO> clientList = (List<OrderSourceDTO>) request.getAttribute("clientData");
List<FetchAnyMasterTableDTO> inductryDataList = (List<FetchAnyMasterTableDTO>) request.getAttribute("inductryData");
SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO) request.getAttribute("projectSearchPageDTO");
%>
<%
String projectId = "";
String projectName = "";
String responsibleName = "";
String contratorName = "";
String contratorId = "";
String clientName = "";
String inductryName = "";
String inductryId = "";
String totalTeamsProject = "";
String totalEmpProject = "";
%>
<%
if (projectSearchPageDTO != null) {
	projectId = projectSearchPageDTO.getProjectId();
	projectName = projectSearchPageDTO.getProjectName();
	responsibleName = projectSearchPageDTO.getResponsibleName();
	contratorId = projectSearchPageDTO.getContratorId();
	clientName = projectSearchPageDTO.getClientName();
	inductryId = projectSearchPageDTO.getInductryId();
	totalTeamsProject = projectSearchPageDTO.getTotalTeamsProject();
	totalEmpProject = projectSearchPageDTO.getTotalEmpProject();
}
%>


<body>

	<input type="button" value="戻る" onclick="location.href='./menu.jsp'">
	<div class="center">
		<div class="title">案件検索</div>
		
		<br>
		<div class="errorMessage" id="errorList"></div>
		<div id="pjtDeleteError">${ errorMsg}</div>
		<br>
		
		<h3>≪検索条件≫</h3>
		<p class="partition-around">
			<span>案件ID <input type="text" id="projectId"
				value=<%=projectId%>>

			</span> <span>案件名 <input type="text" id="projectName"
				value=<%=projectName%>>
			</span> <span>責任者 <input type="text" id="resposibleName"
				value=<%=responsibleName%>></span> <span>受注元 <select
				id="companyName">

					<%
					if (constractorList.size() > 1) {
					%>
					<option></option>
					<%
					}
					%>
					<%
					for (int i = 0; i < constractorList.size(); i++) {
					%>



					<%
					if (constractorList.get(i).getMasterDataId().equals(contratorId)) {
					%>
					<option value="<%=constractorList.get(i).getMasterDataId()%>"
						selected>
						
						<%=constractorList.get(i).getMasterData()%>
						
					</option>
					<%
					} else {
					%>
					<option value="<%=constractorList.get(i).getMasterDataId()%>">
						<%=constractorList.get(i).getMasterData()%>
						<%="</option>"%>
						<%
						}
						%>


						<%
						}
						%>
					
			</select>
			</span>
		</p>
		<p class="partition-around">
			<%
			if (clientName == null) {
			%>
			<span>発注元 <input type="text" autocomplete="on" id="text_box"
				list="clientName"> <datalist id="clientName">
					<%
					} else {
					%>
					<span>発注元 <input type="text" autocomplete="on" id="text_box"
						value="<%=clientName%>" list="clientName"> <datalist
							id="clientName">
							<%
							}
							%>
							<%
							List<String> orderSourceList = new ArrayList<>();
							for (int i = 0; i < clientList.size(); i++) {
								String orderSource = clientList.get(i).getOrderSourceName();
								orderSourceList.add(orderSource);
							}
							List<String> dedupOrderSourceList = new ArrayList<>(new HashSet<>(orderSourceList));

							for (int i = 0; i < dedupOrderSourceList.size(); i++) {

								if (dedupOrderSourceList.get(i).equals(clientName)) {
							%>
							<option selected>
								<%=dedupOrderSourceList.get(i)%>
							</option>
							<%
							} else {
							%>
							<option>
								<%=dedupOrderSourceList.get(i)%>
							</option>
							<%
							}
							%>

							<%
							}
							%>

						</datalist></span>
					<span>業種<select id="inductryName">
							<option></option>
							<%
							for (int i = 0; i < inductryDataList.size(); i++) {
							%>

							<%
							if (inductryDataList.get(i).getMasterDataId().equals(inductryId)) {
							%>
							<option value="<%=inductryDataList.get(i).getMasterDataId()%>"
								selected>
								<%=inductryDataList.get(i).getMasterData()%>
							</option>
							<%
							} else {
							%>
							<option value="<%=inductryDataList.get(i).getMasterDataId()%>">
								<%=inductryDataList.get(i).getMasterData()%>
							</option>
							<%
							}
							%>
							<%
							}
							%>
					</select></span>
					<span>参加チーム数 <input type="text" id="totalTeamsProject"
						value=<%=totalTeamsProject%>></span>
					<span>総参加人数 <input type="text" id="totalEmpProject"
						value=<%=totalEmpProject%>></span>
		</p>
		<br> <br>
		<p class="partition-center">
			<span><input type="button" value="検索" id="searchProject"></span>
			<span> <input type="button" value="新規登録"
				onclick="location.href='./ShowRegistProject'"></span>

		</p>
	</div>
	<div></div>
	<div id="sort">
		<table border="1" style="table-layout: fixed;">
			<thead>
				<tr>
					<th class="detail_button_width"></th>
					<th class="sort projectId_width" data-sort="projectId">案件ID</th>
					<th class="sort" data-sort="projectName">案件名</th>
					<th class="sort projectOwner_width" data-sort="projectOwner">案件責任者</th>
					<th class="sort CompanyName_width" data-sort="companyName">受注元</th>
					<th class="sort CompanyName_width" data-sort="client">発注元</th>
					<th class="sort Industry_width" data-sort="commercial">業種</th>
					<th class="sort Member_width" data-sort="teamNumber">参加チーム数</th>
					<th class="sort Member_width" data-sort="memberNumber">総参加人数</th>
					<th class="sort Date_width" data-sort="startProject">案件開始日</th>
					<th class="sort Date_width" data-sort="endProject">案件完了日</th>
				</tr>
			</thead>
			<tbody id="project" class="list" style="word-wrap: break-word">
			</tbody>
		</table>
	</div>

	<script src="../js/jquery-3.4.1.min.js"></script>
	<script src="../js/searchProject.js" charset="UTF-8"></script>

	<!-- 再検索 -->
	<%
	if (projectSearchPageDTO != null) {
	%><script>
		searchProject();
	</script>
	<%
	}
	%>
</body>
</html>