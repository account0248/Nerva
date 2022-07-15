
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.vaile.nerva.detailEmployee.EmpInfoDTO"%>
<%@ page import="jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO"%>
<%@ page import="jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.skillMaster.SkillTypeDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO"%>
<%
EmpInfoDTO empInfoDTO = (EmpInfoDTO) session.getAttribute("empInfoDTO");

@SuppressWarnings("unchecked")
ArrayList<EmpSkillInfoDTO> empSkillInfoDTOList = (ArrayList<EmpSkillInfoDTO>) session
		.getAttribute("empSkillInfoDTOList");
@SuppressWarnings("unchecked")
ArrayList<EmpWorkExpDTO> empWorkExpDTOList = (ArrayList<EmpWorkExpDTO>) session.getAttribute("empWorkExpDTOList");
@SuppressWarnings("unchecked")
HashMap<String, List<FetchAnyMasterTableDTO>> masterTableInfo = (HashMap<String, List<FetchAnyMasterTableDTO>>) request
		.getAttribute("masterTableInfo");
@SuppressWarnings("unchecked")
List<SkillTypeDTO> skillTypeList = (ArrayList<SkillTypeDTO>) session.getAttribute("skillTypeList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<!--  -->
<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="../js/jquery-ui/jquery-ui.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/updateEmployee/backPage.js"></script>
<script type="text/javascript" src="../js/updateEmployee/documentObject.js"></script>
<script type="text/javascript" src="../js/updateEmployee/updateEmployee.js"></script>
<script type="text/javascript" src="../js/updateEmployee/skillInfo.js"></script>
<script type="text/javascript" src="../js/updateEmployee/workExperience.js"></script>
<title>従業員更新</title>
</head>
<body>
	<input type="button" value="戻る" onclick="UpdateEmpBackPage()" class="button">
	<form action="UpdateEmpAction" method="post" name="UpdateEmp">
		<div class="center">
			<div class="title">従業員更新</div>
			<br>
			<div class="errorMessage" id="errorMsg">
				<%
				@SuppressWarnings("unchecked")
				List<String> errorList = (List<String>) request.getAttribute("errorMsg");
				if (errorList != null) {
					for (int i = 0; i < errorList.size(); i++) {
						out.println(errorList.get(i));
						out.print("<br>");
					}
				}
				%>
			</div>
			<br>
			<div class="employeeInfo">
				<div class="firstLine">
					<p class="partition-around">
						<span> 従業員ID <input type="text" id="employeeId" value="<%=empInfoDTO.getEmployeeId()%>" disabled="disabled">
						</span> <span> 従業員名 <input type="text" id="employeeName" class="required" value="<%=empInfoDTO.getEmployeeName()%>" required>
						</span> <span> 性別 <input type="text" value="<%=empInfoDTO.getSex()%>" disabled="disabled">
						</span> <span> 生年月日 <input type="text" value="<%=empInfoDTO.getBirthDate()%>" disabled="disabled">
						</span>
					</p>
				</div>
				<p class="partition-around">
					<span> 所属会社 <input type="text" value="<%=empInfoDTO.getBelongCompany()%>" disabled="disabled">
					</span> <span> 事業所 <input type="text" class="required" value="<%=empInfoDTO.getOffice()%>" id="office" required>
					</span> <span> 所属組織 <select id="departmentId" class="required" required>
							<option value="<%=empInfoDTO.getDepartmentId()%>" selected="selected"><%=empInfoDTO.getDepartmentName()%></option>
							<%
							for (int i = 0; i < masterTableInfo.get("departmentDTOList").size(); i++) {
							%>
							<option value="<%=masterTableInfo.get("departmentDTOList").get(i).getMasterDataId()%>">
								<%=masterTableInfo.get("departmentDTOList").get(i).getMasterData()%>
							</option>
							<%
							}
							%>
					</select>
					</span> <span> 役職 <select id="postId" class="required" required>
							<option value="<%=empInfoDTO.getPostId()%>" selected="selected"><%=empInfoDTO.getPost()%></option>
							<%
							for (int i = 0; i < masterTableInfo.get("postDTOList").size(); i++) {
							%>
							<option value="<%=masterTableInfo.get("postDTOList").get(i).getMasterDataId()%>">
								<%=masterTableInfo.get("postDTOList").get(i).getMasterData()%>
							</option>
							<%
							}
							%>
					</select>
					</span>
				</p>
				<p class="partition-around">
					<span> 郵便番号 <input type="text" class="required" id="postalCode" value="<%=empInfoDTO.getPostalCode()%>" required>
					</span> <span> 住所 <input type="text" id="address" class="required" value="<%=empInfoDTO.getAddress()%>" required>
					</span> <span> TEL <input type="text" id="phoneNumber" class="required" value="<%=empInfoDTO.getPhoneNumber()%>" required>
					</span> <span> Mail <input type="email" id="mail" class="required" value="<%=empInfoDTO.getMail()%>" required>
					</span>
				</p>
			</div>
			<br>
		</div>
		<div class="center">
			<h3>≪保有スキル≫</h3>
			<div class="partition-right2">
				<label></label>
				<label></label>
				<label></label>
				<label>
					<input type="button" value="追加" onclick="addSkill()" class="button addRowButton">
				</label>
			</div>
		</div>
		<%
		for (int i = 0; i < skillTypeList.size(); i++) {
		%>
		<input type="hidden" class="upSkillTypeArray" value="<%=skillTypeList.get(i).isYearsDateOfAcquisition()%>">
		<%
		}
		%>
		<div class="center">
			<table border="1" id="empSkill" style="table-layout: fixed;">
				<thead>
					<tr>
						<th class="Skill_width">スキルType</th>
						<th>内容</th>
						<th class="ExperienceYears_width">経験年数</th>
						<th class="Date_width">取得年月</th>
						<th class="delete_button_width"></th>
					</tr>
				</thead>
				<tbody id="skill">
					<%
					for (EmpSkillInfoDTO empSkillDTO : empSkillInfoDTOList) {
					%>
					<tr>
						<td>
							<span> <select name="skillTypeId" class="updateSelectedSkill required width93" onchange="changeSkill(this)">
									<%
									for (int i = 0; i < masterTableInfo.get("skillDTOList").size(); i++) {
									%>
									<%
									if (empSkillDTO.getSkillTypeId().equals(masterTableInfo.get("skillDTOList").get(i).getMasterDataId())) {
									%>
									<option value="<%=masterTableInfo.get("skillDTOList").get(i).getMasterDataId()%>" selected="selected">
										<%=masterTableInfo.get("skillDTOList").get(i).getMasterData()%>
									</option>
									<%
									} else {
									%>
									<option value="<%=masterTableInfo.get("skillDTOList").get(i).getMasterDataId()%>">
										<%=masterTableInfo.get("skillDTOList").get(i).getMasterData()%>
									</option>
									<%
									}
									%>
									<%
									}
									%>
							</select> <input type="hidden" class="skillTypeFlg" value="<%=empSkillDTO.isYearsDateOfAcquisitionFlg()%>">
							</span>
						</td>
						<td>
							<input type="text" name="updateSkillDetail" onchange="changeSkill(this)" class="updateSkillDetail required width93" value="<%=empSkillDTO.getSkillDetail()%>" required>
						</td>
						<td>
							<%
							if (empSkillDTO.isYearsDateOfAcquisitionFlg()) {
							%>
							<input type="text" size="12" name="updateExperienceYears" class="updateExperienceYears  experienceYears required" onchange="changeSkill(this)" value="<%=empSkillDTO.getExperienceYears()%>"
								required>
							<%
							} else {
							%>
							<input type="text" size="12" name="updateExperienceYears" value="" class="updateExperienceYears experienceYears" onchange="changeSkill(this)" readonly
								style="background-color: #EEEEEE; text-align: center">
							<%
							}
							%>
						</td>
						<td>
							<%
							if (empSkillDTO.isYearsDateOfAcquisitionFlg()) {
							%>
							<input type="month" name="updateAcquisitionYearMonth" class="updateAcquisitionYearMonth acquisitionYearMonth" onchange="changeSkill(this)" value="" readonly style="background-color: #EEEEEE;">
							<%
							} else {
							%>
							<input type="month" name="updateAcquisitionYearMonth" class="updateAcquisitionYearMonth acquisitionYearMonth required" onchange="changeSkill(this)"
								value="<%=empSkillDTO.getAcquisitionYearMonth()%>" required>
							<%
							}
							%>
						</td>
						<td>
							<input type="hidden" value="<%=empSkillDTO.getSkillInfoId()%>" class="skillInfoId" name="skillInfoId">
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="center">
			<h3>≪業務経験≫</h3>
			<div class="partition-right2">
				<label></label>
				<label></label>
				<label></label>
				<label>
					<input type="button" value="追加" onclick="addTeamInfo()" class="button addRowButton">
				</label>
			</div>
		</div>
		<div class="center">
			<table border="1" id="empWorkExp" style="table-layout: fixed;">
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
						<th class="delete_button_width"></th>
					</tr>
				</thead>
				<tbody id="empExp" style="word-wrap: break-word">
					<%
					for (EmpWorkExpDTO empWorkExpDTO : empWorkExpDTOList) {
					%>
					<tr>
						<td class="updateResponsibleProject">
							<div class="workExpTd"><%=empWorkExpDTO.getResponsibleProject()%></div>
						</td>
						<td class="updateResponsibleIndustry">
							<div class="workExpTd"><%=empWorkExpDTO.getResponsibleIndustry()%></div>
						</td>
						<td class="updateProjectAttributionCompany">
							<div class="workExpTd"><%=empWorkExpDTO.getProjectAttributionCompany()%></div>
						</td>
						<td class="updateResponsibleManager">
							<div class="workExpTd"><%=empWorkExpDTO.getResponsibleManager()%></div>
						</td>
						<td class="updateBelongTeam">
							<div class="workExpTd"><%=empWorkExpDTO.getBelongTeam()%></div>
						</td>
						<td class="updateRole"><%=empWorkExpDTO.getRole()%></td>
						<td class="updateContractType"><%=empWorkExpDTO.getContractType()%></td>
						<%
						if (empWorkExpDTO.getMonthlyUnitPrice() == null) {
						%><td class="updateMonthlyUnitPrice">
							<div class="workExpTd">-</div>
						</td>
						<%
						} else {
						%><td class="updateMonthlyUnitPrice">
							<div class="workExpTd">
								&yen;<%=empWorkExpDTO.getMonthlyUnitPrice()%></div>
						</td>
						<%
						}
						%>
						<td>
							<input type="date" onchange="changeWorkExp(this)" name="updateTeamBelongStartDate" class="updateTeamBelongStartDate required" value="<%=empWorkExpDTO.getTeamBelongStartDate()%>" required>
						</td>
						<td>
							<input type="date" onchange="changeWorkExp(this)" name="updateTeamBelongCompleteDate" class="updateTeamBelongCompleteDate" value="<%=empWorkExpDTO.getTeamBelongCompleteDate()%>">
						</td>
						<td>
							<input type="hidden" class="employeeExperienceId" value="<%=empWorkExpDTO.getEmployeeExperienceId()%>" name="employeeExperienceId">
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<p class="partition-center">
			<span style="margin-right: 1%"> <input type="button" value="更新" id="openUpdateDialog" class="button">
			</span> <span style="margin-left: 1%"> <input type="button" value="削除" id="openDeleteDialog" class="button">
			</span>
		</p>
	</form>
	<div id="dialog-confirm-employee-update" style="display: none;">この内容で更新しますか？</div>
	<div id="dialog-confirm-employee-delete" style="display: none;">この従業員を削除しますか？</div>
	<script type="text/javascript" src="../js/updateEmployee/updateEmployee.js"></script>
	<script type="text/javascript" src="../js/updateEmployee/deleteEmployee.js"></script>
</body>
</html>