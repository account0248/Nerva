<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.vaile.nerva.registEmployee.RegistEmpPageDTO"%>
<%@ page import="jp.co.vaile.nerva.registEmployee.RegistSkillInfoPageDTO"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.companyMaster.SearchCommonCompanyDTO"%>
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
<script type="text/javascript" src="../js/registemployee/addSkill.js"></script>
<link rel="stylesheet" type="text/css" href="../js/jquery-ui/jquery-ui.css">
<title>従業員登録</title>
</head>
<body>
	<%
	List<SearchCommonCompanyDTO> companyList = (List<SearchCommonCompanyDTO>) request.getAttribute("companyList");
	List<FetchAnyMasterTableDTO> departmentList = (List<FetchAnyMasterTableDTO>) request.getAttribute("departmentList");
	List<FetchAnyMasterTableDTO> postList = (List<FetchAnyMasterTableDTO>) request.getAttribute("postList");
	List<FetchAnyMasterTableDTO> skillTypeList = (List<FetchAnyMasterTableDTO>) request.getAttribute("skillTypeList");
	RegistEmpPageDTO registEmpPageDTO = (RegistEmpPageDTO) request.getAttribute("registEmpPageDTO");
	RegistSkillInfoPageDTO registSkillInfoPageDTO = (RegistSkillInfoPageDTO) request.getAttribute("registSkillInfoPageDTO");
	%>
	<a href="./ShowSearchEmpAction"><input type="button" value="戻る"></a>
	<form method="POST" action="RegistEmpAction" name="registEmpAction">
		<div class="center">
			<div class="title">従業員登録</div>

			<br>
			<div class="errorMessage" id="errorList">
				<%
				List<String> errorList = (List<String>) request.getAttribute("errorList");
				if (errorList != null) {

					for (int i = 0; i < errorList.size(); i++) {
						out.println(errorList.get(i));
						out.print("<br>");
					}
				}
				%>
			</div>
			<br>

			<p class="partition-around">
				<span> 従業員ID <input required class="required" type="text" name="employeeId" placeholder="" id="employeeId"
						value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getEmployeeId()%><%}%>">
				</span> <span> 従業員名 <input required class="required" type="text" name="employeeName" id="employeeName" value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getEmployeeName()%><%}%>">
				</span> <span> 性別 <select required class="required" name="sex" id="sex">
						<option value=""></option>
						<option value="男">男</option>
						<option value="女">女</option>
				</select>
				</span> <span> 生年月日 <input required class="required" type="date" name="birthDate" id="birthDate" value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getBirthDate()%><%}%>">
				</span>
			</p>
			<p class="partition-around">
				<span> 所属会社 <select required class="required company" name="companyId" id="companyId" onchange="changePlaceholder()">
						<option value=""></option>
						<%
						for (int i = 0; i < companyList.size(); i++) {
						%>
						<option value="<%=companyList.get(i).getCompanyId()%>"><%=companyList.get(i).getCompanyName()%></option>
						<%
						}
						%>
				</select> <%
 for (int i = 0; i < companyList.size(); i++) {
 %> <input type="hidden" id="<%=companyList.get(i).getCompanyId()%>" name="companyCode" value="<%=companyList.get(i).getCompanyCode()%>" /> <%
 }
 %>

				</span> <span> 事業所 <input required class="required" type="text" name="office" id="office" value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getOffice()%><%}%>">
				</span> <span> 所属組織 <select required class="required" name="departmentId" id="departmentId">
						<option value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getDepartmentId()%><%}%>">
							<%
							if (registEmpPageDTO != null) {
							%><%=registEmpPageDTO.getPostId()%>
							<%
							}
							%>
						</option>
						<%
						for (int i = 0; i < departmentList.size(); i++) {
						%>
						<option value="<%=departmentList.get(i).getMasterDataId()%>"><%=departmentList.get(i).getMasterData()%></option>
						<%
						}
						%>
				</select>
				</span> <span> 役職 <select required class="required" name="postId" id="postId">
						<option value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getPostId()%><%}%>">
							<%
							if (registEmpPageDTO != null) {
							%><%=registEmpPageDTO.getPostId()%>
							<%
							}
							%>
						</option>
						<%
						for (int i = 0; i < postList.size(); i++) {
						%>
						<option value="<%=postList.get(i).getMasterDataId()%>"><%=postList.get(i).getMasterData()%></option>
						<%
						}
						%>
				</select>
				</span>
			</p>
			<p class="partition-around">
				<span> 郵便番号 <input required class="required" type="text" name="postalCode" id="postalCode" value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getPostalCode()%><%}%>"
						placeholder="000-0000">
				</span> <span> 住所 <input required class="required" type="text" name="address" id="address" value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getAddress()%><%}%>">
				</span> <span> TEL <input required class="required" type="text" name="phoneNumber" id="phoneNumber" placeholder="000-0000-0000"
						value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getPhoneNumber()%><%}%>">
				</span> <span> Mail <input required class="required" type="text" name="mail" id="mail" value="<%if (registEmpPageDTO != null) {%><%=registEmpPageDTO.getMail()%><%}%>" placeholder="XXX@XXX.XX">
				</span>
			</p>
			<br>
		</div>
		<div class="center">
			<h3>≪保有スキル≫</h3>
		</div>
		<div class="partition-right2">
			<span> 
				<input type="button" value="追加" onclick="addSkill()">
			</span>
		</div>
		<div>
			<table border="1">
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
				</tbody>
			</table>
		</div>
		<p class="partition-center">
			<span> <input type="button" value="登録" id="btnRegistDialog">
			</span>
		</p>
	</form>
	<div id="dialog-confirm-regist-employee" style="display: none;">この内容で登録しますか？</div>
	<script type="text/javascript" src="../js/registemployee/registEmployee.js"></script>
</body>
</html>