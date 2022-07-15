<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO"%>
<%@ page import="jp.co.vaile.nerva.searchemployee.SearchEmpPageDTO"%>
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
<script type="text/javascript" src="../js/searchEmployee.js"
	charset="UTF-8"></script>
<title>従業員検索</title>
</head>
<body>
	<%
	List<FetchAnyMasterTableDTO> campanyNameList = (List<FetchAnyMasterTableDTO>) request.getAttribute("campanyNameList");
	SearchEmpPageDTO searchEmpPageDTO = (SearchEmpPageDTO) session.getAttribute("searchEmpPageDTO");
	%>


	<input type="button" value="戻る" id=""
		onclick="location.href='./menu.jsp'">


	<div class="center">

		<div class="title">従業員検索</div>
		<br>
		<div class="errorMessage" id="errorList"></div>
		<div id="empDeleteError">${errorMsg}</div>
		<br>
		<h3>≪検索条件≫</h3>
		<form>
			<p class="partition-around">

				<span>従業員ID <input type="text" id="employeeId"
					value="<%if (searchEmpPageDTO != null)%><%=searchEmpPageDTO.getEmployeeId()%>"></span>
				<span>従業員名 <input type="text" id="employeeName"
					value="<%if (searchEmpPageDTO != null)%><%=searchEmpPageDTO.getEmployeeName()%>"></span>

				<span>所属会社 <select id="companyName">
						<%
						if (campanyNameList.size() > 1) {
						%>
						<option></option>
						<%
						}
						%>
						<%
						for (int i = 0; i < campanyNameList.size(); i++) {
						%>
						<%
						if (searchEmpPageDTO != null) {
						%>
						<%
						if (searchEmpPageDTO.getCompanyName().equals(campanyNameList.get(i).getMasterData())) {
						%>
						<option selected>
							<%=campanyNameList.get(i).getMasterData()%>
						</option>
						<%
						} else {
						%>
						<option>
							<%=campanyNameList.get(i).getMasterData()%>
						</option>
						<%
						}
						%>
						<%
						} else {
						%>
						<option>
							<%=campanyNameList.get(i).getMasterData()%>
						</option>
						<%
						}
						%>
						<%
						}
						%>
				</select></span>
			</p>
			<p class="partition-around">
				<span>担当案件 <input type="text" id="projectName"
					value="<%if (searchEmpPageDTO != null)%><%=searchEmpPageDTO.getProjectName()%>"></span>
				<span>所属チーム <input type="text" id="teamName"
					value="<%if (searchEmpPageDTO != null)%><%=searchEmpPageDTO.getTeamName()%>"></span>
				<span>担当部長 <input type="text" id="teamManager"
					value="<%if (searchEmpPageDTO != null)%><%=searchEmpPageDTO.getTeamManager()%>"></span>
			</p>
			<br>
			<p class="partition-around">
				<span>スキル絞込み <input type="text" id="skillFiltering"
					value="<%if (searchEmpPageDTO != null)%><%=searchEmpPageDTO.getSkillFiltering()%>"></span>
			</p>
			<br>
			<!-- onclick="createResultEmployee()" -->
			<p class="partition-center">
				<span><input type="button" value="検索"
					onclick="createResultEmp()"></span> <span><a
					href="./RegistEmpPageAction"><input type="button" value="新規登録"></a></span>
			</p>
		</form>
	</div>
	<%
	if (searchEmpPageDTO != null) {
	%><script>
		createResultEmp();
	</script>
	<%
	}
	%>
	<div></div>
	<div id="sort">
		<table border="1" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="detail_button_width"></th>
					<th class="sort employeeId_width" data-sort="employeeId">従業員ID</th>
					<th class="sort employeeName_width" data-sort="employeeName">従業員名</th>
					<th class="sort CompanyName_width" data-sort="companyName">所属会社</th>
					<th class="sort projectName_width" data-sort="projectName">担当案件</th>
					<th class="sort teamName_width" data-sort="teamName">所属チーム</th>
					<th class="sort teamManagerName_width" data-sort="teamManager">担当部長</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="employee" class="list" style="word-wrap:break-word">
			</tbody>
		</table>
	</div>
</body>
</html>