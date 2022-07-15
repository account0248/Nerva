<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.vaile.nerva.detailEmployee.EmpInfoDTO"%>
<%@ page import="jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO"%>
<%@ page import="jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO"%>
<%@ page import="java.util.ArrayList"%>

<%
	EmpInfoDTO empInfoDTO = (EmpInfoDTO) request.getAttribute("empInfoDTO");

	@SuppressWarnings("unchecked")
	ArrayList<EmpSkillInfoDTO> empSkillInfoDTOList = (ArrayList<EmpSkillInfoDTO>) request
			.getAttribute("empSkillInfoDTOList");
	@SuppressWarnings("unchecked")
	ArrayList<EmpWorkExpDTO> empWorkExpDTOList = (ArrayList<EmpWorkExpDTO>) request
			.getAttribute("empWorkExpDTOList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/detailEmployee.js"></script>
<title>従業員詳細</title>
</head>
<body>
	<form action="EmpDetailBackPageAction" method="get">
		<input type="submit" value="戻る">
	</form>
	<form action="ShowUpdateEmpAction" method="post" name="formUpdate">
		<div class="center">
			<div class="title">従業員詳細</div>
			<br> <br>
			<p class="partition-around">
				<input type="hidden" name="employeeId"
					value=""> <span>従業員ID <input
					type="text" name="empId" value="<%=empInfoDTO.getEmployeeId()%>"
					disabled="disabled">
				</span> <span>従業員名 <input type="text"
					value="<%=empInfoDTO.getEmployeeName()%>" disabled="disabled"></span>
				<span>性別 <input type="text" value="<%=empInfoDTO.getSex()%>"
					disabled="disabled"></span> <span>生年月日 <input type="text"
					value="<%=empInfoDTO.getBirthDate()%>" disabled="disabled">
				</span>
			</p>
			<p class="partition-around">
				<span>所属会社 <input type="text"
					value="<%=empInfoDTO.getBelongCompany()%>" disabled="disabled"></span>
				<span>事業所 <input type="text"
					value="<%=empInfoDTO.getOffice()%>" disabled="disabled"></span> <span>所属組織
					<input type="text" value="<%=empInfoDTO.getDepartmentName()%>"
					disabled="disabled">
				</span> <span>役職 <input type="text"
					value="<%=empInfoDTO.getPost()%>" disabled="disabled">
				</span>
			</p>
			<p class="partition-around">
				<span>郵便番号 <input type="text"
					value="<%=empInfoDTO.getPostalCode()%>" disabled="disabled"></span>
				<span>住所 <input type="text"
					value="<%=empInfoDTO.getAddress()%>" disabled="disabled"></span> <span>TEL
					<input type="text" value="<%=empInfoDTO.getPhoneNumber()%>"
					disabled="disabled">
				</span> <span>Mail <input type="text"
					value="<%=empInfoDTO.getMail()%>" disabled="disabled"></span>
			</p>
			<br>
		</div>
		<div class="center">
			<h3>≪保有スキル≫</h3>
		</div>
		<div>
			<table border="1" style="table-layout:fixed;">
				<thead>
					<tr>
						<th class="Skill_width">スキルType</th>
						<th >内容</th>
						<th class="ExperienceYears_width">経験年数</th>
						<th class="Date_width">取得年月</th>
					</tr>
				</thead>
				<%
					for (EmpSkillInfoDTO empSkillDTO : empSkillInfoDTOList) {
				%>
				<tbody>
					<tr>
						<td><%=empSkillDTO.getSkillType()%></td>
						<td style="word-wrap:break-word"><%=empSkillDTO.getSkillDetail()%></td>
						<td><%=empSkillDTO.getExperienceYears()%></td>
						<td><%=empSkillDTO.getAcquisitionYearMonth()%></td>
					</tr>
				</tbody>
				<%
					}
				%>
			</table>
		</div>
		<div class="center">
			<h3>≪業務経験≫</h3>
		</div>
		<div>
			<table border="1" style="table-layout:fixed;">
				<thead>
					<tr>
						<th class="projectName_width">担当案件</th>
						<th class="Industry_width">担当業種</th>
						<th class="ProjectAttributionCompany_width">案件帰属会社</th>
						<th class="ManagerName_width">担当部長</th>
						<th class="teamName_width">所属チーム</th>
						<th class="Role_width">担当</th>
						<th class="ContractType_width">契約形態</th>
						<th class="MonthlyUnitPrice_width">月単価</th>
						<th class="Date_width">所属開始日</th>
						<th class="Date_width">所属完了日</th>
					</tr>
				</thead>
				<%
					for (EmpWorkExpDTO empWorkExpDTO : empWorkExpDTOList) {
				%>
				<tbody>
					<tr>
						<td style="word-wrap:break-word"><%=empWorkExpDTO.getResponsibleProject()%></td>
						<td><%=empWorkExpDTO.getResponsibleIndustry()%></td>
						<td><%=empWorkExpDTO.getProjectAttributionCompany()%></td>
						<td><%=empWorkExpDTO.getResponsibleManager()%></td>
						<td><%=empWorkExpDTO.getBelongTeam()%></td>
						<td><%=empWorkExpDTO.getRole()%></td>
						<td><%=empWorkExpDTO.getContractType()%></td>
						<%
							if (empWorkExpDTO.getMonthlyUnitPrice() == null) {
						%><td>-</td>
						<%
							} else {
						%><td>&yen;<%=empWorkExpDTO.getMonthlyUnitPrice()%></td>
						<%
							}
						%>
						<td><%=empWorkExpDTO.getTeamBelongStartDate()%></td>
						<td><%=empWorkExpDTO.getTeamBelongCompleteDate()%></td>
					</tr>
				</tbody>
				<%
					}
				%>
			</table>
		</div>
		<p class="partition-center">
			<span><input type="button" value="更新" onclick="empIdCopy()"></span>
		</p>
	</form>
</body>
</html>