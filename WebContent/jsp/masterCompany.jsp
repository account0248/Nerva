<%@ page language="java" contentType="text/html; Stringset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<!DOCTYPE html>

<html>

<head>
	<meta Stringset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/general.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" type="text/css" href="css/scroll.css">
	<link rel="stylesheet" type="text/css" href="./js/jquery-ui/jquery-ui.css">
	<script type="text/javascript" src="js/list.min.js"></script>
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/searchEmployee.js"></script>
	<script type="text/javascript" src="js/updated.js"></script>
	<script type="text/javascript" src="./js/list.min.js"></script>
	<script type="text/javascript" src="./js/jquery-ui/jquery-ui.js"></script>
	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet"> 
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

	<title>所属会社マスタメンテナンス</title>
</head>
<body>
<%
List<Character> companyCodeList= (List<Character>) request.getAttribute("companyCodeList");
String companyBelongCode=(String) request.getAttribute("companyBelongCode");
String companyParent =  (String) request.getAttribute("companyParent");
String companyChild= (String) request.getAttribute("companyChild");
String parentCompanyValue=  (String) request.getAttribute("parentCompanyValue");
String subCompanyValue= (String) request.getAttribute("subCompanyValue");
List<Character> companyAllCodeList= (List<Character>) request.getAttribute("companyAllCodeList");
%>
	<input type="button" value="戻る" onclick="location.href='./jsp/masterManagement.jsp'">
	<div class="center">
		<div class="title">所属会社マスタメンテナンス</div>
		<div class="errorMessage partition-center mt-2" id="errorList"></div>
		<div id="display-grey">
		</div>
		<div id="new-registory">
			<i class="fas fa-times-circle"></i>
			<h3>　≪所属会社新規登録≫　</h3>
			<form action="#">
			<div  class="errorMessage mt-2 partition-center" id="registErrorList"></div>
				<p class="partition-around mt-2">
					<span class=masterSpan>

						<label for="companyId">所属会社ID </label><input type="text" id="registCompanyId" class="mr-3 masterInput">
						<label for="companyName">所属会社名 </label><input type="text" id="registCompanyName"class="masterInput">
					</span>
				</p>
				<p class="partition-around">
					<span class=masterSpan>
					グループ <select name="group" id="registCompanyGroup" class="mr-3 masterInput">		
									
						<option value=""></option>
						<%if (companyParent!=null) {%>
						<option value="<%=parentCompanyValue%>"><%=companyParent%></option>
						<%}%>
						<option value="<%=subCompanyValue%>"><%=companyChild%></option>
					</select>
					会社識別コード <select name="code" id="registCompanyCode"class="masterInput">
						
						<option value=""></option>
						
						<%for (int i = 0; i < companyCodeList.size(); i++) {%>
						<option value="<%=companyCodeList.get(i)%>"><%=companyCodeList.get(i)%></option>
						<%}%>
						
					
					</select>
				</span>
				</p>
				<p class="partition-center">
					<span><input type="button" value="登録"id ="btnRegistDialog"></span>
				</p>
			</form>
			<script type="text/javascript" src="./js/new_register.js"></script>
			<script type="text/javascript" src="./js/insertMasterCompany.js"></script>	
			
		</div>
	</div>
	<h3 class="partition-center mt-2">　≪所属会社検索≫　</h3>
	<form action="masterCompanyRegist">
		<p class="partition-around">
			<span class=masterSpan>
				所属会社ID <input type="text" id=companyId class=masterInput>
				所属会社名 <input type="text" id=companyName class=masterInput>
				グループ <select name="group" id=companyGroup class="masterInput">
					<option value=""></option>
						<%if (companyParent!=null) {%>
					<option value="<%=parentCompanyValue%>"><%=companyParent%></option>
					<%}%>
					<option value="<%=subCompanyValue%>"><%=companyChild%></option>
				</select>
				会社識別コード <select name="code" id=companyCode class="masterInput">
				
					<option value=""></option>
						<%if (companyBelongCode==null) {%>
						<%for (int i = 0; i < companyAllCodeList.size(); i++) {%>
						<option value="<%=companyAllCodeList.get(i)%>"><%=companyAllCodeList.get(i)%></option>
						<%}%>
						<%}%>
						<%if (companyBelongCode!=null) {%>
						<option value="<%=companyBelongCode%>"><%=companyBelongCode%></option>	
						<%}%>
				</select>
			</span>
		</p>
		<br>
		<p class="partition-center">
			<span>
				<input type="button" value="検索" id="search_button">
				<input type="button" value="新規登録" id="regist_button">
			</span>
		</p>

	</form>

	<h3 class="partition-center mt-5">　≪所属会社一覧≫　</h3>
	<form action="#">
		<div id="sort" class="scroll width45">
			<table border="1" class="table-w100 tableList">
				<thead>
					<tr>

						<th class="table-w18" data-sort="companyId">所属会社ID</th>
						<th  data-sort="companyName">所属会社名</th>
						<th class="table-w12" >グループ</th>
						<th class="table-w24">会社識別コード</th>
					</tr>
					
				</thead>
				<tbody id="company">
				</tbody>
			</table>
		</div>


		<div class="partition-center mt-2">
		
			<input type="button" value="更新" id="updateCompany" style="display: none;">
			<div id="dialog-confirm" style="display:none;">この内容で更新しますか？</div>
			<div id="dialog-confirm-insert-masterCompany" style="display:none;">この内容で登録しますか？</div>		</div>
	</form>

<script type="text/javascript" src="./js/searchMasterCompany.js"></script>	
<script type="text/javascript" src="./js/updateMasterCompany.js"></script>

</body>

</html>